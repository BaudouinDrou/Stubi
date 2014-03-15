import java.awt.image.BufferedImage;
import java.io.IOException;


public class Background {
	public BufferedImage img;
	
	public Background() {
		ImageLoader loader = new ImageLoader();
		try {
			img = loader.load("img/bg.png");
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
}
