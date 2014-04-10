package game;


public class Level {
	/**
	 * This class contain data about the level : Background, length for the character & list of obstacles
	 */
	private int length;	// distance to be run by Stubi
	private Background bg;	// background image
	private Obstacle[][] obstacles;	// Obastacle within the game
	
	/**
	 * Main constructor
	 * @param n is the level number
	 */
	public Level(int n) {
		String path = "img/LEVEL-" + n + ".png";
		bg = new Background(path);
		length = bg.getWidth();
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
	 * This function has been used for testing the loading of Obstacle
	 * @deprecated
	 */
	public void printObs() {
		String asw = "";
		for (int i = 0; i<obstacles.length;++i) {
			for (int j = 0; j<obstacles[i].length;++j)
				asw += i + ":" + j + ":" + obstacles[i][j].CollisionTop() + " ";
			asw += '\n';
		}
		System.out.println(asw);
	}
}