package main;

import java.awt.Component;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MyMenu extends JMenuBar{
	/**
	 * This class is a menu for the game options.
	 */
	private static final long serialVersionUID = 1L;
	
	//This is the JMenu that is shown
	private JMenu menu;
	private String pathAttr = "conf/menuList.csv";
	private int numConfColumn = 3;

	public MyMenu() {
	    super();
	    menu = new JMenu();
	    super.add(menu);
	}
	
	public MyMenu(String title) {
	    super();
	    menu = new JMenu(title);
	    super.add(menu);
	}
	
	public void setType(int n) {
		InputStreamReader isReader= new InputStreamReader(MyMenu.class.getClassLoader().getResourceAsStream(pathAttr));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = reader.readLine();	// read the first line containning the description of the column
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
				String[] split = line.split(";");
				int ID = Integer.parseInt(split[0]);
				if (n==ID){		// If we are on the line where the Menu is defined
					int size = Integer.parseInt(split[1]);
					int i = 0;
					for (; i<size;++i){
						String[] split2 = split[i+numConfColumn].split(",");
						if (split2.length>1) {
							MyMenu submenu = new MyMenu(split2[1]);
							submenu.setType(Integer.parseInt(split2[0]));
							menu.add(submenu);	// submenu
						} else {
							menu.add(new MyMenuItem(Integer.parseInt(split[i+numConfColumn])));	// items
						}
					}
				}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
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