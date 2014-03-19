package game;
import java.awt.image.BufferedImage;


public class SpriteSheet {
	private BufferedImage spriteSheet;
	private int sWidth, sHeight;
	
	public SpriteSheet(BufferedImage ss){
		this(ss,50,50);
	}
	
	public SpriteSheet(BufferedImage ss, int width, int height){	// width and height are the size of a sprite
		this.spriteSheet = ss;
		this.sWidth = width;
		this.sHeight = height;
	}
	
	public int getWidth(){
		return sWidth;
	}
	
	public int getHeight(){
		return sHeight;
	}
	
	public void setSize(int width, int height){
		this.sWidth = width;
		this.sHeight = height;
	}
	
	public BufferedImage getSprite(int n){
		int x = n*sWidth;	// Supposing the Sprites are in line
		return this.spriteSheet.getSubimage(x, 0, sWidth, sHeight);
	}
	
	public BufferedImage getSprite(int x, int y){
		return this.spriteSheet.getSubimage(x, y, sWidth, sHeight);
	}
}
