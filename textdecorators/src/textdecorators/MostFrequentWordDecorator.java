package textdecorators;

import textdecorators.util.InputDetails;

public class MostFrequentWordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String PREFIX = "MOST_FREQUENT_";
	private final String SUFFIX = "_MOST_FREQUENT";

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
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
		String inputString[] = id.getInputString().split("([,.\\s]+)");
		
		//System.out.println(updatedInputString);
		//System.out.println(mostFrequentCount);
		if (null != atd) {
			atd.processInputDetails();
		}

	}
}
