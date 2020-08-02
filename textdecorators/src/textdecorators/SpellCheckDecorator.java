package textdecorators;

import textdecorators.util.InputDetails;

public class SpellCheckDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "SPELLCHECK_";
	private final String SUFFIX = "_SPELLCHECK";

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	@Override
	public void processInputDetails() {
		if (id == null) {
			System.out.println("inputString is null");
			System.exit(0);
		}
		String updatedInputString = id.getInputString();

		/*
		 * String[] array = id.getInputString().split(" "); Boolean flag = false;
		 * for(int i = 0; i < array.length; i++) { if(array[i].contains(".")) { array[i]
		 * = array[i].replace(".", ""); flag = true; }
		 * if(id.getMisspledString().contains(array[i])) { updatedInputString += PREFIX
		 * + array[i] + SUFFIX;
		 * 
		 * }else { updatedInputString += array[i] + " "; } if(flag) { updatedInputString
		 * += ". "; flag=false; } else { updatedInputString += " "; } }
		 */

		for (int i = 0; i < id.getMisspledString().size(); i++) {
			updatedInputString = updatedInputString.replaceAll(id.getMisspledString().get(i),
					(PREFIX + id.getMisspledString().get(i) + SUFFIX));

		}
		System.out.println(updatedInputString);
		if (null != atd) {
			atd.processInputDetails();
		}

	}
}
