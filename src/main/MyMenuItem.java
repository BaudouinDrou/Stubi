package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JMenuItem;

public class MyMenuItem extends JMenuItem{
	/**
	 * This class define an item in a menu
	 */
	private static final long serialVersionUID = 1L;
	private static String path = "conf/menuItems.csv";
	private String title;
	private String description;
	
	/**
	 * This constructor build a Menu Item base on its ID in the configuration ({@link conf}) file "menuItems.csv"
	 * @param n is the ID of the item in the csv file
	 */
	public MyMenuItem(int n) {
		super(CatchTitle(n));
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDesc() {
		return description;
	}
	
	public static String CatchTitle(int n) {
		InputStreamReader isReader= new InputStreamReader(MyMenu.class.getClassLoader().getResourceAsStream(path));
		String title = null;
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = reader.readLine();	// read the first line containing the description of the column
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
				String[] split = line.split(";");
				int ID = Integer.parseInt(split[0]);
				if (n==ID){		// If we are on the line where the Menu is defined
					title = split[1];
//					description = split[2];
				}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return title;
	}
}
