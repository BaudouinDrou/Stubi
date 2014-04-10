package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Obstacle {
	/**
	 * This class describe an obstacle
	 */
	private static int sWidth = 30;
	private static int sHeight = 30;
	private static int confLines = Stubi.WINDY/sWidth;
	private static SpriteSheet ss = new SpriteSheet("img/obstacles.png",sWidth,sHeight);
	private int n;		// type of obstacle
	private int x,y;	// position in the image
	private boolean collisionTop = true;
	private boolean visible = true; 
	
	/**
	 * Main Constructor
	 * @param n is the type of the Obstacle
	 * @param x is the X coordinate on the whole level
	 * @param y is the Y coordinate on the whole level
	 */
	public Obstacle(int n, int x, int y) {
		this.n = n;
		this.x = x;
		this.y = y;
	}
	
	// Getters	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return n;
	}
	
	public boolean CollisionTop() {
		return collisionTop;
	}
	
	public boolean visible() {
		return visible;
	}
	
	public BufferedImage getImg() {
		return ss.getSprite(n);
	}
	
	public static int getHeight() {
		return sHeight;
	}
	
	public static int getWidth() {
		return sWidth;
	}
	
	// Setters
	public void setCollisionTop(boolean b) {
		collisionTop = b;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	
	// Classical functions
	
	public String toString() {
		String asw = "";
		asw += "Position x : " + x + " and int pos " + (int) x/50 + '\n';
		asw += "Position y : " + y + " and int pos " + (int) y/50 + '\n';
		asw += "Type of Obstacle : " + n + '\n';
		asw += "Collision Top : " + collisionTop;
		return asw;
	}
	
	// Other functions	
	
	/**
	 * This function is reading a configuration file and depending on this, create an array of obstacles with the help of charToObs
	 * @param n is the level number : each level has a different configuration
	 * @return a bi-dimensional array of Obstacle 
	 * @deprecated
	*/
	public static Obstacle[][] readConfTxt(int n){
		String path = "/conf/confLevel"+ n +".txt";		
		Obstacle[][] obs = new Obstacle[Obstacle.confLines][];
		
		InputStreamReader isReader= new InputStreamReader(Obstacle.class.getResourceAsStream(path));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = null;
		    int j = 0;	// y coord
		    while ((line = reader.readLine()) != null && j<obs.length) {	// Reading the file line by line
				ArrayList<Obstacle> tmpObs = new ArrayList<Obstacle>();	// creates an arrayList to contain the elements
		    	for (int i = 0; i<line.length(); ++i){	// i = x coord
		    		tmpObs.add(charToObs(line.charAt(i),i,j));
		    	}
		    	obs[j] = tmpObs.toArray(new Obstacle[tmpObs.size()]);	// transform the arrayList into an array
		    	++j;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return obs;
	}	
	
	/**
	 * This function is reading a configuration file and depending on this, create an array of obstacles with the help of StringToObs
	 * @param n is the level number : each level has a different configuration
	 * @return a bi-dimensional array of Obstacle 
	*/
	public static Obstacle[][] readConfCsv(int n){
		String path = "/conf/confLevel"+ n +".csv";		
		Obstacle[][] obs = new Obstacle[Obstacle.confLines][];
		
		InputStreamReader isReader= new InputStreamReader(Obstacle.class.getResourceAsStream(path));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = null;
		    int j = 0;	// y coord
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
				ArrayList<Obstacle> tmpObs = new ArrayList<Obstacle>();	// creates an arrayList to contain the elements
				String[] split = line.split("'");
		    	for (int i = 0; i<split.length; ++i){	// i = x coord
		    		tmpObs.add(StringToObs(split[i],i,j));
		    	}
		    	obs[j] = tmpObs.toArray(new Obstacle[tmpObs.size()]);	// transform the arrayList into an array
		    	++j;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return obs;
	}
	
	/**
	 * This method return an Obstacle from it's given char ID 
	 * @param c is its ID
	 * @param i is its position on the line (will be used to know its position on the screen)
	 * @param j is its position on the column (will be used to know its position on the screen)
	 * @return an Obstacle
	 * @deprecated
	 */
	private static Obstacle charToObs(char c, int i, int j){
		String s = c + "";
		return StringToObs(s, i, j);
	}
	
	/**
	 * This method return an Obstacle from it's given String ID ("12", "grass", "soil", ...) 
	 * @param s is its ID
	 * @param i is its position on the line (will be used to know its position on the screen)
	 * @param j is its position on the column (will be used to know its position on the screen)
	 * @return an Obstacle
	 */
	private static Obstacle StringToObs(String s, int i, int j){
		Obstacle asw = null;
		switch(s){
		case "":
			asw = new Obstacle(10,i*sWidth,j*sHeight);
			asw.setCollisionTop(false);
			asw.setVisible(false);
			break;
		case "0":	// Wall
			asw = new Obstacle(0,i*sWidth,j*sHeight);
			// ...
			break;
		case "1":	// Stairs steps (ascending)
			asw = new Obstacle(1,i*sWidth,j*sHeight);
			break;
		case "2":	// Stairs steps (flat)
			asw = new Obstacle(2,i*sWidth,j*sHeight);
			break;
		case "3":	// Stairs steps (descending)
			asw = new Obstacle(3,i*sWidth,j*sHeight);
			break;
		case "4":	// Stairs (content)
			asw = new Obstacle(4,i*sWidth,j*sHeight);
			break;
		case "5":	// Flames
			asw = new Obstacle(5,i*sWidth,j*sHeight);
			break;
		case "6":	// Flames
			asw = new Obstacle(6,i*sWidth,j*sHeight);
			break;
		case "/":	// Final portal
			asw = new Obstacle(15,i*sWidth,j*sHeight);
			asw.setCollisionTop(false);
			break;
		default:
			asw = new Obstacle(10,i*sWidth,j*sHeight);
			asw.setCollisionTop(false);
			asw.setVisible(false);
		}
		return asw;
	}
}
