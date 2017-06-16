package exceptions;

public class PersonaNonEsistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
    public PersonaNonEsistenteException() {}

    //Constructor that accepts a message
    public PersonaNonEsistenteException(String message)
    {
       super(message);
    }
}
