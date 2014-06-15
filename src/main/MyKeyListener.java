package main;
import game.MainCharacter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener{
	/**
	 * This class is here to respond to user's input via keyboard
	 */
	MyPanel pan;
	int currentKey = -1;
	boolean pause = false;
	
	/**
	 * Main constructor
	 * @param panel is the Panel, the KeyListener will be used in
	 */
	public MyKeyListener(MyPanel panel){
		pan = panel;
	}
	
	/**
	 * This function will react when the user will press a key, depending on the key, a different action is to be expected
	 */
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();
		MainCharacter stubi = pan.getStubi();
		boolean nonRepeat = (code != currentKey);
		currentKey = code;
		switch (code) {
		case 37:	// '<-'
		case 81:	// 'q'
			// Action start go left
			if (nonRepeat)
				stubi.setMove('l');
			break;
		case 38:	// up arrow
		case 90:	// 'z'
		case 32:	// ' '
			// Action start jump
			stubi.setMove('j');
			break;
		case 39:	// '->'
		case 68:	// 'd'
			// Action start go right
			if (nonRepeat)
				stubi.setMove('r');
			break;
		case 40:	// down arrow
		case 83:	// 's'
			// Action start crawling
			if (nonRepeat)
				stubi.setCrawling(true);
			break;
		case 27:
			// Action when "esc" pressed
			if (pause){
				pause = false;
				pan.resume();
			} else {
				pause = true;
				pan.pause();
			}
			break;
		default:
			System.out.println(event.getKeyCode());
			break;
		}
	
	}

	/**
	 * This function is here to send the information of a released key
	 */
	public void keyReleased(KeyEvent event) {
		int code = event.getKeyCode();
		MainCharacter stubi = pan.getStubi();
		if (code==currentKey)
			currentKey = -1;
		switch (code) {
		case 37:
		case 81:
			stubi.setMove('s');	// stand
			// Action end go left
			break;
		case 39:
		case 68:
			stubi.setMove('s');	// stand
			// Action end go right
			break;
		case 40:
		case 83:
			stubi.setCrawling(false);
			// Action end crawling
			break;
		default:
			break;		
		}                  
	}
	
	/**
	 * Unused for now, this function could be useful later
	 */
	public void keyTyped(KeyEvent event) {
//		System.out.println("Code touche tapee : " + event.getKeyCode() + " - caractere touche tapee : " + event.getKeyChar());  	
	}   	
}
