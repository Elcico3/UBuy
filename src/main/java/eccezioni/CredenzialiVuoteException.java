package eccezioni;

public class CredenzialiVuoteException extends CredenzialiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CredenzialiVuoteException(){
		
	}
	
	public CredenzialiVuoteException(String m){
		super(m);
	}
	
	public String toString()
	{
		return "Il campo nome è omesso";
	}
}
