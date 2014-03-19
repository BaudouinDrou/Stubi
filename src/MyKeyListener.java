import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener{

	MyPanel pan;
	
	public MyKeyListener(MyPanel panel){
		pan = panel;
	}
	
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case 37:
			// Action start go left
			pan.getStubi().setMove('l');
			break;
		case 38:
			// Action start jump
			break;
		case 39:
			// Action start go right
			pan.getStubi().setMove('r');
			break;
		case 40:
			pan.getStubi().setCrawling(true);
			// Action start crawling
			break;
		default:
			break;		
		}
		
	}

	public void keyReleased(KeyEvent event) {
//		System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		switch (event.getKeyCode()) {
		case 37:
			pan.getStubi().setMove('s');	// stand
			// Action end go left
			break;
		case 39:
			pan.getStubi().setMove('s');	// stand
			// Action end go right
			break;
		case 40:
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
