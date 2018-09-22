package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bean.ProdottoBean;
import bean.ValutazioneBean;
import dominio.Compratore;
import dominio.Prodotto;
import dominio.Valutazione;
import dominio.Venditore;
import eccezioni.CronologiaVuotaException;
import eccezioni.CredenzialiException;
import eccezioni.CredenzialiVuoteException;
import hibernate.FactoryProdotto;
import hibernate.FactoryValutazione;
import hibernate.FactoryVenditore;

public class GestoreCompratore {
	private static GestoreCompratore gestoreCompratore;
	private Compratore accountC;

	private GestoreCompratore() {

	}

	public synchronized static GestoreCompratore avviaControllerGestore() {
		if (gestoreCompratore == null) {
			gestoreCompratore = new GestoreCompratore();
		}
		return gestoreCompratore;
	} 

	public void serializzaCompratore(String nome, String cognome, String username, String password,
			List<Prodotto> listaProdottiAcquistati) throws IOException, CredenzialiVuoteException {
		if(nome.equals("") || cognome.equals("")||username.equals("")||password.equals("")){
			throw new CredenzialiVuoteException();
		}
		Compratore c = new Compratore(nome, cognome, username, password, listaProdottiAcquistati);
		FileOutputStream fileout = new FileOutputStream("src/main/java/file/Compratore.dat");
		ObjectOutputStream out = new ObjectOutputStream(fileout);
		out.writeObject(c);
		out.close();
		fileout.close();
	}

	// public void serializzaCompratore(String nome, String cognome, String
	// username, String password) {
	// Compratore c = new Compratore(nome, cognome, username, password);
	//
	// try {
	// ArrayList<Compratore> listaCompratori = new ArrayList<Compratore>();
	// FileOutputStream fileOut = new
	// FileOutputStream("src/main/java/file/Compratore.dat");
	// ObjectOutputStream out = new ObjectOutputStream(fileOut);
	// // listaCompratori.add(c);
	// // out.writeObject(listaCompratori);
	// // out.close();
	// // fileOut.close();
	// listaCompratori = deserializzaCompratore();
	// if (listaCompratori == null) {
	// listaCompratori = new ArrayList<Compratore>();
	// listaCompratori.add(c);
	// out.writeObject(listaCompratori);
	// } else {
	// listaCompratori.add(c);
	// out.writeObject(listaCompratori);
	// }
	// // listaCompratori.add(c);
	// // out.writeObject(listaCompratori);
	// out.close();
	// fileOut.close();
	// // System.out.printf("Serialized data is saved in
	// // /file/Compratore.dat\n");
	// } catch (IOException i) {
	// i.printStackTrace();
	// }
	//
	// }
	public Compratore deserializzaCompratore() throws IOException, ClassNotFoundException {
		FileInputStream filein = new FileInputStream("src/main/java/file/Compratore.dat");
		ObjectInputStream in = new ObjectInputStream(filein);
		Compratore c = (Compratore) in.readObject();
		in.close();
		filein.close();
		return c;
	}

	public List<ProdottoBean> ottieniProdotti() {
		List<ProdottoBean> lb=new ArrayList<ProdottoBean>();
		List<Prodotto> listaProdotti = FactoryProdotto.ottieniProdottiDisponibili();
		 for(int i=0;i<listaProdotti.size();i++){
		 ProdottoBean pb=new ProdottoBean();
		 pb.setNome(listaProdotti.get(i).getNome());
		 pb.setPrezzo(listaProdotti.get(i).getPrezzo());
		 pb.setCategorie(listaProdotti.get(i).getCategoria());
		 pb.setDescrizione(listaProdotti.get(i).getDescrizione());
		 pb.setPrezzo(listaProdotti.get(i).getPrezzo());
		 lb.add(pb);
		 }
		
		for(int i=0; i<listaProdotti.size();i++){
			
		}
		return lb;
	}
	
