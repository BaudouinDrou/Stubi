package game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	private volatile Level level;
	
	public int x = 150, y = 360;	// Starting Position
	private int rX = x, rY = y;		// Real position
	private int moveFactor = 0;	// Number of pixel to change his position
	private int speed = 3;
	private int width, height;
	
	private long previousTime = 0;
	private int timeFrame;
	
	private int jump = 0;
	private int gravity = 6;
	
	private boolean dead = false;

	// Constructors
	
	public Character(String path, Level lvl){
		this(path, 50, 100, null, lvl);
	}
	
	public Character(String path, int sWidth, int sHeight, int[]sequence, Level lvl){
		this(path, sWidth, sHeight,sequence,MyPanel.gameTimeFrame, lvl);
	}
	
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
		sprites.add(ss.getSprite(0));
		
		// Frame sequence (walk/run) :		
		if (sequence!=null){
			for (int i = 0; i<sequence.length;++i){
				sprites.add(ss.getSprite(sequence[i]));			
			}
		} // If sequence empty act as no walk
		
		display = new Animator(sprites);
		display.setSpeed(timeFrame);
		
		// Setting time settings
		this.timeFrame = timeFrame;
		previousTime = 0;
		
		// Level 
		this.level = level;
	}
	
	// Getters Setters
	
	public BufferedImage getSprite(){
		return display.sprite;
	}
	
	public boolean dead() {
		return dead;
	}
	
	public void setDisplaySpeed(long n){
		display.setSpeed(n);
	}
	
	public void setMove(char c) {
		switch (c){
		case 'r':	// right
			moveFactor = speed;
			play();
			break;
		case 'l':	// left
			moveFactor = -speed;
			play();
			break;
		case 's':	// stand
			moveFactor = 0;
			stop();
			break;
		case 'j':	// jump
			if (jump == 0)
				jump = 1;
			break;
		default:
			moveFactor = 0;
			stop();
			break;
		}
	}
	
	public void setCrawling(boolean b){
		if (b)
			speed /= 2;
		else
			speed *= 2;
	}
	
	// Services
	
	public void play(){
		display.play();
	}
	
	public void stop(){
		display.stop();
	}
	
	public void resume(){
		display.resume();
	}
	
	public void pause(){
		display.pause();
	}

	// Other functions
	
	public void update(long time){
		display.update(time);
		if(time - previousTime >= timeFrame) {
			gravityUpdate(time);
			// movement
			x += moveFactor;
			rX += moveFactor;
			
			// Right side limit for the character
			if (x+width>Stubi.WINDX-250) {
				if (!level.getBackground().update(moveFactor))
					x = Stubi.WINDX-250-width;
				else // end of the lvl
					x = Math.min(Stubi.WINDX-width,x);
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
	
	private void gravityUpdate(long time){
		if (jump != 0) {
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
				jump = 0;				
			}
		} else {
			// Gravity :
			y += gravity;
		}
		
		Obstacle[][] obs = level.getObstacles();
		
		// Testing the boxes below Stubi :
		int i = (y+height)/50; // box below the Nbox (one occupied by Stubi's feet) ex : y = 365 => i = 9 (start count at 0 !!!), 10th line
		int j = rX/50;
		if (i>=obs.length) {
			dead = true;
		} else {
			if (obs[i][j].collision() || obs[i][j+1].collision())
				y = i*50 - height -1;	// Put Stubi back in his N box if collision i = 9 => y = 350
		}
	}
}
