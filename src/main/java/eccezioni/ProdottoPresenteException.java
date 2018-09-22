package eccezioni;

public class ProdottoPresenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdottoPresenteException(){
		
	}
	
	public ProdottoPresenteException(String m){
		super(m);
	}
}
