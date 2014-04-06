package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener{

	MyPanel pan;
	int currentKey = -1;
	boolean pause = false;
	
	public MyKeyListener(MyPanel panel){
		pan = panel;
	}
	
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();
		boolean nonRepeat = (code != currentKey);
		currentKey = code;
		switch (code) {
		case 37:	// '<-'
		case 81:	// 'q'
			// Action start go left
			if (nonRepeat)
				pan.getStubi().setMove('l');
			break;
		case 38:	// up arrow
		case 90:	// 'z'
		case 32:	// ' '
			// Action start jump
			pan.getStubi().setMove('j');
			break;
		case 39:	// '->'
		case 68:	// 'd'
			// Action start go right
			if (nonRepeat)
				pan.getStubi().setMove('r');
			break;
		case 40:	// down arrow
		case 83:	// 's'
			// Action start crawling
			if (nonRepeat)
				pan.getStubi().setCrawling(true);
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

	public void keyReleased(KeyEvent event) {
//		System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		switch (event.getKeyCode()) {
		case 37:
		case 81:
			pan.getStubi().setMove('s');	// stand
			// Action end go left
			break;
		case 39:
		case 68:
			pan.getStubi().setMove('s');	// stand
			// Action end go right
			break;
		case 40:
		case 83:
			pan.getStubi().setCrawling(false);
			// Action end crawling
			break;
		default:
			break;		
		}                  
	}
	public void keyTyped(KeyEvent event) {
//		System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());  	
	}   	
}
