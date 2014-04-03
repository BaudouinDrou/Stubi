package game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	public int x = 50, y = 360;	// Position
	private int moveFactor = 0;	// Number of pixel to change his position
	private int speed = 3;
	private int width, height;
	
	private long previousTime = 0;
	private int timeFrame;
	
	private int jump = 0;
	private int jumpSize = 5;

	// Constructors
	
	public Character(){
		this("img/perso-sprite.png");
	}
	
	public Character(String path){
		this(path, 50, 100);
	}
	
	public Character(String path, int sWidth, int sHeight){
		this(path, sWidth, sHeight,null);
	}
	
	public Character(String path, int sWidth, int sHeight, int[]sequence){
		this(path, sWidth, sHeight,sequence,MyPanel.gameTimeFrame);
	}
	
	public Character(String path, int sWidth, int sHeight, int[]sequence, int timeFrame){
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
	}
	
	// Getters Setters
	
	public BufferedImage getSprite(){
		return display.sprite;
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
			// movement
			x += moveFactor;
			jumpUpdate(time);
			
			// Right and left side limit for the character :
			if (x+width>Stubi.WINDX)
				x = Stubi.WINDX-width;
			if (x<0)
				x = 0;
			// Ground limit :
			if (y+height>Stubi.WINDY)
				y = Stubi.WINDY-height;
			// Rooftop limit :
			if (y<0)
				y = 0;
		}
	}
	
	private void jumpUpdate(long time){
		if (jump != 0) {
			if (jump < 15) {	// Go higher
				y -= jumpSize;
				++jump;
			} else if (jump < 30) {	// Go higher more slowly
				y -= jumpSize/2;
				++jump;				
			} else if (jump == 30) {	// Stand
				++jump;				
			} else if (jump <45) {	// Start reaching the ground
				y += jumpSize/2;
				++jump;
			} else if (jump <60) {	// Start reaching the ground
				y += jumpSize;
				++jump;
			} else {
				jump = 0;				
			}
		}
	}
}
