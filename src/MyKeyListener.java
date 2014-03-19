import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener{

	public void keyPressed(KeyEvent event) {
		System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
		switch (event.getKeyCode()) {
		case 37:
			// Action start go left
			break;
		case 38:
			// Action start jump
			break;
		case 39:
			// Action start go right
			break;
		case 40:
			// Action start crawling
			break;
		default:
			break;		
		}
		
	}

	public void keyReleased(KeyEvent event) {
		System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		switch (event.getKeyCode()) {
		case 37:
			// Action end go left
			break;
		case 39:
			// Action end go right
			break;
		case 40:
			// Action end crawling
			break;
		default:
			break;		
		}                  
	}
	public void keyTyped(KeyEvent event) {
		System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
    	
	}   	
}
