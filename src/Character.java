import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	public int x = 50, y = 360;	// Position
	private int moveFactor = 0;	// Number of pixel to change his position
	private int speed = 3;
	private boolean crawl = false;	// crawling or not
	private int width, height;

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
		this(path, sWidth, sHeight,sequence,50);
	}
	
	public Character(String path, int sWidth, int sHeight, int[]sequence, int speed){
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

		// Frame sequence :
		if (sequence!=null){
			for (int i = 0; i<sequence.length;++i){
				sprites.add(ss.getSprite(sequence[i]));			
			}
		} else {
			sprites.add(ss.getSprite(0));
		}
		
		display = new Animator(sprites);
		display.setSpeed(speed);
	}
	
	public BufferedImage getSprite(){
		return display.sprite;
	}
	
	public void setDisplaySpeed(long n){
		display.setSpeed(n);
	}
	
	public void update(long time){
		display.update(time);
		x += moveFactor;
		if (x + width>Stubi.WINDX)
			x = Stubi.WINDX-width;
		if (x<0)
			x = 0;
	}
	
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
	
	public void setMove(char c) {
		switch (c){
		case 'r':
			moveFactor = speed;
			play();
			break;
		case 'l':
			moveFactor = -speed;
			play();
			break;
		case 's':
			moveFactor = 0;
			stop();
			break;
		default:
			moveFactor = 0;
			stop();
		}
	}
	
	public void setCrawling(boolean b){
		crawl = b;
		if (b)
			speed /= 2;
		else
			speed *= 2;
	}
}
