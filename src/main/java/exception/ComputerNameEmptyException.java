package exception;

public class ComputerNameEmptyException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new computer name empty exception.
	 */
	public ComputerNameEmptyException() {
		super();
	}

	/**
	 * Instantiates a new computer name empty exception.
	 *
	 * @param message the message
	 */
	public ComputerNameEmptyException(String message) {
		super(message);
	}

}
