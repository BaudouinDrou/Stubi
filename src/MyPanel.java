import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	private Character stubi;
	private Background map;
	Image dbImage;
    Graphics dbg;

	public void init() {
		stubi = new Character();
		map = new Background();	
	}
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, null);
//        if (map != null);
//    		g.drawImage(map.img, 0, 0, null);
    }
    
    public void paintComponent(Graphics g){
        if(stubi  != null){
           stubi.update(System.currentTimeMillis());
            g.drawImage(stubi.getSprite(),stubi.x,stubi.y, null);
        }
        repaint();
    }
}
