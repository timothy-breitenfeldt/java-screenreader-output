package tests;

import java.util.Arrays;
import java.util.Scanner;
import jso.screenreaders.*;



public class Test {

public static void main(String[] args) {
	SAPI screenreader = new SAPI();
    Scanner kin = new Scanner(System.in);
    String[] voices = screenreader.getVoices();
    System.out.println("current voice: " + screenreader.getVoice());
    System.out.println("voice current rate: " + screenreader.getRate());
    screenreader.setRate(5);
    System.out.println("new rate: " + screenreader.getRate());
    screenreader.speak("hello world", true);
    screenreader.speakWait("this is a string of text with a new rate.", true);


    /*
        	screenreader.speak("test");
            screenreader.speak("hello");
            screenreader.speak("world", true);
            kin.nextLine();
            screenreader.speak("new message");
            kin.nextLine();
            */

        kin.close();
    }  //end method

}  //end class