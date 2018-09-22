package test;

import static org.junit.Assert.*;

import org.junit.Test;
import dominio.Prodotto;
import dominio.Venditore;
import eccezioni.ProdottoPresenteException;
import eccezioni.UsernameException;
import hibernate.FactoryProdotto;
import hibernate.FactoryVenditore;

public class DatabaseTest {
	
	public DatabaseTest() {
		// TODO Auto-generated constructor stub
	}


	@Test
	public void databaseTest() throws UsernameException, ProdottoPresenteException {
		
		
		Venditore v= FactoryVenditore.ricercaVenditore("danteali");
		System.out.println(v);
		Prodotto q= FactoryProdotto.ricercaProdotto("Divina Commedia", v);
		
		assertEquals("Errore","Divina Commedia", q.getNome());
		assertEquals("Errore","Libri/Cd",q.getCategoria());
		assertEquals("Errore", "Il mio nuovo libro",q.getDescrizione());
		

	}
	@Test
	public void dbNoProduct(){
		Venditore v= FactoryVenditore.ricercaVenditore("danteali");
		System.out.println(v);
		Prodotto q= FactoryProdotto.ricercaProdotto("I promessi sposi", v);
		
		
		assertNull(q);
	}

}
