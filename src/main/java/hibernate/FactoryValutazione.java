package hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dominio.Valutazione;

public class FactoryValutazione {
	public static void salva(Valutazione val) {

		Session s = GestoreRisorseJDBC.getSessioneFactory();

		s.beginTransaction();

		s.saveOrUpdate(val);

		s.getTransaction().commit();

		// s.close();
	}



	public static List<Valutazione> ricercaValutazioniCompratore(String usernameCompratore) {
		Session s = GestoreRisorseJDBC.getSessioneFactory();
		Query q = s.createQuery("from Valutazione where compratore=:usernameCompratore");
		q.setParameter("usernameCompratore", usernameCompratore);
		@SuppressWarnings("unchecked")
		List<Valutazione> lvb = (List<Valutazione>) q.list();
		return lvb;
	}
}