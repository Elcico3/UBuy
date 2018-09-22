package eccezioni;

public class VenditoreNonTrovatoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VenditoreNonTrovatoException(){
		
	}
	public  VenditoreNonTrovatoException(String m){
		super(m);
	}

}
