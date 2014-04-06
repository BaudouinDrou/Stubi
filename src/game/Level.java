package game;

import java.util.ArrayList;

public class Level {
	private int length;	// distance to be run by Stubi
	public Background bg;	// background image
	
	private ArrayList<Obstacle> frames;
	
	public Level(int n) {
		String path = "img/LEVEL-" + n + ".png";
		bg = new Background(path);
		length = bg.getWidth();
	}
	
	public Background getBackground() {
		return bg;
	}

}
