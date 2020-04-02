package jso.screenreaders;

import java.util.LinkedHashMap;
import java.util.Set;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.jacob.com.Dispatch;


public class SAPI extends Screenreader {

	private ActiveXComponent sapi;
	private LinkedHashMap<String, Dispatch> voices;
	private int pitch;
	private static final int SVSFDefault = 0;
	private static final int SVSFlagsAsync = 1;
	private static final int SVSFPurgeBeforeSpeak = 2;
	private static final int SVSFIsFilename = 4;
	private static final int SVSFIsXML = 8;
	private static final int SVSFIsNotXML = 16;
	private static final int SVSFPersistXML = 32;

	public SAPI() {
		super("sapi");
		this.sapi = new ActiveXComponent("SAPI.SpVoice");
		this.voices = this.getAvailableVoices();
		this.pitch = 0;
	}//end constructor

	private LinkedHashMap<String, Dispatch> getAvailableVoices() {
		LinkedHashMap<String, Dispatch> voices = new LinkedHashMap<String, Dispatch>();
		Variant variant = this.sapi.invoke("GetVoices");
		Dispatch speechObjectTokens = variant.toDispatch();
int voiceCount = Dispatch.get(speechObjectTokens, "Count").toInt();

for (int index = 0; index < voiceCount; index++) {
	Dispatch spObjectToken = Dispatch.call(speechObjectTokens, "Item", new Variant(index)).toDispatch();
	String voiceDescription = Dispatch.call(spObjectToken, "GetDescription").toString();
	voices.put(voiceDescription, spObjectToken);
}//end for loop

		return voices;
	}//end method

	@Override
	public boolean isRunning() {
		return this.sapi != null;
	}//end method

	@Override
	public void speak(String text) {
		String scrubbedText= text.replace("<", "&lt;");
		  String textOutput = "<pitch absmiddle=\"" + this.pitch + "\">" + scrubbedText + "</pitch>";
		this.sapi.invoke("speak", textOutput, SAPI.SVSFlagsAsync | SAPI.SVSFIsXML);
	}//end method

	@Override
	public void speak(String text, boolean interrupt) {
		int interruptValue = interrupt ? SAPI.SVSFPurgeBeforeSpeak : SAPI.SVSFDefault;
		String scrubbedText= text.replace("<", "&lt;");
		  String textOutput = "<pitch absmiddle=\"" + this.pitch + "\">" + scrubbedText + "</pitch>";
		this.sapi.invoke("speak", textOutput, SAPI.SVSFlagsAsync | interruptValue | SAPI.SVSFIsXML);
	}//end method

	@Override
	public void silence() {
		this.sapi.invoke("speak", "", SAPI.SVSFlagsAsync | SAPI.SVSFPurgeBeforeSpeak);
	}//end method

	public void waitUntilDone(long msTimeout) {
		this.sapi.invoke("WaitUntilDone", new Variant(msTimeout));
	}//end method

	public void waitUntilDone() {
		this.waitUntilDone(-1);
	}//end method

	public void speakWait(String text) {
		this.speak(text);
		this.waitUntilDone();
	}//end method

	public void speakWait(String text, boolean interrupt) {
		this.speak(text, interrupt);
		this.waitUntilDone();
	}//end method

	public String[] getVoices() {
		Set<String> keysSet = this.voices.keySet();
		return keysSet.toArray(new String[keysSet.size()]);
	}//end method

	public String getVoice() {
		Dispatch spObjectToken = this.sapi.getProperty("Voice").toDispatch();
Variant voiceDescriptionObject = Dispatch.call(spObjectToken, "GetDescription");
return voiceDescriptionObject.toString();
	}//end method

public void setVoice(String voiceDescription) {
	if ( !this.voices.containsKey(voiceDescription)) {
		throw new IllegalArgumentException("Provided voice description is not found in the available voices.");
	}//end if

	Dispatch spObjectToken = this.voices.get(voiceDescription);
	Dispatch.putRef(this.sapi, "Voice", spObjectToken);
}//end method

public int getRate() {
	return this.sapi.getProperty("Rate").getInt();
}//end method

public void setRate(int value) {
	this.sapi.setProperty("Rate", value);
}//end method

 public int getPitch() {
	 return this.pitch;
 }//end method

 public void setPitch(int value) {
	 this.pitch = value;
 }//end method

public int getVolume() {
	return this.sapi.getProperty("Volume").getInt();
}//end method

public void setVolume(int value) {
	this.sapi.setProperty("Volume", value);
}//end method

}//end class
