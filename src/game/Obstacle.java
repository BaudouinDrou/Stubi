package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Obstacle extends genObstacle {
	/**
	 * This class describe an obstacle
	 */
	
	private int x,y;	// position in the image
	
	/**
	 * Main Constructor
	 * @param key is the key to access the Obstacle, it is its description on CSV file
	 * @param x is the X coordinate on the whole level
	 * @param y is the Y coordinate on the whole level
	 */	
	public Obstacle(String key, int x, int y) {
		genObstacle tmp;
		try {
			tmp = (genObstacle) genObstacle.obsMapping.get(key);
		} catch (IllegalArgumentException e) {
			tmp = new genObstacle(-1,false,false,0);	// ID = 0 collision, visible, hurting 
		}
		if (tmp != null) {
			super.n = tmp.getID();
			super.collisionTop = tmp.CollisionTop();
			super.collisionBot = tmp.CollisionBot();
			super.collisionSide = tmp.CollisionSide();
			super.visible = tmp.visible();
			super.hurting = tmp.getDmg();
		}
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
	
	// Classical functions
	
	public String toString() {
		String asw = "";
		asw += "Position x : " + x + " and int pos " + (int) x/50 + '\n';
		asw += "Position y : " + y + " and int pos " + (int) y/50 + '\n';
		asw += "Type of Obstacle : " + n + '\n';
		asw += "Collision Top : " + collisionTop;
		return asw;
	}
	
	// Static functions
	
	/**
	 * This function is reading a configuration file and depending on this it creates an array of obstacles with the help of StringToObs
	 * @param n is the level number : each level has a different configuration
	 * @return a bi-dimensional array of Obstacle 
	*/
	public static Obstacle[][] readConfCsv(int n){
		String path = "conf/confLevel"+ n +".csv";		
		Obstacle[][] obs = new Obstacle[Obstacle.confLines][];
		
		InputStreamReader isReader= new InputStreamReader(Obstacle.class.getClassLoader().getResourceAsStream(path));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = null;
		    int j = 0;	// y coord
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
				ArrayList<Obstacle> tmpObsList = new ArrayList<Obstacle>();	// creates an arrayList to contain the elements
				String[] split = line.split(";");
		    	for (int i = 0; i<split.length; ++i){	// i = x coord
		    		int x = i*genObstacle.getWidth();
		    		int y = j*genObstacle.getHeight();
		    		Obstacle tmpObs = new Obstacle(split[i],x,y);
		    		tmpObsList.add(tmpObs);
		    	}
		    	obs[j] = tmpObsList.toArray(new Obstacle[tmpObsList.size()]);	// transform the arrayList into an array
		    	++j;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return obs;
	}
}
