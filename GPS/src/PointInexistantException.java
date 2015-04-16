//Cette exception est lanc�e par la classe PanelItineraire lorsque le point entr� n'existe pas
public class PointInexistantException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PointInexistantException() {
		super();
	}

	public PointInexistantException(String message) {
		super(message);
	}

	public PointInexistantException(String message, Throwable cause) {
		super(message, cause);
	}

	public PointInexistantException(Throwable cause) {
		super(cause);
	}

}