package DataStructure;

public class SmartBSTException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SmartBSTException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SmartBSTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SmartBSTException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SmartBSTException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SmartBSTException(Throwable cause) {
		super(cause);
	}

}
