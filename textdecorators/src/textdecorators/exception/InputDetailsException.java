package textdecorators.exception;

import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class InputDetailsException extends TextDecoratorsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8061834051734106230L;

	private ErrorCode code;

	/**
	 * @param message
	 */
	public InputDetailsException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param errorCode
	 */
	public InputDetailsException(ErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @return String: errorCode
	 */
	public String getErrorCode() {
		return this.code.toString();
	}

	/**
	 * @param exception
	 * @throws Exception
	 */
	public static void processException(InputDetailsException exception) throws Exception {
		MyLogger.getMyLoggerInstance().writeMessage("Something went wrong in InputDetails class", DebugLevel.EXCEPTION);
		throw new InputDetailsException(exception.getErrorCode() + ": " + exception.getMessage().getClass().getName());
	}
}
