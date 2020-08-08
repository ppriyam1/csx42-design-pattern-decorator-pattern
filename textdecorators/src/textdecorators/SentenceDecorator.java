package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

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
	public void processInputDetails() {
		if (id == null) {
			LOGGER.writeMessage(PREFIX+"inputString is null"+SUFFIX, DebugLevel.SENTENCE_DECORATOR);
			System.exit(0);
		}
		
		String updatedInputString = "";
		String inputString[] = id.getUpdatedInputString().split("\\. |  \\.");
		for (int i = 0; i < inputString.length; i++) {
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
