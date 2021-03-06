package game;

import java.awt.Graphics;

import main.Stubi;


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
	 * @param x is the coordinate of the main character
	 * @param lag is the lag with the main character.
	 */
	public void print(Graphics g, int x, int lag){
		g.drawImage(bg.getImg(), 0, 0, null);
		int j0 = (x-lag)/genObstacle.sWidth -1;	// part of the grid to draw the obstacles (j0<0 special case)
		int j1 = j0 + Stubi.WINDX/genObstacle.sWidth + 2;
		for (int i = 0; i<obstacles.length;++i){	// i = y coord
			for (int j = j0; j<obstacles[i].length && j<=j1; ++j) { // j = x coord
				Obstacle tmp;
				int xx = lag - x; // Position of printing
					if (j<0) {
						// Print the beginning of the level (a wall ? a repeat of the first sequence ?)
						tmp = obstacles[i][0];
						xx += tmp.getX() + genObstacle.getWidth()*j;
					} else {
						tmp = obstacles[i][j];
						xx += tmp.getX();
					}
					if (tmp.visible()) {
						int y = tmp.getY();
						g.drawImage(tmp.getImg(),xx, y, null);	// Buffered Image, x coord, y coord, no Image observer
					}
			}
		}
	}
}