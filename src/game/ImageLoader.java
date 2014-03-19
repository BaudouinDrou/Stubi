package game;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	public BufferedImage load(String path) throws IOException {
//		File file = new File(path);
//		BufferedImage img = ImageIO.read(file);
		
		BufferedImage img = ImageIO.read(getClass().getResource(path));
		return img;
	}
	
}
