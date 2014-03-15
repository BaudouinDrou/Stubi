import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Character stubi;
	private Background map;
	
	Image dbImage;
    Graphics dbg;

	public void init() {
		int[] seq = {1,2,3,4,5,4,3,2};
		stubi = new Character("img/perso-sprite.png",90,100,seq);
		map = new Background("img/bg.png");
	}
	
	public Background getMap(){
		return map;
	}
	
	public Character getStubi(){
		return stubi;
	}
	
	@Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, null);
    }
    
    public void paintComponent(Graphics g){
	    // Map
    	if (map!=null)
    		g.drawImage(getMap().getImg(), 0, 0, null);
	    
	    // Character
	    if(stubi  != null){
	       stubi.update(System.currentTimeMillis());
	        g.drawImage(getStubi().getSprite(),stubi.x,stubi.y, null);
	    }		    
	    repaint();
    }
}
