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
    	update();
	    level.print(g,stubi.x);
	    stubi.print(g);
	    repaint();
    }
    
    public void update() {
    	int x = stubi.x;
    	x = x / level.scale;
    	level.getBackground().setX(x);
    }
}
