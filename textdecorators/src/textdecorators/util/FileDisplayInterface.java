package textdecorators.util;

import textdecorators.exception.InputDetailsException;

public interface FileDisplayInterface {

	/**
	 * Method to write the output to the file.
	 * 
	 * @throws InputDetailsException
	 */
	public void writeToFile() throws InputDetailsException;

}
