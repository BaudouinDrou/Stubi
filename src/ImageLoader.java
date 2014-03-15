import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	public BufferedImage load(String path) throws IOException {
		File file = new File(path);
		System.out.println("Path : " + path);
		BufferedImage img = ImageIO.read(file);
		return img;
	}
	
}
