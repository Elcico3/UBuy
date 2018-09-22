package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VALUTAZIONE")
public class Valutazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "voto")
	private int voto;

	@Column(name = "commento")
	private String commento;

	@ManyToOne
	@JoinColumn(name = "id_venditore")
	private Venditore venditore;

	@Column(name = "compratore")
	private String usernameCompratore;
	
	public Valutazione(){
		
	}

	public Valutazione(Venditore v, int voto) {
		this.setVenditore(v);
		this.setVoto(voto);
	}

	public Valutazione(Venditore v, int voto, String commento, String usernameCompratore) {
		this.setVenditore(v);
		this.setVoto(voto);
		this.setCommento(commento);
		this.setUsernameCompratore(usernameCompratore);
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Venditore getVenditore() {
		return venditore;
	}

	public void setVenditore(Venditore venditore) {
		this.venditore = venditore;
	}

	public String getUsernameCompratore() {
		return usernameCompratore;
	}

	public void setUsernameCompratore(String usernameCompratore) {
		this.usernameCompratore = usernameCompratore;
	}
	

}
