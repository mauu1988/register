package it.register.esercizi.java.EserciziRegister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestEsercizio2 {
	UtilityMoltiplicazioneAlternativa obj = new UtilityMoltiplicazioneAlternativa();
	 @Test
	  public void TestGetValoreFromArray() {
		 
		 int array_input[] = { 1, 2, 3}; //inizializzando così l'elemento zero è il numero 1, l'elemento uno e è il numero 2 e il terzo elemento è il 3 per dovrebbe restiture 321
		 
		  assertEquals(321, obj.GetValoreFromArray(array_input));
	  }
	 
	 @Test
	  public void TestGetValoreFromArrayNull() {
		 
		 int array_input[] = null; //inizializzando così l'elemento zero è il numero 1, l'elemento uno e è il numero 2 e il terzo elemento è il 3 per dovrebbe restiture 321
		 
		  assertEquals(0, obj.GetValoreFromArray(array_input));
	  }
	 
	 @Test
	  public void TestGetArrayFromValore() {
		 
		  assertEquals(3, obj.GetArrayFromValore(123).length);
	  }
	 
	 @Test
	  public void TestMoltiplicazioneAlternativa() {
		 int moltiplicando[] = { 5, 1}; 
		 int moltiplicatore[] = {2}; 
		 
		 int output[] = obj.Moltiplica(moltiplicando,moltiplicatore);
		
		 assertEquals(30,  obj.GetValoreFromArray(output));
	  } 
	 
	 @Test
	  public void TestMoltiplicazioneAlternativaConZero() {
		 int moltiplicando[] = { 5, 1}; 
		 int moltiplicatore[] = {0}; 
		 
		 int output[] = obj.Moltiplica(moltiplicando,moltiplicatore);
		
		 assertEquals(0,  obj.GetValoreFromArray(output));
	  } 
	 
	 @Test
	  public void TestMoltiplicazioneAlternativaConZeroMoltiplicando() {
		 int moltiplicando[] = { 0}; 
		 int moltiplicatore[] = {1,5}; 
		 
		 int output[] = obj.Moltiplica(moltiplicando,moltiplicatore);
		 assertEquals(0,  obj.GetValoreFromArray(output));
	  } 
	 
	 @Test
	  public void TestMoltiplicazioneConNegativi() {
		 int moltiplicando[] = { 2}; 
		 int moltiplicatore[] = {-20}; 
		 
		 int output[] = obj.Moltiplica(moltiplicando,moltiplicatore);
		 assertEquals(-40,  obj.GetValoreFromArray(output));
	  } 
	 
	 
}