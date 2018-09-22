package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENDITORE")
public class Venditore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Venditore() {
		// super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venditore")
	private int id;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "password")
	private String password;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_venditore")
	// @IndexColumn(name="id_venditore")
	// @OneToMany (fetch = FetchType.EAGER,mappedBy="venditore",cascade =
	// CascadeType.ALL)
	private List<Prodotto> prodotti;

	@OneToMany//(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name="id_venditore")
	private List<Valutazione> valutazione;
	
	public Venditore(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public void inserisciProdotto(Prodotto prodotto) {
		// TODO Auto-generated method stub
		if (this.prodotti == null)
			this.prodotti = new ArrayList<Prodotto>();
		this.getProdotti().add(prodotto);
	}
	
	public void modificaProdotto(String vecchioNome,Prodotto p){
		for(int i=0;i<prodotti.size();i++){
			if(prodotti.get(i).getNome()==vecchioNome){
				prodotti.remove(i);
				prodotti.add(p);	
			}
		}
	}
	public void eliminaProdotto(String nome){
		for(int i=0;i<prodotti.size();i++){
			if(prodotti.get(i).getNome()==nome){
				prodotti.remove(i);
			}
		}
	}
	private List<Prodotto> getProdotti() {
		// TODO Auto-generated method stub
		return prodotti;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
