package exception;

public class DateOrderException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new date order exception.
	 */
	public DateOrderException() {
		super();
	}

	/**
	 * Instantiates a new date order exception.
	 *
	 * @param message the message
	 */
	public DateOrderException(String message) {
		super(message);
	}

}
