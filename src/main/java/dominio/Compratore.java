package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compratore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private List<Prodotto> listaProdottiAcquistati;

	public Compratore(String nome, String cognome, String username, String password,
			List<Prodotto> listaProdottiAcquistati) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setUsername(username);
		this.setPassword(password);
		this.listaProdottiAcquistati = listaProdottiAcquistati;
	}

	public List<Prodotto> getListaProdottiAcquistati() {
		return listaProdottiAcquistati;
	}

	public void setListaProdottiAcquistati(List<Prodotto> listaProdottiAcquistati) {
		this.listaProdottiAcquistati = listaProdottiAcquistati;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void aggiungiProdottoAcquistato(Prodotto p) {
		if (listaProdottiAcquistati == null)
			listaProdottiAcquistati = new ArrayList<Prodotto>();
		listaProdottiAcquistati.add(p);

	}
	public void eliminaProdottoAcquistato(String nomeProdotto, String descrizione, double prezzo,
			String categoria){
		for (int i = 0; i < listaProdottiAcquistati.size(); i++) {
			
			if (listaProdottiAcquistati.get(i).getNome().equals(nomeProdotto) && listaProdottiAcquistati.get(i).getDescrizione().equals(descrizione)
					&& listaProdottiAcquistati.get(i).getPrezzo() == prezzo && listaProdottiAcquistati.get(i).getCategoria().equals(categoria)) {
				listaProdottiAcquistati.remove(i);
			}
			this.setListaProdottiAcquistati(listaProdottiAcquistati);
		}
	}
}
