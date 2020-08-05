package textdecorators;

import textdecorators.util.InputDetails;

public class SentenceDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	protected String inputString = "";
	private final String PREFIX = "BEGIN_SENTENCE__";
	private final String SUFFIX = "__END_SENTENCE";

	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;

	}

	@Override
	public void processInputDetails() {
		if (id == null) {
			System.out.println("inputString is null");
			System.exit(0);
		}
		
		String updatedInputString = "";
		String inputString[] = id.getUpdatedInputString().split("\\. |  \\.");
		for (int i = 0; i < inputString.length; i++) {
			updatedInputString += (PREFIX + inputString[i] + SUFFIX + ".");
		}	
		id.update(updatedInputString);
		
		System.out.println(updatedInputString);		
		//id.setInputString(updatedInputString);
		
		if (null != atd) {
			atd.processInputDetails();

		}
		
	}
	
	public void updateString() {
		
	}
}
