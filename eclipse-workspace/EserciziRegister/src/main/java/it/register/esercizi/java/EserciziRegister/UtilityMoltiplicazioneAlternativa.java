package it.register.esercizi.java.EserciziRegister;

import java.util.LinkedList;

/**
 * @author maurizio.gargiulo
 * questa classe offre tutte le utility necessario per effettuare una moltiplicazione
 * "alternativa" attraverso l'operatore + (anzichè *) 
 */
public class UtilityMoltiplicazioneAlternativa { 
	  private final int tipo_sistema = 10;  //essendo il nostro sistema decimale qui ci metto 10
	
	
	/**
	 * @param input array di interi che dovrà essere "convertito"
	 * @return il valore "reso intero" degli elementi dell'array di input
	 * 
	 * questo metodo si occupa di ritornare un unico valore del nostro
	 * sistema decimale posizionale a fronte di un array di interi preso in input
	 */
	public int GetValoreFromArray(int [] input) {
		
		int totale = 0;
		
		if(input==null || input.length==0)//se l'input è vuoto esco direttamente con zero
			return 0;
		
		for(int i=0;i<input.length;i++) {
			totale += (int) (input[i]*Math.pow(tipo_sistema, i)); //sfrutto il fatto che il nostro sistema è psozionale
		}
		
		return totale; //il totale rappresenterà il numero "reso intero" 
	}
	
	/**
	 * @param input numero che si intende trasformare in array
	 * @return l'array "composto" dal numero preso in input 
	 * il metodo dall'intero preso in input, riempie e ritorna un array partendo dalla posizione 0 per le unità, posizione 1 per le decine (e così via..) 
	 * ritornando quindi l'array corrispondente
	 */
	public int[] GetArrayFromValore(int input) {
		  
	        LinkedList<Integer> stack = new LinkedList<Integer>();
	        
	        while(Math.abs(input) > 0){//lavoro per valore assoluto per permettere la conversione anche tra numeri negativi
	            stack.push(input % 10);
	            input = input / 10;
	        }
	        int i =stack.size()-1;
	        int [] output = new int [stack.size()];
	        while ((!stack.isEmpty())){
	        	output[i] = (stack.pop()); 
	        	i--;
	        }
	        return output;
	}
	
	/**
	 * @param array_moltiplicando
	 * @param array_moltiplicatore
	 * @return l'array risultante della moltiplicazione "alternativa" (effettuata tramite l'operatore +)
	 * il metodo prende in input 2 array di interi che ne fa la moltiplicazione utilizzando l'operatore + e 
	 * restituendo l'array risultante che avrà il valore della moltiplicazione allocatto partendo dalla posizione 0 per le unità, 1 per le decine e così via.
	 */
	public int [] Moltiplica(int [] array_moltiplicando, int [] array_moltiplicatore) {
		int totale = 0;
		int moltiplicando = GetValoreFromArray(array_moltiplicando);
		int moltiplicatore = GetValoreFromArray(array_moltiplicatore);
		//dopo aver convertito tutto in interi
		
		int numero_step = Math.abs(moltiplicatore); //prendo il valore assoluto degli step da seguire
		
		while(numero_step>0) {  //procedo alla moltiplicazione effettuando n somme
			
			totale += moltiplicando;
			numero_step--;
		}
		
		if((moltiplicando<0 && moltiplicatore>0) || (moltiplicando>0 && moltiplicatore<0) ) //se solo uno dei due valori e' negativo inverto il segno finale sul totale
		{	totale = totale*-1;
		
		}
		
		return GetArrayFromValore(totale); //ritorno il totale in "formato array"
	}

}
