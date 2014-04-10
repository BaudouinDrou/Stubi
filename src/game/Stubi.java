package game;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Stubi extends JFrame {
	/**
	 * Main class of the Stubi project
	 * 
	 * @author Baudouin Duthoit
	 * @author Romain Duthoit
	 * @version 0.0
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel pan;
	public static int WINDX = 800;
	public static int WINDY = 600;
	
	/**
	 * This constructor without parameters is setting up everything for the window and start the beneath functions from the JPanel
	 */
	public Stubi(){
		// Window parameter
		setTitle("Stubi");
		setSize(WINDX, WINDY);
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setFocusable(false);	// To enable Keylistener on panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        pan = new MyPanel();
        pan.setFocusable(true);
        pan.requestFocus();
        pan.addKeyListener(new MyKeyListener(pan));
        setContentPane(pan);
        pan.setPreferredSize(new Dimension(WINDX, WINDY));
        
        pack();	// To be fixed, provisional solution
	}
	
	public String toString(){
		return "Successful test !";
	}
    
	/**
	 * This is the main method which is launching the game
	 * @param arg is the standard paramter for the main method
	 */
    public static void main(String [] arg) {
		Stubi test = new Stubi();
		System.out.println(test);
	}
}