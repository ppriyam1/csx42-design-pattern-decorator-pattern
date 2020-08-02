package textdecorators.driver;

import textdecorators.util.InputDetails;
import textdecorators.util.FileDisplayInterface;

import textdecorators.KeywordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.AbstractTextDecorator;
import textdecorators.MostFrequentWordDecorator;

public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${output}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			System.exit(0);

			// (args[2].equals("${keywords}")) ||
			// (args[4].contains("${debug}"))

		}

		final String inputFilename = "./" + args[0];
		final String misspelledFilename = "./" + args[1];
		final String outputFilename = "./" + args[2];

		InputDetails inputD = new InputDetails(inputFilename, misspelledFilename, outputFilename);
		inputD.processInputDetails();
		AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		//sentenceDecorator.processInputDetails();
		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
		//spellCheckDecorator.processInputDetails();
		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
		AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
		mostFreqWordDecorator.processInputDetails();
		// mostFrequentWordDecorator.processInputDetails();

		// ((FileDisplayInterface) inputD).writeToFile();

	}
}
