package game;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	/**
	 * This class has been built in order to make easier the loading of images 
	 */
	
	/**
	 * This method give a BufferedImage from a path to it
	 * @param path the path to the image (usually "img/XXX.png")
	 * @return a BufferedImage
	 * @throws IOException
	 */
	public BufferedImage load(String path) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource(path));
		return img;
	}
	
}
