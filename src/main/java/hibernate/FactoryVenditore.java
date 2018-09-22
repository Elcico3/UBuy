package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dominio.Venditore;
import eccezioni.CredenzialiErrateException;
import eccezioni.UsernameException;
import eccezioni.VenditoreNonTrovatoException;

public class FactoryVenditore {

	
	public static void salva(Venditore a) throws UsernameException {
		
		Session s = GestoreRisorseJDBC.getSessioneFactory();
	
		s.beginTransaction();

		Query q = s.createQuery("from Venditore where username=:username");
		q.setParameter("username", a.getUsername());
		if (q.uniqueResult() != null) {
			throw new UsernameException("Questo username è gia stato scelto!");
		}

		// within the transaction, save the event
		s.saveOrUpdate(a);

		// commit the current transaction of the session
		s.getTransaction().commit();

		s.flush();

		// close session
		// s.close();
	}

	public static void aggiornaVenditore(Venditore daAggiornare) {
		Session s = GestoreRisorseJDBC.getSessioneFactory();
		s.beginTransaction();
		s.update(daAggiornare);
		s.getTransaction().commit();
		// s.close();
	}

	public static void eliminaVenditore(Venditore daEliminare) {
		Session s = GestoreRisorseJDBC.getSessioneFactory();
		s.beginTransaction();
		s.delete(daEliminare);
		s.getTransaction().commit();
		// s.close();
	}

	public static Venditore autentica(String username, String password) throws CredenzialiErrateException {
		try {

			Session session = GestoreRisorseJDBC.getSessioneFactory();

			Query query = session.createQuery("from Venditore where username=:username");

			query.setParameter("username", username);

			Venditore a = (Venditore) query.uniqueResult();
			if (a == null) {
				throw new CredenzialiErrateException("Credenziali Errate");
			}
			if (a != null) {
				

				if (password.equals(a.getPassword())) {
					return a;
				} else {
					throw new CredenzialiErrateException("Credenziali Errate");
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Venditore ricercaVenditore(String username) {
		try {
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			Query query = session.createQuery("from Venditore where username=:username");
			query.setParameter("username", username);
			Venditore v = (Venditore) query.uniqueResult();
			if (v != null) {
				//session.close();
				return v;
			} else {
				//session.close();
				throw new VenditoreNonTrovatoException("L'utente non esiste");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int reicercaIdVenditore(String username) {
		try {
			Session session = GestoreRisorseJDBC.getSessioneFactory();
			Query query = session.createQuery("Select id_venditore from Venditore where username=:username");
			query.setParameter("username", username);
			int i = (int) query.uniqueResult();
			session.close();
			return i;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void pulisciDB() {
		Session s = GestoreRisorseJDBC.getSessioneFactory();
		Query q = s.createQuery("Delete from Prodotto");
		q.executeUpdate();
		q = s.createQuery("Delete from Valutazione");
		q.executeUpdate();
		q = s.createQuery("Delete from Venditore");
		q.executeUpdate();
	}
}