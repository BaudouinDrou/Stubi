package game;


public class Level {
	private int length;	// distance to be run by Stubi
	public Background bg;	// background image
	
	private Obstacle[][] obstacles;
	
	public Level(int n) {
		String path = "img/LEVEL-" + n + ".png";
		bg = new Background(path);
		length = bg.getWidth();
		obstacles = Obstacle.readConf(n);
	}
	
	public Background getBackground() {
		return bg;
	}
	
	public Obstacle[][] getObstacles(){
		return obstacles;
	}
}