package main;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class Stubi extends JFrame {
	/**
	 * Main class of the Stubi project
	 */
	private static final long serialVersionUID = 1L;
	private JLayeredPane content;
	private MyPanel pan;
	private MyMenu menu;
	public static int WINDX = 800;
	public static int WINDY = 600;
	int menuHeight = 80;
	int margin = 5;
	
	/**
	 * This constructor without parameters is setting up everything for the window and start the beneath functions from the JPanel
	 */
	public Stubi(){
		// Content frame set up
		content = new JLayeredPane();
		content.setPreferredSize(new Dimension(WINDX, WINDY + menuHeight));
		
        // Menu set up
        JMenu jmenu = new JMenu("Under Menu");
	    jmenu.add(new JMenuItem("I am useless"));
	
	    menu = new MyMenu("Menu");
	    menu.add(jmenu);
	    menu.add(new JMenuItem("Lvl #1"));
	    menu.add(new JMenuItem("Lvl #2"));
        menu.setPreferredSize(new Dimension(WINDX, menuHeight));
        menu.setBounds(margin,margin,WINDX-2*margin, menuHeight-2*margin);
	    
        content.add(menu);
	    
        // Panel set up
        pan = new MyPanel();
        pan.addKeyListener(new MyKeyListener(pan));
        pan.setFocusable(true);		// For Keylistener
        pan.requestFocusInWindow(); // For Keylistener
        pan.setBounds(0,menuHeight,WINDX, WINDY);
        pan.setPreferredSize(new Dimension(WINDX, WINDY));
        content.add(pan);
//        setContentPane(pan);
        
		// Window parameter
		setTitle("Stubi");
		setSize(WINDX, WINDY + menuHeight);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setContentPane(content);
        pack();	// To be fixed, provisional solution
	}
	
	public String toString(){
		return "Successful test !";
	}
    
	/**
	 * This is the main method which is launching the game
	 * @param arg is the standard parameter for the main method
	 */
    public static void main(String [] arg) {
		Stubi test = new Stubi();
		System.out.println(test);
	}
}