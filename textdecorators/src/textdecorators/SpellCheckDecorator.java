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
public class SpellCheckDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "SPELLCHECK_";
	private final String SUFFIX = "_SPELLCHECK";

	MyLogger LOGGER = MyLogger.getMyLoggerInstance();

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	@Override
	public void processInputDetails() throws InputDetailsException {
		if (id == null) {
			String message = ErrorCode.INVALID_INPUT_EMPTY + ": " + PREFIX + "inputString is null" + SUFFIX;
			LOGGER.writeMessage(message, DebugLevel.EXCEPTION);
			System.exit(0);
		}

		String updatedInputString = "";

		String[] array = id.getUpdatedInputString().split(" ");
		Boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(".")) {
				array[i] = array[i].replace(".", "");
				flag = true;
			}
			if (id.getMisspledString().contains(array[i].toLowerCase()))
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

		LOGGER.writeMessage(PREFIX + updatedInputString + SUFFIX, DebugLevel.SPELL_CHECK_DECORATOR);
		if (null != atd) {
			atd.processInputDetails();
		}

	}

	@Override
	public String toString() {
		return "SpellCheckDecorator [atd=" + atd + ", id=" + id + "]";
	}
}
