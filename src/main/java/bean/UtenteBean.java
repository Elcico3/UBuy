package bean;

import controller.GestoreVenditore;
import eccezioni.CredenzialiErrateException;
import eccezioni.UsernameException;

public class UtenteBean {

	private String username;
	private String password;
	private String tipologia;
	private String nome;
	private String cognome;
	private boolean log;

	public UtenteBean() {
		this.username = "";
		this.password = "";
		this.tipologia = "";
		this.nome = "";
		this.log = false;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean isLog() {
		return log;
	}

	public boolean convalida() throws CredenzialiErrateException {

		this.log = false;
		if (this.username.equals("") || this.password.equals("")) {
			return this.log;
		}
		GestoreVenditore Controller = GestoreVenditore.avviaControllerUtente();
		this.log = Controller.inserisciCredenziali(this.username, this.password, this.tipologia, this);

		return this.log;
	}

	public boolean registra() throws UsernameException {
		if (this.username.equals("") || this.password.equals("") || this.nome.equals("") || this.cognome.equals("")) {
			return false;
		}
		GestoreVenditore Controller = GestoreVenditore.avviaControllerUtente();
		Controller.convalidaRegistrazione(this.nome, this.cognome, this.username, this.password, this.tipologia);
		return (Controller.getAccountV() != null);
	}
	// public List<ProdottoBean> creaProdottiBean(){
	// GestoreVenditore gu=GestoreVenditore.avviaControllerUtente();
	// GestoreProdotto gp=GestoreProdotto.avviaControllerProdotto();
	// List<ProdottoBean> listaBean=new ArrayList<ProdottoBean>();
	// listaBean=gp.ottieniProdottiBeanVenditore(gu.getAccountV());
	// return listaBean;
	// }
}
