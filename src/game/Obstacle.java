package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Obstacle {
	private static int sWidth = 50;
	private static int sHeight = 50;
	private static SpriteSheet ss = new SpriteSheet("img/obstacles.png",sWidth,sHeight);
	private int n;		// type of obstacle
	private int x,y;	// position in the image
	
	public Obstacle(int n, int x, int y) {	// n will be the type of obstacle
		this.n = n;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public BufferedImage getImg() {
		return ss.getSprite(n);
	}
	
	/*
	 * This function is reading a configuration file and depending on this, create an array of obstacles with the help of the function below
	*/
	public static Obstacle[][] readConf(int n){
		String path = "/conf/confLevel"+ n +".txt";		
		Obstacle[][] obs = new Obstacle[12][];
		
		InputStreamReader isReader= new InputStreamReader(Obstacle.class.getResourceAsStream(path));
		try (BufferedReader reader = new BufferedReader(isReader)) {
		    String line = null;
		    int j = 0;	// y coord
		    while ((line = reader.readLine()) != null) {	// Reading the file line by line
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
			break;
		case '0':	// Wall
			asw = new Obstacle(0,i*sWidth,j*sHeight);
			// ...
			break;
		case '1':	// ???
			asw = new Obstacle(1,i*sWidth,j*sHeight);
			break;
		}
		return asw;
	}
}
