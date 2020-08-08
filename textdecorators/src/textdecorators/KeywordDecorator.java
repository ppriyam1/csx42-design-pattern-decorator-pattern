package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class KeywordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "KEYWORD_";
	private final String SUFFIX = "_KEYWORD";
	
	MyLogger LOGGER = MyLogger.getMyLoggerInstance();

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	@Override
	public void processInputDetails() {
		if (id == null) {
			LOGGER.writeMessage(PREFIX+"inputString is null"+SUFFIX, DebugLevel.KEYWORD_DECORATOR);
			System.exit(0);
		}
		
		String updatedInputString = "";
		String[] array =id.getUpdatedInputString().split(" ");
		Boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(".")) {
				array[i] = array[i].replace(".", "");
				flag = true;
			}
			if (id.getKeywordsString().contains(array[i].toLowerCase()))
				updatedInputString += PREFIX + array[i] + SUFFIX;

			else
				updatedInputString += array[i];

			if (flag) {
				updatedInputString += ". ";
				flag = false;
			} else
				updatedInputString += " ";

		}
		id.update(updatedInputString);
		
		LOGGER.writeMessage(PREFIX+ updatedInputString +SUFFIX, DebugLevel.KEYWORD_DECORATOR);
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	@Override
	public String toString() {
		return "KeywordDecorator [atd=" + atd + ", id=" + id + "]";
	}
}
