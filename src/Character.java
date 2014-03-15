import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Character {
	private Animator display;
	public int x = 50, y = 50;	// Position
	
	public Character(){
		ImageLoader loader = new ImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.load("img/SpriteCharacter.png");
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
		
		
		// Frame sequence :
		sprites.add(ss.getSprite(0));
		sprites.add(ss.getSprite(1));
		
		display = new Animator(sprites);
		display.setSpeed(200);
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
