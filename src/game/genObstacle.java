package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.SimpleBindings;

public class genObstacle {
	/**
	 * This class describe a generic Obstacle
	 */
	// Static
	protected static int sWidth = 30;
	protected static int sHeight = 30;
	protected static int confLines = Stubi.WINDY/sHeight;
	protected static SpriteSheet ss;
	protected static String pathAttr = "/conf/obstacleAttributes.csv";
	protected static SimpleBindings obsMapping;
	
	// Not static
	protected int n;		// type of obstacle (ID on the sprite)
	protected boolean collisionTop = true;
	protected boolean collisionBot = true;
	protected boolean collisionSide = true;
	protected boolean visible = true;
	protected int hurting = 0;
	
	public genObstacle(){
		n = 0;
	}
	
	/**
	 * Main constructor.
	 * @param ID is the ID on the SpriteSheet
	 * @param collision if the block collide or not
	 * @param visible if the block is visible or not
	 * @param hurting if the block is hurting or not
	 */
	public genObstacle(int ID, boolean collision, boolean visible, int hurting){
		n = ID;
		collisionTop = collision;
		collisionBot = collision;
		collisionSide = collision;
		this.visible = visible;
		this.hurting = hurting;
	}

	/**
	 * Accurate Constructor
	 * @param ID is the ID on the SpriteSheet
	 * @param collisionTop if there is a collision on the top of the block
	 * @param collisionBot if there is a collision on the bottom of the block
	 * @param collisionSide if there is a collision on the side of the block
	 * @param visible if the block is visible or not
	 * @param hurting if the block is hurting or not
	 */
	public genObstacle(int ID, boolean collisionTop, boolean collisionBot, boolean collisionSide, boolean visible, int hurting){
		n = ID;
		this.collisionTop = collisionTop;
		this.collisionBot = collisionBot;
		this.collisionSide = collisionSide;
		this.visible = visible;
		this.hurting = hurting;
	}

	//Getters
	public int getID() {
		return n;
	}
	
	public boolean CollisionTop() {
		return collisionTop;
	}
	
	public boolean CollisionBot() {
		return collisionBot;
	}
	
	public boolean CollisionSide() {
		return collisionSide;
	}
	
	public boolean visible() {
		return visible;
	}
	
	public int getDmg() {
		return hurting;
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
	
	// Classical methods
	
	public String toString() {
		String asw = "";
		asw += "Type of Obstacle : " + n + '\n';
		asw += "Visibility : " + visible + '\n';
		asw += "Collision Top : " + collisionTop;		
		return asw;
	}
	
	// Static methods
	/**
	 * This function initialize the class by using the level number (to load the right spriteSheet)
	 * @param n is the level number or more exactly, the spriteSheet type
	 */
	public static void init(int n) {
		setMapping();
		ss = new SpriteSheet("img/obstacles" + n + ".png",sWidth,sHeight);
	}
	
	/**
	 * this function update the spriteSheet, with a new type
	 * @param n is the spriteSheet type
	 */
	public static void changeSpriteSheet(int n) {
		ss = new SpriteSheet("img/obstacles" + n + ".png",sWidth,sHeight);		
	}
	
	/**
	 * This method is used to get a mapping between the obstacles and their attributes from the csv file "obstacleAttributes.csv"
	 */
	private static void setMapping(){
		obsMapping = new SimpleBindings();	// creates an arrayList to contain the elements
		InputStreamReader isReader= new InputStreamReader(Obstacle.class.getResourceAsStream(pathAttr));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = reader.readLine();	// read the first line
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
				String[] split = line.split(";");
				int ID = Integer.parseInt(split[1]);
				boolean cT = (split[2].equals("1"));
				boolean cB = (split[3].equals("1"));
				boolean cS = (split[4].equals("1"));
				boolean v = (split[5].equals("1"));
				int h = Integer.parseInt(split[6]);
				genObstacle tmpGenObs = new genObstacle(ID,cT,cB,cS,v,h);
		    	obsMapping.put(split[0],tmpGenObs);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
