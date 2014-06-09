package game;

public class MainCharacter extends Character {
	/**
	 * This class contain all information about the main character.
	 * It extends {@link Character} for more factorizations
	 */
	private boolean crawl = false;
	
	public int lag = 150;
	
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
			moveX = speed;
			if (crawl)
				moveX /= 2;
			play();
			break;
		case 'l':	// left
			moveX = -speed;
			if (crawl)
				moveX /= 2;
			play();
			break;
		case 's':	// stand
			moveX = 0;
			stop();
			break;
		case 'j':	// jump
			if (!inAir)	// jump only if the character is not previously jumping
				jump = 1;
			break;
		default:
			moveX = 0;
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
