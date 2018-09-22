package eccezioni;

public class CredenzialiErrateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CredenzialiErrateException(){
		
	}
	
	public CredenzialiErrateException(String m){
		super(m);
	}
}
