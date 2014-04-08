package game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private volatile Character stubi;
	private volatile Level level;
	
	static int gameTimeFrame = 50;
	
	Image dbImage;
    Graphics dbg;

	public void init() {
		int[] seq = {1,2,3,4,5,4,3,2};
		stubi = new Character("img/perso-sprite.png",90,100,seq,gameTimeFrame);
		level = new Level(1);
	}
	
	public Character getStubi(){
		return stubi;
	}
	
	public void pause(){
		// executed when pressed esc
		stubi.pause();
	}
	
	public void resume(){
		// executed when resumed
		stubi.resume();
	}
	
	@Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);	// Calling the method below
        g.drawImage(dbImage, 0, 0, null);
    }
    
    public void paintComponent(Graphics g){
	    // Level Printing
    	if (level!=null){
    		Background bg = level.getBackground(); 
    		bg.update(1);
    		g.drawImage(bg.getImg(), 0, 0, null);
    		int x = bg.getX();
    		int j0 = x/50;
    		int j1 = j0 + Stubi.WINDX/50 + 1;
    		Obstacle[][] obs = level.getObstacles();
    		for (int i = 0; i<obs.length;++i){	// i = x coord
    			for (int j = j0; j<obs[i].length && j<j1; ++j) { // j = y coord
    				if (obs[i][j] != null) {
    					Obstacle tmp = obs[i][j];
    					g.drawImage(tmp.getImg(),tmp.getX()-x,tmp.getY(), null);
    				}
    			}
    		}
    	}
	    
	    // Character
	    if(stubi  != null){
	    	stubi.update(System.currentTimeMillis());
	        g.drawImage(getStubi().getSprite(),stubi.x,stubi.y, null);
	    }		    
	    repaint();
    }
}
