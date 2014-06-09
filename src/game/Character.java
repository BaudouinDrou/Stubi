package game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	/**
	 * This class describe all characters in the game
	 * For now, only the main character exist and he is the only one implemented in that class
	 */
	public Animator walk;
	public BufferedImage stand;
	public volatile Level level;
	
	protected int x = 150, y = 250;	// Starting Position
	protected int speed = 3;
	protected int width, height;
	
	protected long previousTime = 0;
	protected int timeFrame;

	protected int moveX = 0;	// Number of pixel to change his position on x
	protected int moveY = 6;	// Number of pixel to change his position on y
	protected int jump = -1; 	// -1 mean in the air
	protected boolean inAir = true;
	
	protected int health = 100;
	protected boolean dead = false;
	
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
		if (moveX==0)
			return stand;
		return walk.sprite;
	}
	
	public boolean dead() {
		return dead;
	}
	
	public void setWalkRefreshTime(long n){
		walk.setRefreshTime(n);
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
		level.getBackground().setX(x);
		if(time - previousTime >= timeFrame) {
			// movement over y and x
			upY();
			upX();
			// Calculation Stubi position :
			int i = y/Obstacle.getHeight(); // Stubi's head position in grid
			int j = x/Obstacle.getWidth(); // Stubi's left side position in the grid
			int k = x%Obstacle.getWidth(); // Stubi's x coord in the (i,j) block in the grid
			int l  = y%Obstacle.getHeight(); // Stubi's y coord in the (i,j) block in the grid
			// collision :
			collisionUpdate(i,j,k,l);
			// dmg
			dmgUpdate(i,j,k,l);
		}
	}
	
	/**
	 * This function is updating the Y position based on the character (jumping or not)
	 * It is based on {@link #jump} & {@link #moveY}
	 */
	public void upY(){
		if (jump > 0) {
			// Jump case
			if (jump < 15) {	// Go higher
				y -= moveY;
				++jump;
			} else if (jump < 30) {	// Go higher more slowly
				y -= moveY/2;
				++jump;				
			} else if (jump == 30) {	// Stand
				++jump;				
			} else if (jump <= 45) {	// Start reaching the ground
				y += moveY/2;
				++jump;
			} else if (jump <60) {	// Start reaching the ground
				y += moveY;
				++jump;
			} else {
				jump = -1;				
			}
		} else {
			// Only gravity :
			y += moveY;
		}
	}
	
	/**
	 * This method update x depending on the previous data we have about it.
	 */
	public void upX(){
		x += moveX;
		if (x+width>level.getLength()){
			x = level.getLength()-width;
		}
		if (x<0) { // begin lock
			x = 0;
		}
	}
	
	/**
	 * This function is applying collision with obstacles, resetting the character at his right place
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param k is the X coordinate within the box
	 */
	public void collisionUpdate(int i, int j, int k, int l) {
		Obstacle[][] obs = level.getObstacles();
		boolean collideBad = true;
		if (i+(height/Obstacle.getHeight())+ 1>=obs.length) {	// Out of the drawing.
			dead = true;
			System.out.println("Below the box");
		} else {
			collisionSide(obs,i,j,k,l);
			collideBad &= collisionFeet(obs,j,k);
			collideBad &= collisionHead(obs,i,j,k);
			if (collideBad)
				System.out.println("BAD COLLISION");
		}
	}
	
	
	/**
	 * This function is testing the collision with the below obstacles, helping {@link #collisionUpdate()}
	 * @param obs is an array of array containing Obstacle
	 * @param j correspond to the x coord of the first box in contact with Stubi
	 * @param k is the position within the box #j
	 * @return a boolean if there is collision or not
	 */
	public boolean collisionFeet(Obstacle[][] obs, int j, int k) {
		// Testing the boxes below Stubi :
		int i = (y+height)/Obstacle.getHeight(); // box below the Nbox (one occupied by Stubi's feet)
		if (i<0) {
			System.out.println("Above the box");
			return false;
		}
		boolean collide = obs[i][j].CollisionTop();	// box below his left foot
		while (k<width){	// try all boxes below Stubi
			collide |= obs[i][++j].CollisionTop();
			k += Obstacle.getWidth();
		}
		if (collide) {
			inAir = false;
			y = i*Obstacle.getHeight() - height;	// Put Stubi back in his N box if collision
		} else
			inAir = true;
		return collide;
	}
	
	/**
	 * This method is used to know if there is a collision at the head of the character and if yes, put him back in place, helping {@link #collisionUpdate()}
	 * @param obs is the array containing obstacles
	 * @param i is the y coord of Stubi's head in the grid
	 * @param j is the x coord of Stubi's head
	 * @param k is the x position inside the box describe by the grid
	 * @return a boolean that say if Stubi collide or not
	 */
	public boolean collisionHead(Obstacle[][] obs,int i, int j, int k) {
		if (i < 0)
			return false;
		boolean collide = obs[i][j].CollisionBot();	// box at his head
		while (k<width){	// try all boxes on Stubi's head line
			collide |= obs[i][++j].CollisionBot();
			k += Obstacle.getWidth();
		}				
		if (collide) {
			y = (i+1)*Obstacle.getHeight();	// Put Stubi back in his N box if collision
		}
		return collide;		
	}
	
	/**
	 * This method deals with the left and right collisions that could occur while playing, helping {@link #collisionUpdate()}
	 * @param obs is the array containing all the obstacles we'll test
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param k is the X coordinate within the box
	 * @return a boolean that say if there has been a collision on Stubi's side
	 */
	public boolean collisionSide(Obstacle[][] obs,int i, int j, int k, int l) {
		// Left side 
		boolean collide = collisionSideHelp(obs,i,j,l);
		while (k<width){	// move to right side
			k += Obstacle.getWidth();
			++j;
		}
		// Right side
		collide |= collisionSideHelp(obs,i,j,l);
		return collide;
	}
	
	/**
	 * This method is here to help the function {@link #collisionSide(Obstacle[][], int, int, int)} to factorize some work
	 * @param obs is the array containing all the obstacles we'll test
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param l is the Y coordinate within the grid
	 * @param move is the movement to apply in case of collision (-1 = to right, +1 = to left)
	 * @return a boolean that say if it has been colliding on the side
	 */
	public boolean collisionSideHelp(Obstacle[][] obs,int i, int j, int l) {
		boolean collide = obs[Math.max(i,0)][j].CollisionBot(); // In case of jump above the roof top limit.
		if ((y+height)/Obstacle.getHeight()<0)
			return false;	// feet above the roof ^^
		while (l<height){
			if (i>=0)
				collide |= obs[i][j].CollisionBot();
			l += Obstacle.getHeight();
			++i;
		}
		if (collide) {
			x -= moveX;	// Put Stubi back in his N box if collision
		}
		return collide;
	}
	
	/**
	 * This function apply the dmg to the character depending on the surrounding obstacles
	 * @param i is the Y coordinate in the grid
	 * @param j is the X coordinate in the grid
	 * @param k is the X coordinate within the (i,j) box
	 * @param l is the Y coordinate within the (i,j) box
	 */
	public void dmgUpdate(int i, int j, int k, int l) {
		Obstacle[][] obs = level.getObstacles();
		int dmg = 0;
		if ((y+height)/Obstacle.getHeight()>=0){ // Could be out of the map
			while (l<height){
				int tmpK = k;
				int tmpJ = j;
				while(tmpK<width && i>=0){
					dmg += obs[i][tmpJ].getDmg();
//					if (obs[i][tmpJ].getDmg()!= 0)		// to print when dmg
//						System.out.println("I:"+i+" J:"+j+" Dmg :"+dmg+" Health:"+health);
					tmpK += Obstacle.getWidth();
					++ tmpJ;
				}
				l += Obstacle.getHeight();
				++i;
			}
		}
		health -= dmg;
		if (health < 0)
			dead = true;
	}
	
	public void print(Graphics g, int lag) {
		if (!dead) {
			update(System.currentTimeMillis());
		   	g.drawImage(getSprite(),lag,y, null);
		}
	}
}
