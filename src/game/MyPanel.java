package game;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	/**
	 * This class contain the JPanel where the game will take place
	 */
	private static final long serialVersionUID = 1L;
	private volatile Character stubi;
	private volatile Level level;
	
	static int gameTimeFrame = 50;
	
	Image dbImage;
    Graphics dbg;
    
    /**
     * This is the default constructor
     */
	public MyPanel() {
		int lvl = 1;
		genObstacle.init(lvl);
		int[] seq = {1,2,3,4,5,4,3,2};
		level = new Level(lvl);
		stubi = new Character("img/perso-sprite.png",90,100,seq,gameTimeFrame,level);
	}
	
	/**
	 * This method give the main character which is included inside the JPanel
	 * @return the main Character which name is Stubi
	 */
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
    
	/**
	 * This method print the components on the JPanel
	 * @param g is a Graphics object used by the paint method
	 */
    public void paintComponent(Graphics g){
	    // Level Printing
    	if (level!=null){
    		Background bg = level.getBackground();
    		g.drawImage(bg.getImg(), 0, 0, null);
    		int x = bg.getX();
    		int j0 = x/genObstacle.sWidth;
    		int j1 = j0 + Stubi.WINDX/genObstacle.sWidth + 1;
    		Obstacle[][] obs = level.getObstacles();
    		for (int i = 0; i<obs.length;++i){	// i = y coord
    			for (int j = j0; j<obs[i].length && j<j1; ++j) { // j = x coord
    					Obstacle tmp = obs[i][j];
    					if (tmp.visible())
    						g.drawImage(tmp.getImg(),tmp.getX()-x,tmp.getY(), null);
    			}
    		}
    	}
    	// Character Printing
	    if(stubi  != null){
	    	stubi.update(System.currentTimeMillis());
	        g.drawImage(getStubi().getSprite(),stubi.x,stubi.y, null);
	    }
    	
	    repaint();
    }
}
