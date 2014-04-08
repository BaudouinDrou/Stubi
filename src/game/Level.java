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
	
	public int getLength(){
		return length;
	}
	
	public Background getBackground() {
		return bg;
	}
	
	public Obstacle[][] getObstacles(){
		return obstacles;
	}
	
	public void printObs() {
		String asw = "";
		for (int i = 0; i<obstacles.length;++i) {
			for (int j = 0; j<obstacles[i].length;++j)
				asw += i + ":" + j + ":" + obstacles[i][j].collision() + " ";
			asw += '\n';
		}
		System.out.println(asw);
	}
}