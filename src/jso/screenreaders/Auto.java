package jso.screenreaders;


public class Auto {

	private Screenreader[] screenreaders;

	public Auto() {
		this.screenreaders = new Screenreader[]{new JAWS(), new NVDA(), new SAPI()};
	}//end constructor

	private Screenreader getRunningScreenreader() {
		for (Screenreader s : this.screenreaders) {
			if (s.isRunning()) {
				return s;
			}//end if
		}//end for loop

		return null;
	}//end method

	public void output(String text) {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.output(text);
		}//end if
	}//end method

	public void output(String text, boolean interrupt) {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.output(text, interrupt);
		}//end if
	}//end method

	public void speak(String text) {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.speak(text);
		}//end if
	}//end method

	public void speak(String text, boolean interrupt) {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.speak(text, interrupt);
		}//end if
	}//end method

	public void silence() {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.silence();
		}//end if
	}//end method

	public void braille(String text) {
		Screenreader screenreader = this.getRunningScreenreader();

		if (screenreader != null && screenreader.isRunning()) {
			screenreader.braille(text);
		}//end if
	}//end method

}//end class
