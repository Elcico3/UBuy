package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import dominio.Prodotto;
import dominio.Venditore;
import eccezioni.ProdottoPresenteException;
import eccezioni.UsernameException;
import hibernate.FactoryProdotto;
import hibernate.FactoryVenditore;

public class BeforeAfterDatabaseTest extends DatabaseTest  {
	
	@BeforeClass
	public static void configurazioneSistema() throws ProdottoPresenteException, UsernameException{
		FactoryVenditore.pulisciDB();
		Venditore dante= new Venditore("dante","Alighieri","danteali","Beatrice");
		Prodotto divinaCommedia=new Prodotto("Divina Commedia",100,"Il mio nuovo libro","Libri/Cd",dante);
		System.out.println("Inizio settaggio entita da testare\n");
		FactoryVenditore.salva(dante);
		FactoryProdotto.Salva(divinaCommedia);
	}
	
	@AfterClass 
	public static void ripristinoSistema(){
		FactoryVenditore.pulisciDB();
	}
	
	
}
