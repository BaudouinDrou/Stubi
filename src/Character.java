import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	public int x = 50, y = 50;	// Position

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
		for (int i = 0; i<sequence.length;++i){
			sprites.add(ss.getSprite(sequence[i]));			
		}
		
		display = new Animator(sprites);
		display.setSpeed(50);
        display.play();
	}
	
	public BufferedImage getSprite(){
		return display.sprite;
	}
	
	public void setDisplaySpeed(long n){
		display.setSpeed(n);
	}
	
	public void update(long time){
		display.update(time);
		x += 2;
		x %= Stubi.WINDX;
		y += 1;
		y %= Stubi.WINDY;
	}
	
	public void play(){
		display.play();
	}
}
