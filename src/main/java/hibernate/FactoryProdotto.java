package hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import dominio.Prodotto;
import dominio.Venditore;
import eccezioni.ProdottoPresenteException;

//classe che inserisce prodotto nel database
public class FactoryProdotto {
	public static void Salva(Prodotto p) throws ProdottoPresenteException {
		try {
			Session s = GestoreRisorseJDBC.getSessioneFactory();
			s.beginTransaction();
			Query q = s.createQuery("from Prodotto where venditore=:venditore and nome=:nome");
			q.setParameter("venditore", p.getVenditore());
			q.setParameter("nome", p.getNome());
			if (q.uniqueResult() != null) {
				throw new ProdottoPresenteException("Esiste già un prodotto messo in vendita da te con questo nome");
			}
			s.saveOrUpdate(p);
			s.getTransaction().commit();
			s.close();
		}
		
		catch (ProdottoPresenteException pa) {
			throw new ProdottoPresenteException("Esiste già un prodotto messo in vendita da te con questo nome");
		}

	}

	@SuppressWarnings("unchecked")
	public static List<Prodotto> ricercaProdottoPerVenditore(Venditore v) {
		try {
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			Query query = session.createQuery("from Prodotto where id_venditore=:venditore");
			query.setParameter("venditore", v);
			List<Prodotto> p = (List<Prodotto>) query.list();
			session.close();
			return p;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public static void eliminaProdotto(String nomeProdotto, Venditore v) {
		try {
			// System.out.println("sono in factoryProdotto nome prodotto da
			// cancellare: " + nomeProdotto + "\n");
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			session.beginTransaction();
			//System.out.println("sono in factoryProdotto, inizio la ricerca del Prodotto da cancellare\n");
			Prodotto p = ricercaProdotto(nomeProdotto, v); 
			session.delete(p);
			//System.out.println("sono in factoryProdotto--Ho cancellato il Prodotto");
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static Prodotto ricercaProdotto(String nomeProdotto, Venditore v) {
		try {
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			session.beginTransaction();
			Query q = session.createQuery("from Prodotto where nome=:nomeProdotto and id_venditore=:venditore");
			q.setParameter("nomeProdotto", nomeProdotto);
			q.setParameter("venditore", v);
			Prodotto p = (Prodotto) q.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void modificaProdotto(String vecchioNome, String nome, String descrizione, String categoria,
			double prezzo, Venditore v) throws ProdottoPresenteException {

		// System.out.println("FactoryProdotto: modificaProdotto");
		Session session = GestoreRisorseJDBC.getSessioneFactory();
		session.beginTransaction();
		Query q = session.createQuery("from Prodotto where nome=:nome and venditore=:venditore");
		q.setParameter("nome", vecchioNome);
		q.setParameter("venditore", v);
//		if (q.uniqueResult() != null && nome!=vecchioNome) {
//			throw new ProdottoPresenteException("Hai gia un altro prodotto in vendita con questo nome");
//		}
		Prodotto p = ricercaProdotto(vecchioNome, v);
		session.close();
		session = GestoreRisorseJDBC.getSessioneFactory();
		session.beginTransaction();
		p.setNome(nome);
		p.setDescrizione(descrizione);
		p.setCategoria(categoria);
		p.setPrezzo(prezzo);
		session.saveOrUpdate(p);
		session.getTransaction().commit();
		session.close();

	}

	public static List<Prodotto> ottieniProdottiDisponibili() {
		try {
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			session.beginTransaction();
			Query q = session.createQuery("from Prodotto where opzionato=:condizione");
			q.setParameter("condizione", false);
			@SuppressWarnings("unchecked")
			List<Prodotto> prodotti = (List<Prodotto>) q.list();
			return prodotti;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void opzionaProdotto(Prodotto p) {
		// System.out.println("inizio opzionaProdotto\n");
		Session session = GestoreRisorseJDBC.getSessioneFactory();
		session.beginTransaction();
		Query q = session.createQuery(
				"from Prodotto where nome=:nome and categoria=:categoria and descrizione=:descrizione and prezzo=:prezzo");
		q.setParameter("nome", p.getNome());
		q.setParameter("categoria", p.getCategoria());
		q.setParameter("descrizione", p.getDescrizione());
		q.setParameter("prezzo", p.getPrezzo());
		Prodotto l = (Prodotto) q.uniqueResult();
		// System.out.println("sono in FactoryProdotto.opzionaProdotto\nProdotto
		// da opzionare: "+l.getNome());
		l.setOpzionato(true);
		session.update(l);
		session.getTransaction().commit();
		//session.close();
	}
	
	public static Prodotto ricercaProdotto(String nome, double prezzo, String categoria, String descrizione){
		Session session = GestoreRisorseJDBC.getSessioneFactory();
		session.beginTransaction();
		Query q = session.createQuery(
				"from Prodotto where nome=:nome and categoria=:categoria and descrizione=:descrizione and prezzo=:prezzo");
		q.setParameter("nome", nome);
		q.setParameter("categoria", categoria);
		q.setParameter("descrizione", descrizione);
		q.setParameter("prezzo", prezzo);
		Prodotto l = (Prodotto) q.uniqueResult();
		// System.out.println("sono in FactoryProdotto.opzionaProdotto\nProdotto
		// da opzionare: "+l.getNome());
		//l.setOpzionato(true);
		//session.update(l);
		//session.getTransaction().commit();
		return l;
	}
}
