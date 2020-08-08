package textdecorators;

import java.util.HashMap;
import java.util.Map;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class MostFrequentWordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "MOST_FREQUENT_";
	private final String SUFFIX = "_MOST_FREQUENT";
	
	MyLogger LOGGER = MyLogger.getMyLoggerInstance();

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	@Override
	public void processInputDetails() {
		if (id == null) {
			LOGGER.writeMessage(PREFIX+"inputString is null"+SUFFIX, DebugLevel.MOST_FREQUENT_WORDS_DECORATOR);
			System.exit(0);
		}
		String mostFrequentWord = "";
		String updatedInputString = "";
		int mostFrequentWordCount = 0;
		
		String inputString[] = id.getUpdatedInputString().split(" ");
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i = 0; i < inputString.length; i++) {
			String key = inputString[i].toLowerCase().replace(".", "");
			map.put(key, map.getOrDefault(key, 0) + 1);
			if(mostFrequentWordCount < map.get(key)) {
				mostFrequentWordCount = map.get(key);
				mostFrequentWord = inputString[i];
			}
		}
		Boolean flag = false;
		for (int i = 0; i < inputString.length; i++) {
			if (inputString[i].contains(".")) {
				inputString[i] = inputString[i].replace(".", "");
				flag = true;
			}
			if (mostFrequentWord.equalsIgnoreCase(inputString[i]))
				updatedInputString += PREFIX + inputString[i] + SUFFIX;

			else
				updatedInputString += inputString[i];

			if (flag) {
				updatedInputString += ". ";
				flag = false;
			} else
				updatedInputString += " ";

		}	
		LOGGER.writeMessage(PREFIX+ updatedInputString +SUFFIX, DebugLevel.MOST_FREQUENT_WORDS_DECORATOR);
		
		id.update(updatedInputString);
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	@Override
	public String toString() {
		return "MostFrequentWordDecorator [atd=" + atd + ", id=" + id + "]";
	}
}
