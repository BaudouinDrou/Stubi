import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	private Character stubi;
	private Background map;
	
	Image dbImage;
    Graphics dbg;

	public void init() {
		int[] seq = {1,2,3,4,5,4,3,2};
		stubi = new Character("img/perso-sprite.png",87,100,seq);
		map = new Background("img/bg.png");
	}
	
	@Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, null);
//		Image img = null;
//		try {
//			img = ImageIO.read(new File("img/bg.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        g.drawImage(img, 0, 0, null);
    }
    
    public void paintComponent(Graphics g){
	    // Map
    	if (map!=null)
    		g.drawImage(map.getImg(), 0, 0, null);
	    
	    // Character
	    if(stubi  != null){
	       stubi.update(System.currentTimeMillis());
	        g.drawImage(stubi.getSprite(),stubi.x,stubi.y, null);
	    }		    
	    repaint();
    }
}
