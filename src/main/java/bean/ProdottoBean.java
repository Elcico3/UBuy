package bean;

import java.util.List;

import controller.GestoreProdotto;
import controller.GestoreVenditore;
import eccezioni.ProdottoPresenteException;

public class ProdottoBean {

	private String nome;
	private String descrizione;
	private double prezzo;
	private String categorie;
	private String usernameVenditore;
	public ProdottoBean() {
		this.nome = "";
		this.descrizione = "";
		this.prezzo = 0;
		this.categorie = null;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	

	public boolean inserisciProdotto(UtenteBean ub) throws ProdottoPresenteException {
		if (this.nome.equals("") || this.descrizione.equals("") || this.prezzo < 0)
			return false;
		GestoreProdotto g = GestoreProdotto.avviaControllerProdotto();
		
		g.aggiungiProdotto(this.nome, this.prezzo, this.descrizione, this.categorie);//,gu.ottieniVenditore(ub.getUsername()));
		return true;
	}
	
	public boolean eliminaProdotto(ProdottoBean pb,UtenteBean ub){
		GestoreProdotto gp=GestoreProdotto.avviaControllerProdotto();
		//GestoreUtente gu=GestoreUtente.avviaControllerUtente();
		gp.eliminaProdottoVenditore(pb.getNome(), ub);
		return false;
	}
	public List<ProdottoBean> ottieniBeanProdottiVenditore(){
		GestoreProdotto gp=GestoreProdotto.avviaControllerProdotto();
		GestoreVenditore gu= GestoreVenditore.avviaControllerUtente();
		List<ProdottoBean> pblist=gp.ottieniProdottiBeanVenditore(gu.getAccountV());
		return pblist;
	}
	
	public void iniziaModificaProdotto(String vecchioNome, ProdottoBean nuovoBean,UtenteBean ub) throws ProdottoPresenteException{
		System.out.println("ProdottoBean iniziaModificaProdotto");
		GestoreProdotto gp=GestoreProdotto.avviaControllerProdotto();
		gp.modificaProdottoDaBean(vecchioNome, nuovoBean,ub);
	}
	public void stampaBean(){
		System.out.println("Inizio stampa Bean:\nNome Prodotto: "+this.nome+"\nPrezzo Prodotto: "+this.prezzo+" euro\nDescrizione: "+this.descrizione);
	}

	public String getUsernameVenditore() {
		return usernameVenditore;
	}

	public void setUsernameVenditore(String usernameVenditore) {
		this.usernameVenditore = usernameVenditore;
	}

//	public Venditore getVenditore() {
//		return venditore;
//	}

//	public void setVenditore(Venditore venditore) {
//	}
//	public void rimuoviProdottoDaCompratore() throws IOException, CronologiaVuotaException, CredenzialiVuoteException{
//		GestoreCompratore gc = GestoreCompratore.avviaControllerGestore();
//		gc.rimuoviProdottoDopoRecensione(this.nome, this.descrizione, this.prezzo, this.categorie);
//	}
}
