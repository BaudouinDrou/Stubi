package game;

import java.awt.Graphics;


public class Level {
	/**
	 * This class contain data about the level : Background, length for the character & list of obstacles
	 */
	public int scale = 2;
	private int length;	// distance to be run by Stubi
	private Background bg;	// background image
	private Obstacle[][] obstacles;	// Obstacle within the game
	
	/**
	 * Main constructor
	 * @param n is the level number
	 */
	public Level(int n) {
		String path = "img/LEVEL-" + n + ".png";
		bg = new Background(path);
		length = bg.getWidth()*scale;
		obstacles = Obstacle.readConfCsv(n);
	}
	
	public int getLength(){
		return length;
	}
	
	public Background getBackground() {
		return bg;
	}
	
	public Obstacle[][] getObstacles(){
		return obstacles;
	}
	
	/**
	 * This method is used to print the level on the graphic g
	 * @param g is the graph on which we print the level
	 */
	public void print(Graphics g, int x){
		g.drawImage(bg.getImg(), 0, 0, null);
		int j0 = x/genObstacle.sWidth;
		int j1 = j0 + Stubi.WINDX/genObstacle.sWidth + 1;
		for (int i = 0; i<obstacles.length;++i){	// i = y coord
			for (int j = j0; j<obstacles[i].length && j<j1; ++j) { // j = x coord
					Obstacle tmp = obstacles[i][j];
					if (tmp.visible())
						g.drawImage(tmp.getImg(),tmp.getX()-x,tmp.getY(), null);	// Buffered Image, x coord, y coord, no Image observer
			}
		}
	}
}