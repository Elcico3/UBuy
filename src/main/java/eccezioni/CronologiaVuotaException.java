package eccezioni;

public class CronologiaVuotaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CronologiaVuotaException()
	{
	}
	
	public CronologiaVuotaException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "Non ci sono prodotti da recensire!";
	}
}
