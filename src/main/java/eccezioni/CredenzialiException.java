package eccezioni;

public class CredenzialiException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public CredenzialiException()
	{
	}
	
	public CredenzialiException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "Credenziali errate, riprova";
	}
}
