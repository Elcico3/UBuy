package bean;

import controller.GestoreCompratore;

public class ValutazioneBean {

	private int voto;
	private String commento;
	private String usernameCompratore;
	private String usernameVenditore;

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public String getUsernameCompratore() {
		return usernameCompratore;
	}

	public void setUsernameCompratore() {
		this.usernameCompratore = GestoreCompratore.avviaControllerGestore().getAccountC().getUsername();
	}

	public String getUsernameVenditore() {
		return usernameVenditore;
	}

	public void setUsernameVenditore(String usernameVenditore) {
		this.usernameVenditore = usernameVenditore;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		if (voto > 5) {
			this.voto = 5;
		} else if (voto < 1) {
			this.voto = 1;
		}else{
			this.voto=voto;
		}

	}
	public void inviaValutazione(){
		GestoreCompratore gc=GestoreCompratore.avviaControllerGestore();
		gc.confermaValutazione(this.voto, this.commento, this.usernameCompratore,this.usernameVenditore);
		
	}
}
