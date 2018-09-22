package controller;

import java.util.ArrayList;
import java.util.List;

import bean.ProdottoBean;
import bean.UtenteBean;
import dominio.Prodotto;
import dominio.Venditore;
import eccezioni.ProdottoPresenteException;
import hibernate.FactoryProdotto;

public class GestoreProdotto {
	private static GestoreProdotto gestoreProdotto;

	public GestoreProdotto() {

	}

	public static GestoreProdotto avviaControllerProdotto() {
		if (gestoreProdotto == null) {
			gestoreProdotto = new GestoreProdotto();
		}
		return gestoreProdotto;
	}

	public void aggiungiProdotto(String nome, double prezzo, String descrizione, String categoria) throws ProdottoPresenteException{//, Venditore v) {
		Prodotto prodotto = new Prodotto(nome, prezzo, descrizione, categoria);//, v);
		GestoreVenditore controllerUtente = GestoreVenditore.avviaControllerUtente();
		prodotto.setVenditore(controllerUtente.getAccountV());
		if (controllerUtente.getAccountV() != null)
			controllerUtente.getAccountV().inserisciProdotto(prodotto);
		FactoryProdotto.Salva(prodotto);
	}

	public List<ProdottoBean> ottieniProdottiBeanVenditore(Venditore v) {
		try {
			List<Prodotto> p = FactoryProdotto.ricercaProdottoPerVenditore(v);
			List<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();
			for (int i = 0; i < p.size(); i++) {
				ProdottoBean pb = new ProdottoBean();
				pb.setNome(p.get(i).getNome());
				pb.setDescrizione(p.get(i).getDescrizione());
				pb.setPrezzo(p.get(i).getPrezzo());
				pb.setCategorie(p.get(i).getCategoria());
				listaBean.add(pb); 
			}
			return listaBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void eliminaProdottoVenditore(String nomeProdotto,UtenteBean ub) {
		//System.out.println("sono in GestoreProdotto: nome del prodotto da cancellare: "+nomeProdotto+"\n");
		//Venditore v=FactoryVenditore.ricercaVenditore(ub.getUsername());
		Venditore v = GestoreVenditore.avviaControllerUtente().getAccountV();
		FactoryProdotto.eliminaProdotto(nomeProdotto,v);
		v.eliminaProdotto(nomeProdotto);
	}
	
	public void modificaProdottoDaBean(String vecchioNome,ProdottoBean nuovoBean,UtenteBean ub) throws ProdottoPresenteException{
		System.out.println("GestreProdotto: modificaProdottoDaBeaan");
		//Venditore v=FactoryVenditore.ricercaVenditore(ub.getUsername());
		Venditore v=GestoreVenditore.avviaControllerUtente().getAccountV();
		FactoryProdotto.modificaProdotto(vecchioNome, nuovoBean.getNome(), nuovoBean.getDescrizione(), nuovoBean.getCategorie(), nuovoBean.getPrezzo(),v);
		Prodotto p=new Prodotto(nuovoBean.getNome(),nuovoBean.getPrezzo(),nuovoBean.getDescrizione(),nuovoBean.getCategorie());
		v.modificaProdotto(vecchioNome, p);
	}
}
