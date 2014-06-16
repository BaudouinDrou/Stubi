package main;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MyMenu extends JMenuBar{
	/**
	 * This class is a menu for the game.
	 */
	private static final long serialVersionUID = 1L;
	
	//This is the JMenu that is shown
	private JMenu menu;
	
	public MyMenu(String title) {
	    super();
	    menu = new JMenu(title);
	    super.add(menu);
	}
	
	@Override
	public Component add(Component comp) {
	    //You add the the JMenu instead of the JMenuBar
	    return menu.add(comp);
	}
	
	@Override
	public JMenu add(JMenu c) {
	    //You add the the JMenu instead of the JMenuBar
	    return (JMenu) menu.add(c);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	
	    //Include these two lines to remove the button look
	    //Or remove this method to keep the button look
	    //g.setColor(getBackground());
	    //g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	protected void paintBorder(Graphics g) {
	    //Remove this line to remove the underline look
	    //when you remove the button look
	    //An alternative is to you setBorderPainted(false);
	    //when you create the object or in the constructor
	    //Or remove this method to keep the border
	    //super.paintBorder(g);
	}
}