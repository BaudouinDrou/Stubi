package game;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Background {
	/**
	 * This class is used to set the background of a level
	 */
	public SpriteSheet ss;
	private int x = 0;
	private int xMax;
	private int width;
	
	/**
	 * Main constructor. 
	 * The image is loaded on our own because we need its width
	 * @param path is the path to the Background image
	 */
	public Background(String path) {
		ImageLoader loader = new ImageLoader();
		BufferedImage img = null;
		try {
			img = loader.load(path);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		ss = new SpriteSheet(path,Stubi.WINDX,Stubi.WINDY);
		width = img.getWidth(); 
		xMax =  width - Stubi.WINDX;
	}
	
	public int getX() {
		return x;
	}
	
	public int getWidth(){
		return width;
	}
	
	/**
	 * This method is called to move the background of several pixels
	 * @param n is the number of pixels to make the background move of
	 * @return a boolean that say if we reached the end of the level (x == xMax)
	 */
	public boolean update(int n){
		x = Math.min(x + n,xMax);
		if (x<0)
			x = 0;
		return (x==xMax);
	}
	
	/**
	 * This method return the image to be print on the screen
	 * @return a Buffered image to be print in the MyPanel
	 */
	public BufferedImage getImg(){
		return ss.getSprite(x,0);
	}
	
}