	public List<ProdottoBean> ottieniProdottiComprati() {
		List<ProdottoBean> lb=new ArrayList<ProdottoBean>();
		List<Prodotto> prodottiAcquistati= getAccountC().getListaProdottiAcquistati();
		 for(int i=0;i<prodottiAcquistati.size();i++){
		 ProdottoBean pb=new ProdottoBean();
		 pb.setNome(prodottiAcquistati.get(i).getNome());
		 pb.setPrezzo(prodottiAcquistati.get(i).getPrezzo());
		 pb.setCategorie(prodottiAcquistati.get(i).getCategoria());
		 pb.setDescrizione(prodottiAcquistati.get(i).getDescrizione());
		 pb.setPrezzo(prodottiAcquistati.get(i).getPrezzo());
		 pb.setUsernameVenditore(prodottiAcquistati.get(i).getVenditore().getUsername());
		 lb.add(pb);
		 }
		
		return lb;
	}
	

	public boolean convalidaLogin(String username, String password) throws ClassNotFoundException, IOException, CredenzialiException {
		Compratore c = deserializzaCompratore();
		if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
			setAccountC(c);
			return true;
		}
		if(!c.getUsername().equals(username) || !c.getPassword().equals(password)){
			throw new CredenzialiException();
		}
		return false;
	}


	public Compratore getAccountC() {
		return accountC;
	}

	public void setAccountC(Compratore accountC) {
		this.accountC = accountC;
	}
	
	//metodo senza prodotto
	public void compraProdotto(String nomeProdotto,String usernameVenditore) throws IOException, CredenzialiVuoteException {
		Venditore v= FactoryVenditore.ricercaVenditore(usernameVenditore);
		Prodotto p = FactoryProdotto.ricercaProdotto(nomeProdotto, v);
		p.setOpzionato(true);
		getAccountC().aggiungiProdottoAcquistato(p);
		FactoryProdotto.opzionaProdotto(p);
		serializzaCompratore(getAccountC().getNome(), getAccountC().getCognome(), getAccountC().getUsername(),
				getAccountC().getPassword(), getAccountC().getListaProdottiAcquistati());
	}
	
	public void compraProdotto(Prodotto p) throws IOException, CredenzialiVuoteException {
		p.setOpzionato(true);
		getAccountC().aggiungiProdottoAcquistato(p);
		FactoryProdotto.opzionaProdotto(p);
		serializzaCompratore(getAccountC().getNome(), getAccountC().getCognome(), getAccountC().getUsername(),
				getAccountC().getPassword(), getAccountC().getListaProdottiAcquistati());
	}

	public void confermaValutazione(int voto, String commento, String usernameCompratore, String usernameVenditore) {
		Venditore v = FactoryVenditore.ricercaVenditore(usernameVenditore);
		Valutazione val = new Valutazione(v, voto, commento, usernameCompratore);
		FactoryValutazione.salva(val);

	}

	public void rimuoviProdottoDopoRecensione(String nomeProdotto, String descrizione, double prezzo,
			String categoria) throws IOException, CronologiaVuotaException, CredenzialiVuoteException {
		Compratore c = GestoreCompratore.avviaControllerGestore().getAccountC();
		c.eliminaProdottoAcquistato( nomeProdotto,  descrizione,  prezzo,
			 categoria);
		serializzaCompratore(c.getNome(), c.getCognome(), c.getUsername(), c.getPassword(), c.getListaProdottiAcquistati());
	}

	public void compraProdotto(ProdottoBean prodottoBean) throws CredenzialiVuoteException, IOException {
		// TODO Auto-generated method stub
		Prodotto p = FactoryProdotto.ricercaProdotto(prodottoBean.getNome(),prodottoBean.getPrezzo(),prodottoBean.getCategorie(),prodottoBean.getDescrizione());
		p.setOpzionato(true);
		getAccountC().aggiungiProdottoAcquistato(p);
		FactoryProdotto.opzionaProdotto(p);
		serializzaCompratore(getAccountC().getNome(), getAccountC().getCognome(), getAccountC().getUsername(),
				getAccountC().getPassword(), getAccountC().getListaProdottiAcquistati());
	}
	
	public List<ValutazioneBean> ottieniValutazioniCompratoreCorrente(){
		List <Valutazione> lv=FactoryValutazione.ricercaValutazioniCompratore(getAccountC().getUsername());
		List <ValutazioneBean> lvb = new ArrayList<ValutazioneBean>();
		for(int i=0; i<lv.size();i++){
			ValutazioneBean vb = new ValutazioneBean();
			vb.setVoto((lv.get(i).getVoto()));
			vb.setUsernameVenditore(lv.get(i).getVenditore().getUsername());
			vb.setCommento(lv.get(i).getCommento());
			lvb.add(vb); 
		}
		return lvb;
	}
}