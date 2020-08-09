package textdecorators.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import textdecorators.exception.ErrorCode;
import textdecorators.exception.InputDetailsException;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author preetipriyam
 *
 */
public class InputDetails implements FileDisplayInterface, StdoutDisplayInterface {

	private String inputFileName;
	private String outputFileName;
	private String misspelledFilename;
	private String keywordsFilename;
	private static final String ALPHANUMERIC_PATTERN = "[a-zA-Z0-9\\.,\\s]{0,}";

	private FileProcessor fileProcessor = null;
	protected String inputString = "";
	private String updatedInputString = "";

	private List<String> misspledString;
	private List<String> keywordsString;

	MyLogger LOGGER = MyLogger.getMyLoggerInstance();

	public InputDetails(String inputFileNameIn, String misspelledFilenameIn, String keywordsFilenameIn,
			String outputFileNameIn) {
		this.inputFileName = inputFileNameIn;
		this.outputFileName = outputFileNameIn;
		this.misspelledFilename = misspelledFilenameIn;
		this.keywordsFilename = keywordsFilenameIn;
	}

	/**
	 * Method to process the input files.
	 * 
	 * @throws InputDetailsException
	 */
	public void processInputDetails() throws InputDetailsException {
		try {

			fileProcessor = new FileProcessor(inputFileName);

			String inputInstruction = fileProcessor.poll();

			if (inputInstruction == null || inputInstruction.isEmpty()) {
				LOGGER.writeMessage(ErrorCode.EMPTY_FILR_ERROR + ": " + "Input file is empty", DebugLevel.EXCEPTION);
				System.exit(0);
			}

			isinstructionValid(inputInstruction);

			this.inputString = inputInstruction;

			fileProcessor = new FileProcessor(misspelledFilename);

			String misspledInstruction = fileProcessor.poll();

			if (misspledInstruction == null || misspledInstruction.isEmpty()) {
				LOGGER.writeMessage(ErrorCode.EMPTY_FILR_ERROR + ": " + "Misspelled words file is empty",
						DebugLevel.EXCEPTION);
				System.exit(0);
			}

			this.misspledString = new ArrayList<String>();
			while (misspledInstruction != null) {
				this.misspledString.add(misspledInstruction.toLowerCase());
				misspledInstruction = fileProcessor.poll();
			}

			fileProcessor = new FileProcessor(keywordsFilename);

			String keywordsInstruction = fileProcessor.poll();

			if (keywordsInstruction == null || keywordsInstruction.isEmpty()) {
				LOGGER.writeMessage(ErrorCode.EMPTY_FILR_ERROR + ": " + "Keywords file is empty", DebugLevel.EXCEPTION);
				System.exit(0);
			}

			this.keywordsString = new ArrayList<String>();
			while (keywordsInstruction != null) {
				this.keywordsString.add(keywordsInstruction);
				keywordsInstruction = fileProcessor.poll();
			}
		} catch (InvalidPathException | SecurityException | IOException e) {
			e.printStackTrace();
		}

	}

	public String update() {
		return inputString;
	}

	/**
	 * Method to validate the input instruction.
	 * 
	 * @param instruction
	 * @throws InputDetailsException
	 */
	public void isinstructionValid(String instruction) throws InputDetailsException {

		if (Pattern.matches(ALPHANUMERIC_PATTERN, instruction) == false) {
			String message = ErrorCode.INVALID_INPUT_FORMAT + ": " + "Input String is invalid";
			LOGGER.writeMessage(message, DebugLevel.EXCEPTION);
			System.exit(0);
		}
	}

	public String getUpdatedInputString() throws InputDetailsException {
		if (updatedInputString == "") {
			if (inputString == "") {
				processInputDetails();
			}
			return inputString;
		}
		return updatedInputString;
	}

	@Override
	public void writeToFile() throws InputDetailsException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.write(updatedInputString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new InputDetailsException(e.getMessage());
		}

	}

	@Override
	public void printToStdout() {
		System.out.println(updatedInputString);
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public FileProcessor getFileProcessor() {
		return fileProcessor;
	}

	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	public List<String> getMisspledString() {
		return misspledString;
	}

	public void setMisspledString(List<String> misspledString) {
		this.misspledString = misspledString;
	}

	public List<String> getKeywordsString() {
		return keywordsString;
	}

	public void setKeywordsString(List<String> keywordsString) {
		this.keywordsString = keywordsString;
	}

	public void update(String updatedInputStringIn) {
		updatedInputString = updatedInputStringIn;
	}

	@Override
	public String toString() {
		return "InputDetails [inputFileName=" + inputFileName + ", outputFileName=" + outputFileName
				+ ", misspelledFilename=" + misspelledFilename + ", keywordsFilename=" + keywordsFilename
				+ ", inputString=" + inputString + ", updatedInputString=" + updatedInputString + ", misspledString="
				+ misspledString + ", keywordsString=" + keywordsString + "]";
	}

}
