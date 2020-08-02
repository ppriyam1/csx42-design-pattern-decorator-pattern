package textdecorators.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputDetails {

	private String inputFileName;
	private String outputFileName;
	private String misspelledFilename;
	private String keywordsFilename;
	private static final String ALPHANUMERIC_PATTERN = "[a-zA-Z0-9\\.,\\s]{0,}";

	private FileProcessor fileProcessor = null;
	private String inputString = "";
	private List<String> misspledString;
	private List<String> keywordsString;

	public InputDetails(String inputFileNameIn, String misspelledFilenameIn, String keywordsFilenameIn,
			String outputFileNameIn) {
		this.inputFileName = inputFileNameIn;
		this.outputFileName = outputFileNameIn;
		this.misspelledFilename = misspelledFilenameIn;
		this.keywordsFilename = keywordsFilenameIn;
	}

	public void processInputDetails() {
		try {

			fileProcessor = new FileProcessor(inputFileName);

			String inputInstruction = fileProcessor.poll();
			// Method to validate the input string
			isinstructionValid(inputInstruction);
			this.inputString = inputInstruction;

			// System.out.println(inputString);
			fileProcessor = new FileProcessor(misspelledFilename);

			String misspledInstruction = fileProcessor.poll();
			this.misspledString = new ArrayList<String>();
			while (misspledInstruction != null) {
				this.misspledString.add(misspledInstruction.toLowerCase());
				misspledInstruction = fileProcessor.poll();
			}

			fileProcessor = new FileProcessor(keywordsFilename);
			String keywordsInstruction = fileProcessor.poll();
			this.keywordsString = new ArrayList<String>();
			while (keywordsInstruction != null) {
				this.keywordsString.add(keywordsInstruction);
				keywordsInstruction = fileProcessor.poll();
			}

		} catch (InvalidPathException | SecurityException | IOException e) {
			e.printStackTrace();
		}

	}

	public void isinstructionValid(String instruction) {

		if (Pattern.matches(ALPHANUMERIC_PATTERN, instruction) == false) {
			// TODO throw exception
			System.out.println("Input Stirng is invalid");
		}

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

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String arrayOfInputString) {
		this.inputString = arrayOfInputString;
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
}
