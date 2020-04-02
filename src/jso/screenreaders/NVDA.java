package jso.screenreaders;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;


public class NVDA extends Screenreader {

	private interface NVDAControllerClient extends Library {

		public void nvdaController_speakText(WString text);
		public void nvdaController_cancelSpeech();
		public void nvdaController_brailleMessage(WString text);
		public boolean nvdaController_testIfRunning();

	}  //end nested interface


	private NVDAControllerClient nvdaLibrary;

	public NVDA() {
		super("nvda");

		if (this.architecture.equals("32")) {
			this.nvdaLibrary = (NVDAControllerClient)Native.loadLibrary("nvdaControllerClient32", NVDAControllerClient.class);
		} else if (this.architecture.equals("64")) {
			this.nvdaLibrary = (NVDAControllerClient)Native.loadLibrary("nvdaControllerClient64", NVDAControllerClient.class);
		}//end if
	}  //end constructor

	@Override
	public boolean isRunning() {
		return !(this.nvdaLibrary.nvdaController_testIfRunning());
	}  //end method

	@Override
	public void speak(String text) {
		this.nvdaLibrary.nvdaController_speakText(new WString(text));
	}  //end method

	@Override
	public void speak(String text, boolean interrupt) {
		if (interrupt) {
			this.silence();
		}  //end if

		this.nvdaLibrary.nvdaController_speakText(new WString(text));
	}  //end method

	@Override
	public void silence() {
		this.nvdaLibrary.nvdaController_cancelSpeech();
	}  //end method

	@Override
	public void braille(String text) {
		this.nvdaLibrary.nvdaController_brailleMessage(new WString(text));
	}  //end method

}  //end class