import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	public int x = 50, y = 350;	// Position

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
		x += 3;
		x %= Stubi.WINDX;
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
	
	public void move() {
		// TO DO
	}
}
