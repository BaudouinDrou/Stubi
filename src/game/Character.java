package game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	/**
	 * This class describe all characters in the game
	 * For now, only the main character exist and he is the only one implemented in that class
	 */
	private Animator walk;
	private BufferedImage stand;
	private volatile Level level;
	
	public int x = 150, y = 250;	// Starting Position
	private int rX = x;		// Real position
	private int moveFactor = 0;	// Number of pixel to change his position
	private int speed = 3;
	private boolean crawl = false;
	private int width, height;
	
	private long previousTime = 0;
	private int timeFrame;
	
	private int jump = -1; // -1 mean in the air
	private int gravity = 6;
	
	private boolean dead = false;

	// Constructors
	public Character(String path, Level lvl){
		this(path, 50, 100, null, lvl);
	}
	
	public Character(String path, int sWidth, int sHeight, int[]sequence, Level lvl){
		this(path, sWidth, sHeight,sequence,MyPanel.gameTimeFrame, lvl);
	}
	
	/**
	 * This constructor is the main one
	 * @param path is the path to the sprite sheet for the character (usually "img/XXX.png")
	 * @param sWidth is the width of one sprite
	 * @param sHeight is the height of one sprite
	 * @param sequence is the sequence of sprites that makes his walk
	 * @param timeFrame is the refreshing time for the animation
	 * @param level level is the level the character is playing in
	 */
	public Character(String path, int sWidth, int sHeight, int[]sequence, int timeFrame,Level level){
		ImageLoader loader = new ImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.load(path);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet,sWidth,sHeight);
		width = sWidth;
		height = sHeight;
		
		ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
		
		// Stand position in 0
		stand = ss.getSprite(0);
		
		// Frame sequence (walk/run) :		
		if (sequence!=null){
			for (int i = 0; i<sequence.length;++i){
				sprites.add(ss.getSprite(sequence[i]));			
			}
		} // If sequence empty act as no walk
		
		walk = new Animator(sprites);
		walk.setRefreshTime(timeFrame);
		
		// Setting time settings
		this.timeFrame = timeFrame;
		previousTime = 0;
		
		// Level 
		this.level = level;
	}
	
	// Getters Setters
	
	public BufferedImage getSprite() {
		if (moveFactor==0)
			return stand;
		return walk.sprite;
	}
	
	public boolean dead() {
		return dead;
	}
	
	public void setwalkRefreshTime(long n){
		walk.setRefreshTime(n);
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
	
	// Services
	
	public void play(){
		walk.play();
	}
	
	public void stop(){
		walk.stop();
	}
	
	public void resume(){
		walk.resume();
	}
	
	public void pause(){
		walk.pause();
	}

	// Other functions
	/**
	 * This method is called every time we want to print the character on the screen. We test the refreshment time before doing anything
	 * @param time is the current time of the game
	 */
	public void update(long time){
		walk.update(time);
		if(time - previousTime >= timeFrame) {
			gravityUpdate();
			collisionUpdate();
			// movement
			x += moveFactor;
			rX += moveFactor;
			
			// Right side limit for the character BUGGED
			if (x+width>Stubi.WINDX-250) {
				if (!level.getBackground().update(moveFactor))
					x = Stubi.WINDX-250-width;
				else {// end of the lvl, stop moving forward
						x = Stubi.WINDX-width-250;
						rX -= moveFactor;
				}
			}
			if (rX+width>level.getLength()){
				rX = level.getLength()-width;
			}
			// Left side limit for the character :
			if (x<50) {
				x = 50;
				level.getBackground().update(moveFactor);
			}
			if (rX<50) {
				rX = 50;
			}
			// Roof limit
			if (y<0)
				y = 0;
		}
	}
	
	/**
	 * This function is updating the gravity factor based on the character (jumping or not)
	 * It is based on {@link #jump} & {@link #gravity}
	 */
	private void gravityUpdate(){
		if (jump > 0) {
			// Jump case
			if (jump < 15) {	// Go higher
				y -= gravity;
				++jump;
			} else if (jump < 30) {	// Go higher more slowly
				y -= gravity/2;
				++jump;				
			} else if (jump == 30) {	// Stand
				++jump;				
			} else if (jump <= 45) {	// Start reaching the ground
				y += gravity/2;
				++jump;
			} else if (jump <60) {	// Start reaching the ground
				y += gravity;
				++jump;
			} else {
				jump = -1;				
			}
		} else {
			// Gravity :
			y += gravity;
		}
	}
	
	/**
	 * This function is applying collision with obstacles, resetting the character at his right place
	 */
	private void collisionUpdate() {
		Obstacle[][] obs = level.getObstacles();
		int i = y/Obstacle.getHeight(); // Stubi's head position in grid
		int j = rX/Obstacle.getWidth(); // Stubi's left side position in the grid
		int k = rX%Obstacle.getWidth(); // Stubi's x coord int the (i,j) block in the grid
		boolean colideBad = true;
		colideBad &= collisionFeet(obs,j,k);
		colideBad &= collisionHead(obs,i,j,k);
		// collisionSide(obs,i,j,k);
		if (colideBad)
			System.out.println("BAD COLLISION");
	}
	
	
	/**
	 * This function is testing the collision with the below obstacles, helping {@link #collisionUpdate()}
	 * @param obs is an array of array containing Obstacle
	 * @param j correspond to the x coord of the first box in contact with Stubi
	 * @param k is the position within the box #j
	 * @return a boolean if there is collision or not
	 */
	private boolean collisionFeet(Obstacle[][] obs, int j, int k) {
		// Testing the boxes below Stubi :
		int i = (y+height)/Obstacle.getHeight(); // box below the Nbox (one occupied by Stubi's feet)
		if (i>=obs.length) {
			dead = true;
		} else {
			boolean colide = obs[i][j].CollisionTop();	// box below his left foot
			while (k<width){	// try all boxes below Stubi
				colide |= obs[i][++j].CollisionTop();
				k += Obstacle.getWidth();
			}				
			if (colide) {
				jump = 0;
				y = i*Obstacle.getHeight() - height;	// Put Stubi back in his N box if collision
			}
			return colide;
		}
		return false;
	}
	
	/**
	 * This method is used to know if there is a collision at the head of the character and if yes, put him back in place, helping {@link #collisionUpdate()}
	 * @param obs is the array containing obstacles
	 * @param i is the y coord of Stubi's head in the grid
	 * @param j is the x coord of Stubi's head
	 * @param k is the x position inside the box describe by the grid
	 * @return a boolean that say if Stubi colide or not
	 */
	private boolean collisionHead(Obstacle[][] obs,int i, int j, int k) {
		boolean colide = obs[i][j].CollisionBot();	// box at his head
		while (k<width){	// try all boxes on Stubi's head line
			colide |= obs[i][++j].CollisionBot();
			k += Obstacle.getWidth();
		}				
		if (colide) {
			y = (i+1)*Obstacle.getHeight();	// Put Stubi back in his N box if collision
		}
		return colide;		
	}
	
	/**
	 * This method deals with the left and right collisions that could occur while playing, helping {@link #collisionUpdate()}
	 * @param obs is the array containing all the obstacles we'll test
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param k is the X coordinate within the box
	 * @return a boolean that say if there has been a collision on Stubi's side
	 */
	private boolean collisionSide(Obstacle[][] obs,int i, int j, int k) {
		int l  = y%Obstacle.getHeight();
		// Left side 
		boolean colide = collisionSideHelp(obs,i,j,l,1);
		while (k<width){	// move to right side
			k += Obstacle.getWidth();
			++j;
		}
		// Right side
		colide |= collisionSideHelp(obs,i,j,l,-1);
		return colide;
	}
	
	/**
	 * This method is here to help the function {@link #collisionSide(Obstacle[][], int, int, int)} to factorize some work
	 * @param obs is the array containing all the obstacles we'll test
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param l is the Y coordinate within the grid
	 * @param move is the movement to apply in case of collision (-1 = to right, +1 = to left)
	 * @return
	 */
	private boolean collisionSideHelp(Obstacle[][] obs,int i, int j, int l,int move) {
		boolean colide = obs[i][j].CollisionBot();
		while (l<height){
			colide |= obs[++i][j].CollisionBot();
			l += Obstacle.getHeight();
		}
		if (colide) {
			rX = j*Obstacle.getWidth() + move;	// Put Stubi back in his N box if collision
		}
		return colide;
	}
}
