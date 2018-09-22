
package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

/**
 * 
 */
@Entity
@Table(name = "PRODOTTO")
public class Prodotto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "prezzo")
	private double prezzo;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "categoria")
	private String categoria;

	@ManyToOne()
	@JoinColumn(name = "id_venditore")//,insertable=false,updatable=false,nullable=false)
	private Venditore venditore;
	
	@Column(name="opzionato")
	private boolean opzionato = false;
	
	
	public Prodotto(){
		
	}

	public Prodotto(String nome, double prezzo, String descrizione, String categoria,Venditore venditore) {
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setDescrizione(descrizione);
		this.setCategoria(categoria);
		this.setVenditore(venditore);
	}

	public Prodotto(String nome, double prezzo, String descrizione, String categoria) {
		// TODO Auto-generated constructor stub
		this.nome=nome;
		this.prezzo=prezzo;
		this.descrizione=descrizione;
		this.categoria=categoria;
	}

	public Venditore getVenditore() {
		return venditore;
	}

	public void setVenditore(Venditore venditore) {
		this.venditore = venditore;
	}

	/*
	 * public void setVenditore(Venditore venditore) { this.venditore=venditore;
	 * } public Venditore getVenditore() { return this.venditore; }
	 */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setOpzionato(boolean b){
		this.opzionato=b;
	}
	public boolean getOpzionato(){
		return this.opzionato;
	}
}