//Cette exception est lancée par la classe Reseau s’il est impossible de trouver un chemin allant du point A au point B
public class CheminImpossibleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheminImpossibleException() {
		super();
	}

	public CheminImpossibleException(String message) {
		super(message);
	}

	public CheminImpossibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheminImpossibleException(Throwable cause) {
		super(cause);
	}

}
