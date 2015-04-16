//Cette exception est lancée par la classe Point lorsque les coordonnées entrées se trouvent à l'extérieur de la carte
public class CoordonneesIncorrectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordonneesIncorrectException() {
		super();
	}

	public CoordonneesIncorrectException(String message) {
		super(message);
	}

	public CoordonneesIncorrectException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoordonneesIncorrectException(Throwable cause) {
		super(cause);
	}

}
