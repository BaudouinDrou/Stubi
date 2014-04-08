package game;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Background {
	public SpriteSheet ss;
	private int x = 0;
	private int xMax = 0;
	private int width;
	
	public Background(String path) {
		ImageLoader loader = new ImageLoader();
		BufferedImage img = null;
		try {
			img = loader.load(path);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		ss = new SpriteSheet(img,Stubi.WINDX,Stubi.WINDY);
		width = img.getWidth(); 
		xMax =  width - Stubi.WINDX;
	}
	
	public int getX() {
		return x;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void update(int n){
		x = Math.min(x + n,xMax);
	}
	
	public BufferedImage getImg(){
		return ss.getSprite(x,0);
	}
	
}
