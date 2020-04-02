package jso.screenreaders;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;


public class JAWS extends Screenreader {

	private ActiveXComponent jaws;

public JAWS() {
		super("jaws");
		this.jaws = new ActiveXComponent("freedomsci.jawsapi");
	}//end constructor

@Override
	public boolean isRunning() {
		Variant variant = this.jaws.invoke("SayString", "", 0);
		return variant.getBoolean();
	}//end method

@Override
	public void speak(String text) {
		this.jaws.invoke("SayString", text, 0);
	}//end method

@Override
	public void speak(String text, boolean interrupt) {
		int value = interrupt ? 1 : 0;
		this.jaws.invoke("SayString", text, value);
	}//end method

@Override
	public void silence() {
		this.jaws.invoke("StopSpeech");
	}//end method

@Override
	public void braille(String text) {
		this.jaws.invoke("RunFunction", "BrailleMessage(\"" + text + "\")");
	}//end method

public boolean runScript(String scriptName) {
	Variant variant = this.jaws.invoke("RunScript", scriptName);
			return variant.getBoolean();
}//end method

public boolean runFunction(String functionName) {
	Variant variant = this.jaws.invoke("RunFunction", functionName);
			return variant.getBoolean();
}//end method

}//end class
