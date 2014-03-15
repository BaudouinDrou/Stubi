import java.awt.image.BufferedImage;
import java.io.IOException;


public class Background {
	public BufferedImage img;

	public Background() {
		this("img/bg.png");
	}
	
	public Background(String path) {
		ImageLoader loader = new ImageLoader();
		try {
			img = loader.load(path);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
	public BufferedImage getImg(){
		return img;
	}
	
}
