package game;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	public BufferedImage load(String path) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource(path));
		return img;
	}
	
}
