package it.register.esercizi.java.EserciziRegister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maurizio.gargiulo
 * questa classe si occupa della gestione (lettura, caricamento) di un file di log 
 * e della scrittura di un nuovo file .csv risultante 
 *
 */
public class UtilityReport {

	ArrayList<Richiesta> list;
	String ipaddress;
	long numero_richieste_totali = 0;
	

	/**
	 * @param nome_file
	 * @return lista di Richieste
	 * 
	 * il metodo CaricaFile prendendo in input il nome del file si occupa di "caricare" il file
	 * restituiendo un Lista di tipo Record, escludendo dalla lista le richieste con stato OK
	 */
	public ArrayList<Richiesta> CaricaFile(String nome_file) {
		
		Pattern pattern = Pattern.compile(","); //i campi sono divisi per ,
		try (Stream<String> lines = Files.lines(Path.of(nome_file))) {
			
		  list = (ArrayList<Richiesta>) lines.filter(str ->  str.contains("OK"))//prendo solo le richieste con stato OK
				  //posso prendere "contains OK" perchè per come è strutturato il report se ci sta un OK può esserci solo nella colonna status
				  .map(line -> {
					  String[] arr = pattern.split(line);
					  return new Richiesta(
							  arr[3], //ipaddress
							  Integer.parseInt(arr[1])//byte sent
					   ); 
				  }).collect(Collectors.toList());
		  
		  numero_richieste_totali = list.size();//il numero richieste totali coinciderà con il numero righe
		  return list;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * @param nome_file_sorgente
	 * @param nome_file_destinazione
	 * @return true o false in base al successo o insuccesso della creazione
	 * 
	 * il metoodo CreaReport prendendo in input i nomi dei file sorgente e di destinazione, crea un nuovo file di report
	 * qualora il metodo fallisca ritorna false, altrimenti true
	 */
	public boolean CreaReport(String nome_file_sorgente, String nome_file_destinazione) {
		try {
		
			ArrayList<Richiesta> list = CaricaFile(nome_file_sorgente); //carico il file
			
			Long totale_byte_inviati = list.stream().collect(Collectors.summingLong(Richiesta::getByte_sent)); //prendo la somma totale dei byte inviati
		    
			Map<Object, Object> mappa_raggruppata_per_ip = list.stream()//apro il flusso stream
		    		.collect(Collectors.groupingBy(Richiesta::getIpaddress))//raggruppo per ip
		    		.entrySet().stream()
				    .collect(Collectors.toMap(x -> { //per ogni elemento creo un nuovo oggetto Record Output
				        int somma_byte_inviati= x.getValue().stream().mapToInt(Richiesta::getByte_sent).sum(); //prendo la somma dei byte inviati dall'ip corrente
				        Long numero_richieste = x.getValue().stream().collect(Collectors.counting());//conto le richieste che ci sono da parte dell'ip corrente
				        return new Richiesta_Aggregata(x.getKey(), somma_byte_inviati, numero_richieste, ((numero_richieste*100)/numero_richieste_totali), (somma_byte_inviati*100/totale_byte_inviati)); //ritorno la struttura Record Output calcolando la percentuale richieste e la percentuale byte inviati
				    }, Map.Entry::getValue));
			
			Stream<Richiesta_Aggregata> stream = mapToStream(mappa_raggruppata_per_ip);
			
			List<Richiesta_Aggregata> lista_file_destinazione = stream.sorted(Comparator.comparingLong(Richiesta_Aggregata::getNumber_request).reversed()) //ordino la lista per numero richieste desc
					.collect(Collectors.toList());
			if(ScriviReport(lista_file_destinazione, nome_file_destinazione))//se le cose sono andate bene esco con true
				return true;
			
			return false;//se arrivo in fin qui esco con false
		}
		catch(Exception e) {
			e.printStackTrace();
			return false; //in caso di eccezioni esco con false
		}
	}
		
	/**
	 * @param lista_file_destinazione
	 * @param nome_file_destinazione
	 * @return true o false in base al successo o insuccesso della scrittura del report
	 * metodo che si occupa della scrittura del file csv di output, partendo da una lista di RecordOutput
	 *  qualora il metodo fallisca ritorna false, altrimenti true
	 */
	public boolean ScriviReport(List<Richiesta_Aggregata> lista_file_destinazione, String nome_file_destinazione) {
		
		if(lista_file_destinazione ==null || nome_file_destinazione==null || nome_file_destinazione.equals(""))
			return false;
		 
		try (PrintWriter writer = new PrintWriter(new File(nome_file_destinazione))) {
			 StringBuilder sb = new StringBuilder();
			 
			  sb.append("ipaddress,");
			  sb.append("nr,");
			  sb.append("pr,");
			  sb.append("bi,");
			  sb.append("pb");
			  sb.append('\r');
	          sb.append('\n');
			 
	        for(Richiesta_Aggregata entry : lista_file_destinazione) {
	               
	            sb.append(entry.getIpaddress()+",");
	            sb.append(entry.getNumber_request()+",");
	            sb.append(entry.getPercent_request()+",");
	            sb.append(entry.getByte_sent()+",");
	            sb.append(entry.getPercent_byte()+"");
	            sb.append('\r');
	            sb.append('\n');
	        }
	        
	        writer.write(sb.toString());
	        return true;
	
	   } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage());
	      return false;
	   }
		
   }
	
	
    /**
     * @param <K>
     * @param <V>
     * @param map
     * @return
     * 
     * metodo usato per convertire `Map<K, V>` in `Stream<K>`
     */ 
    @SuppressWarnings("unchecked")
	private static <K, V> Stream<Richiesta_Aggregata> mapToStream (Map<K, V> map) {
        return (Stream<Richiesta_Aggregata>) map.keySet().stream();
    }
}
