package jso.screenreaders;


public abstract class Screenreader {

	private String name;
	protected final String architecture;

	public Screenreader(String name) {
		this.name = name;
		this.architecture = System.getProperty("sun.arch.data.model");
	}  //end constructor

	public abstract boolean isRunning();

	public abstract void speak(String text);

	public abstract void speak(String text, boolean interrupt);

	public abstract void silence();

	public void braille(String text) {}

	public void output(String text, boolean interrupt) {
		this.speak(text, interrupt);
		this.braille(text);
	}  //end method

	public void output(String text) {
		this.speak(text);
		this.braille(text);
	}  //end method

	public String getName() {
		return this.name;
	}  //end method

}  //end class
