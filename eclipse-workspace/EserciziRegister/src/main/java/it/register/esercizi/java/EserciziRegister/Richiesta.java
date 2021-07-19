package it.register.esercizi.java.EserciziRegister;

/**
 * @author maurizio.gargiulo
 * la classe Richiesta rappresenta la richiesta presenta nel file di log di input
 *
 */
public class Richiesta {

	// Proprietà per le quali prevedi anche getter e setter
	String ipaddress;
	int byte_sent;

	/**
	* Costruttore
	*/
	public Richiesta(String ipaddress,int byte_sent) {
		this.ipaddress = ipaddress;
		
		this.byte_sent= byte_sent;

	}

	public int getByte_sent() {
		return byte_sent;
	}

	public void setByte_sent(int byte_sent) {
		this.byte_sent = byte_sent;
	}

	public String getIpaddress() {
		
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
