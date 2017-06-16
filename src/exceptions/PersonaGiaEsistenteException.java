package exceptions;

public class PersonaGiaEsistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
    public PersonaGiaEsistenteException() {}

    //Constructor that accepts a message
    public PersonaGiaEsistenteException(String message)
    {
       super(message);
    }
}
