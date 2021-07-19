package it.register.esercizi.java.EserciziRegister;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestEsercizio1 {
	
UtilityReport obj = new UtilityReport();
String nome_file_output = "test.csv";
  @Test
  public void TestInputFile() {
	  ArrayList<Richiesta> richieste = obj.CaricaFile("requests.log");
	  assertEquals(richieste.size(), 6);
  }
  
  @Test
  public void TestFileNull(){
	  Object richieste = obj.CaricaFile(null);
	  assertEquals(null, richieste);
  }
  
  @Test
  public void TestFileNofFound(){
	  Object richieste = obj.CaricaFile("request");
	  assertEquals(null, richieste);
  }
  
  @Test
  public void TestFileInputBlank(){
	  Object richieste = obj.CaricaFile("");
	  assertEquals(null, richieste);
  }
  
  @Test
  public void TestCreazioneReportFileSorgenteNull(){
	 
	  assertEquals(false,obj.CreaReport(null,nome_file_output));
  }
  
  @Test
  public void TestCreazioneReportFileDestinazioneNull(){
	 
	  assertEquals(false, obj.CreaReport("requests.log",null));
  }
  
  @Test
  public void TestCreazioneReportFile(){
	 
	  assertEquals(true, obj.CreaReport("requests.log","test.csv"));
  }
  
  @Test
  public void TestScritturaReport(){
	  
	  try {
		  
		  Richiesta_Aggregata test = new Richiesta_Aggregata("127.0.0.1", 3, 50L, 450L, 81L);
		  List<Richiesta_Aggregata> lista_output = new ArrayList<Richiesta_Aggregata>();
		  lista_output.add(test);
		  assertEquals(true, obj.ScriviReport(lista_output, nome_file_output));
		  assertEquals(2,Files.lines(Paths.get(nome_file_output)).count()); //devono esserci 2 righe perchè una è quella di intesazione
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @Test
  public void TestScritturaReportVuoto(){
	  assertEquals(false, obj.ScriviReport(null, nome_file_output));
  }

}
