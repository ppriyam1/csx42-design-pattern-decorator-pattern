package textdecorators.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {

	public static enum DebugLevel {
		MOST_FREQUENT_WORDS_DECORATOR, KEYWORD_DECORATOR, SPELL_CHECK_DECORATOR, SENTENCE_DECORATOR, INPUT_DETAILS,
		EXCEPTION, NONE
	};

	private static volatile MyLogger myLoggerInstance;
	private static DebugLevel debugLevel;
	
	File file = new File("./log.txt");

	private MyLogger() {
	}

	public static MyLogger getMyLoggerInstance() {
		if (myLoggerInstance == null) {
			synchronized (MyLogger.class) {
				if (myLoggerInstance == null) {
					myLoggerInstance = new MyLogger();
				}
			}
		}

		return myLoggerInstance;
	}

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 5:
			debugLevel = DebugLevel.INPUT_DETAILS;
			break;
		case 4:
			debugLevel = DebugLevel.MOST_FREQUENT_WORDS_DECORATOR;
			break;
		case 3:
			debugLevel = DebugLevel.KEYWORD_DECORATOR;
			break;
		case 2:
			debugLevel = DebugLevel.SPELL_CHECK_DECORATOR;
			break;
		case 1:
			debugLevel = DebugLevel.SENTENCE_DECORATOR;
			break;
		case 0:
			debugLevel = DebugLevel.EXCEPTION;
			break;
		default:
			debugLevel = DebugLevel.NONE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public void writeMessage(String message, DebugLevel levelIn) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			if (levelIn == debugLevel) {
				writer.write(message);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}