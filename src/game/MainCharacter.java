package game;

public class MainCharacter extends Character {
	private boolean crawl = false;
	
	// Constructors
	public MainCharacter(String path, Level lvl){
		super(path, 50, 100, null, lvl);
	}

	public MainCharacter(String path, int sWidth, int sHeight, int[]sequence, int timeFrame,Level level){
		super(path, sWidth, sHeight, sequence, timeFrame,level);
	}
	/**
	 * This function is called by MyKeyListener to set a move to the character
	 * @param c is the kind of move to set to the character
	 */
	public void setMove(char c) {
		switch (c){
		case 'r':	// right
			moveFactor = speed;
			if (crawl)
				moveFactor /= 2;
			play();
			break;
		case 'l':	// left
			moveFactor = -speed;
			if (crawl)
				moveFactor /= 2;
			play();
			break;
		case 's':	// stand
			moveFactor = 0;
			stop();
			break;
		case 'j':	// jump
			if (jump == 0)	// jump only if the character is not previously jumping
				jump = 1;
			break;
		default:
			moveFactor = 0;
			stop();
			break;
		}
	}
	
	/**
	 * This function set if the character is crawling or not
	 * @param b is the boolean value (crawling or not)
	 */
	public void setCrawling(boolean b){
		crawl = b;
	}
}
