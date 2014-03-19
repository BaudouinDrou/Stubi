import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener{

	public void keyPressed(KeyEvent event) {
		System.out.println("Code touche press�e : " + event.getKeyCode() + " - caract�re touche press�e : " + event.getKeyChar());
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
		System.out.println("Code touche rel�ch�e : " + event.getKeyCode() + " - caract�re touche rel�ch�e : " + event.getKeyChar());         
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
		System.out.println("Code touche tap�e : " + event.getKeyCode() + " - caract�re touche tap�e : " + event.getKeyChar());
    	
	}   	
}
