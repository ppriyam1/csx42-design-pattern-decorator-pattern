package textdecorators.driver;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.exception.InputDetailsException;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;
import textdecorators.util.StdoutDisplayInterface;

/**
 * @author preetipriyam
 *
 */
public class Driver {

	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}"))
				|| (args[2].equals("${keywords}")) || (args[3].equals("${output}")) || (args[4].contains("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);

		}

		MyLogger LOGGER = MyLogger.getMyLoggerInstance();

		final String inputFilename = "./" + args[0];
		final String misspelledFilename = "./" + args[1];
		final String keywordsFilename = "./" + args[2];
		final String outputFilename = "./" + args[3];

		if (inputFilename.equals(misspelledFilename) || misspelledFilename.equals(keywordsFilename)
				|| inputFilename.equals(keywordsFilename)) {
			throw new RuntimeException("inputFiles cannot be same");
		}

		String debugLevel = args[4];
		MyLogger.setDebugValue(Integer.parseInt(debugLevel));

		InputDetails inputD = new InputDetails(inputFilename, misspelledFilename, keywordsFilename, outputFilename);
		AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
		AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

		try {
			mostFreqWordDecorator.processInputDetails();
		} catch (InputDetailsException e) {
			e.printStackTrace();
			LOGGER.writeMessage(e.getMessage(), DebugLevel.EXCEPTION);
		}

		try {
			((FileDisplayInterface) inputD).writeToFile();
		} catch (InputDetailsException e) {
			e.printStackTrace();
			LOGGER.writeMessage(e.getMessage(), DebugLevel.EXCEPTION);
		}
		((StdoutDisplayInterface) inputD).printToStdout();

	}
}
