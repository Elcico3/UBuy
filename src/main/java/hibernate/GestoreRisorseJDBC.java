package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dominio.Prodotto;
import dominio.Valutazione;
import dominio.Venditore;

public class GestoreRisorseJDBC {

	private static Configuration configurazione;
	private static ServiceRegistry registroServizio;
	private static SessionFactory sessioneFactory;

	public static void initHibernate() {
		// load hibernate configuration
		//InputStream in = Hibernate.class.getResourceAsStream("/hibernate.cfg.xml");
		configurazione = new Configuration();//.addInputStream(in).configure();
		configurazione.configure();
		

		// use JNDI to bind Hibernate configuration and datasource
		registroServizio = new StandardServiceRegistryBuilder().applySettings(
				configurazione.getProperties()).build();
		configurazione.addAnnotatedClass(Prodotto.class);
		configurazione.addAnnotatedClass(Venditore.class);
		configurazione.addAnnotatedClass(Valutazione.class);

		/*
		 * Retrieve the one session factory that will manage sessions,
		 * connections and transaction
		 */
		sessioneFactory = configurazione.buildSessionFactory(registroServizio);
	}

	public static Session getSessioneFactory() {
		if (sessioneFactory == null)
			initHibernate();
		return sessioneFactory.openSession();
	}

	public static void shutdown() {
		sessioneFactory.close();
	}

}