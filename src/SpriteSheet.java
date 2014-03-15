import java.awt.image.BufferedImage;


public class SpriteSheet {
	private BufferedImage spriteSheet;
	private int sWidth = 200, sHeight = 271;
	
	public SpriteSheet(BufferedImage ss){
		this.spriteSheet = ss;
	}
	
	public BufferedImage getSprite(int n){
		int x = n*sWidth;	// Supposing the Sprites are in line
		return this.spriteSheet.getSubimage(x, 0, sWidth, sHeight);
	}
}
