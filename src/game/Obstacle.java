package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Obstacle {
	private static int sWidth = 30;
	private static int sHeight = 30;
	private static int confLines = Stubi.WINDY/sWidth;
	private static SpriteSheet ss = new SpriteSheet("img/obstacles.png",sWidth,sHeight);
	private int n;		// type of obstacle
	private int x,y;	// position in the image
	private boolean collision = true;
	private boolean print = true; 
	
	public Obstacle(int n, int x, int y) {	// n will be the type of obstacle
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
	
	public boolean collision() {
		return collision;
	}
	
	public boolean print() {
		return print;
	}
	
	public BufferedImage getImg() {
		return ss.getSprite(n);
	}
	
	// Setters
	public void setCollision(boolean b) {
		collision = b;
	}
	
	public void setPrint(boolean b) {
		print = b;
	}
	
	// Classical functions
	
	public String toString() {
		String asw = "";
		asw += "Position x : " + x + " and int pos " + (int) x/50 + '\n';
		asw += "Position y : " + y + " and int pos " + (int) y/50 + '\n';
		asw += "Type of Obstacle : " + n + '\n';
		asw += "Collision : " + collision;
		return asw;
	}
	
	// Other functions	
	
	/*
	 * This function is reading a configuration file and depending on this, create an array of obstacles with the help of the function below
	*/
	public static Obstacle[][] readConf(int n){
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
	
	private static Obstacle charToObs(char c, int i, int j){
		Obstacle asw = null;
		switch(c){
		case ' ':
			asw = new Obstacle(10,i*sWidth,j*sHeight);
			asw.setCollision(false);
			asw.setPrint(false);
			break;
		case '0':	// Wall
			asw = new Obstacle(0,i*sWidth,j*sHeight);
			// ...
			break;
		case '1':	// Stairs steps (ascending)
			asw = new Obstacle(1,i*sWidth,j*sHeight);
			break;
		case '2':	// Stairs steps (flat)
			asw = new Obstacle(2,i*sWidth,j*sHeight);
			break;
		case '3':	// Stairs steps (descending)
			asw = new Obstacle(3,i*sWidth,j*sHeight);
			break;
		case '4':	// Stairs (content)
			asw = new Obstacle(4,i*sWidth,j*sHeight);
			break;
		case '5':	// Flames
			asw = new Obstacle(5,i*sWidth,j*sHeight);
			break;
		case '6':	// Flames
			asw = new Obstacle(6,i*sWidth,j*sHeight);
			break;
		case '/':	// Final portal
			asw = new Obstacle(15,i*sWidth,j*sHeight);
			asw.setCollision(false);
			break;
		default:
			asw = new Obstacle(10,i*sWidth,j*sHeight);
			asw.setCollision(false);
			asw.setPrint(false);
		}
		return asw;
	}
}
