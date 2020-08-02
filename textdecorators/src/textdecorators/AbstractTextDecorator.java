package textdecorators;

public abstract class AbstractTextDecorator {
	private String inputString = "";
	
	public abstract void processInputDetails();
	
	public String getInputString() {
		return inputString;
	}
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
}
