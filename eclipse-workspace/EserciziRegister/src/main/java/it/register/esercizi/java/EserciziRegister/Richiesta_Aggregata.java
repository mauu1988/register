package it.register.esercizi.java.EserciziRegister;

/**
 * @author maurizio.gargiulo
 * questa classe rappresente la richiesta in formato "aggregato" che andrà
 * a essere scritta nel report di destinazione
 *
 */
public class Richiesta_Aggregata extends Richiesta {
	
	Long number_request;
	Long percent_request;
	Long percent_byte;
	
	public Richiesta_Aggregata(String ipaddress, int totale_byte_sent, Long number_request, Long percent_request, Long percent_byte) {
		super(ipaddress, totale_byte_sent);
		this.ipaddress = ipaddress;
		this.number_request = number_request;
		this.percent_request = percent_request;
		this.percent_byte = percent_byte;
		// TODO Auto-generated constructor stub
	}

	public Long getNumber_request() {
		return number_request;
	}

	public void setNumber_request(Long number_request) {
		this.number_request = number_request;
	}

	public Long getPercent_request() {
		return percent_request;
	}

	public void setPercent_request(Long percent_request) {
		this.percent_request = percent_request;
	}

	public Long getPercent_byte() {
		return percent_byte;
	}

	public void setPercent_byte(Long percent_byte) {
		this.percent_byte = percent_byte;
	}
	
	

}
