package textdecorators;

import textdecorators.exception.InputDetailsException;

public abstract class AbstractTextDecorator {

	/**
	 * Method to process the input files.
	 * @throws InputDetailsException 
	 */
	public abstract void processInputDetails() throws InputDetailsException;
}
