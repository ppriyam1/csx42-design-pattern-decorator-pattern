package textdecorators;

import textdecorators.exception.ErrorCode;
import textdecorators.exception.InputDetailsException;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author preetipriyam
 *
 */
public class SentenceDecorator extends AbstractTextDecorator {
	
	private AbstractTextDecorator atd;
	private InputDetails id;
	protected String inputString = "";
	private final String PREFIX = "BEGIN_SENTENCE__";
	private final String SUFFIX = "__END_SENTENCE";
	
	MyLogger LOGGER = MyLogger.getMyLoggerInstance();

	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;

	}

	@Override
	public void processInputDetails() throws InputDetailsException {
		if (id == null) {
			String message = ErrorCode.INVALID_INPUT_EMPTY + ": " + PREFIX+"inputString is null"+SUFFIX;
			LOGGER.writeMessage(message, DebugLevel.EXCEPTION);
			System.exit(0);
		}
		
		String updatedInputString = "";
		String inputString[] = id.getUpdatedInputString().split("\\.");
		for (int i = 0; i < inputString.length-1; i++) {
			updatedInputString += (PREFIX + inputString[i] + SUFFIX + ".");
		}	
		id.update(updatedInputString);
		
		LOGGER.writeMessage(PREFIX+ updatedInputString +SUFFIX, DebugLevel.SENTENCE_DECORATOR);
		
		if (null != atd) {
			atd.processInputDetails();

		}
		
	}

	@Override
	public String toString() {
		return "SentenceDecorator [atd=" + atd + ", id=" + id + ", inputString=" + inputString + "]";
	}
}
