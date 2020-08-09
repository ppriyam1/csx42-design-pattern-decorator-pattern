package textdecorators.exception;

import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author preetipriyam
 *
 */
public class TextDecoratorsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2801733468074092041L;
	private ErrorCode code;

	/**
	 * @param message
	 */
	public TextDecoratorsException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param errorCode
	 */
	public TextDecoratorsException(ErrorCode code, String message) {
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
	 */
	public static void processException(TextDecoratorsException exception) {

		String exceptionMessage = exception.getErrorCode() + ": " + exception.getMessage().getClass().getName();

		MyLogger.getMyLoggerInstance().writeMessage(exceptionMessage, DebugLevel.EXCEPTION);
	}

}
