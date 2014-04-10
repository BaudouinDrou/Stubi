package game;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class SpriteSheet {
	/**
	 * This class contain all necessary functions to use sprites
	 */
	private BufferedImage spriteSheet;
	private int sWidth, sHeight;
	
	public SpriteSheet(BufferedImage ss){
		this(ss,50,50);
	}
	
	/**
	 * This is the main constructor of the class, building a sprite sheet from its path
	 * @param path is the path to the image (often "img/XXX.png")
	 * @param width is the width of one sprite
	 * @param height is the height of one sprite
	 */
	public SpriteSheet(String path, int width, int height){
		ImageLoader loader = new ImageLoader();
		spriteSheet = null;
		try {
			spriteSheet = loader.load(path);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		this.sWidth = width;
		this.sHeight = height;
	}
	
	/**
	 * This constructor is useful if the image is already loaded from its path otherwise, use the main constructor
	 * @param ss is the image which will be the sprite sheet
	 * @param width is the width of one sprite
	 * @param height is the height of one sprite
	 */
	public SpriteSheet(BufferedImage ss, int width, int height){	// width and height are the size of a sprite
		this.spriteSheet = ss;
		this.sWidth = width;
		this.sHeight = height;
	}
	
	/**
	 * This get function return the width of one sprite
	 * @return the width of one sprite
	 */
	public int getWidth(){
		return sWidth;
	}
	
	/**
	 * This get function return the height of one sprite
	 * @return the height of one sprite
	 */	
	public int getHeight(){
		return sHeight;
	}
	
	/**
	 * This function is here to set the size of one Sprite if it hasn't been correctly set
	 * @param width is the width of a sprite
	 * @param height is the height of a sprite
	 */
	public void setSize(int width, int height){
		this.sWidth = width;
		this.sHeight = height;
	}
	
	/**
	 * This method is giving a sprite depending on its position in the sprite sheet
	 * @param n is the position in the sprite sheet (starting from 0)
	 * @return a BufferedImage, corresponding to sprite at nth position 
	 */
	public BufferedImage getSprite(int n){
		int x = n*sWidth;	// Supposing the Sprites are in line
		return this.spriteSheet.getSubimage(x, 0, sWidth, sHeight);
	}
	
	/**
	 * This method is giving a sprite depending on its coords on the sprite sheet
	 * @param x is the X coordinate 
	 * @param y is the Y coordinate
	 * @return a BufferedImage, corresponding to sprite at nth position
	 */
	public BufferedImage getSprite(int x, int y){
		return this.spriteSheet.getSubimage(x, y, sWidth, sHeight);
	}
}
