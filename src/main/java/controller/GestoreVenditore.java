package controller;

import bean.UtenteBean;
import dominio.Venditore;
import eccezioni.CredenzialiErrateException;
import eccezioni.UsernameException;
import hibernate.FactoryVenditore;

public class GestoreVenditore {

	private static GestoreVenditore controllerUtente;
	private Venditore accountV;

	public GestoreVenditore() {

	}

	public static GestoreVenditore avviaControllerUtente() {
		if (controllerUtente == null)
			controllerUtente = new GestoreVenditore();
		return controllerUtente;
	}

	public Venditore getAccountV() {
		return accountV;
	}

	public void setAccountV(Venditore accountV) {
		this.accountV = accountV;
	}

	public boolean inserisciCredenziali(String username, String password, String tipologia, UtenteBean ub)
			throws CredenzialiErrateException {
		if (tipologia.equals("Venditore")) {
			setAccountV(FactoryVenditore.autentica(username, password));
			if (this.getAccountV() == null)
				return false;
			ub.setCognome(this.accountV.getCognome());
			ub.setNome(this.accountV.getNome());
		}
		return true;

	}

	public void convalidaRegistrazione(String nome, String cognome, String username, String password, String tipologia)
			throws UsernameException {
		if (tipologia.equals("Venditore")) {
			setAccountV(new Venditore(nome, cognome, username, password));
			FactoryVenditore.salva(getAccountV());
		} else {
			// setAccountV(new Venditore(nome, cognome, username, password));
			// FactoryVenditore.salva(getAccountV());

		}
	}
	// public Venditore ottieniVenditore(String ub){
	// Venditore v = FactoryVenditore.ricercaVenditore(ub);
	// return v;
	// }
	// public int ottieniIdVenditore(UtenteBean ub){
	// int i= FactoryVenditore.reicercaIdVenditore(ub.getUsername());
	// return i;
	//
	// }
}
