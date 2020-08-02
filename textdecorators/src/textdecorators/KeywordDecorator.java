package textdecorators;

import textdecorators.util.InputDetails;

public class KeywordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "KEYWORD_";
	private final String SUFFIX = "_KEYWORD";

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	@Override
	public void processInputDetails() {
		if (id == null) {
			System.out.println("inputString is null");
			System.exit(0);
		}
		String updatedInputString = "";
		String[] array = id.getInputString().split(" ");
		Boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(".")) {
				array[i] = array[i].replace(".", "");
				flag = true;
			}
			if (id.getKeywordsString().contains(array[i].toLowerCase()))
				updatedInputString += PREFIX + array[i] + SUFFIX;

			else
				updatedInputString += array[i];

			if (flag) {
				updatedInputString += ". ";
				flag = false;
			} else
				updatedInputString += " ";

		}
		
		System.out.println(updatedInputString);
		if (null != atd) {
			atd.processInputDetails();
		}
	}
}
