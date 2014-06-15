package main;
import game.Level;
import game.MainCharacter;
import game.genObstacle;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	/**
	 * This class contain the JPanel where the game will take place
	 */
	private static final long serialVersionUID = 1L;
	private volatile MainCharacter stubi;
	private volatile Level level;
	
	public static int gameTimeFrame = 50;
	
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
		stubi = new MainCharacter("img/perso-sprite.png",90,100,seq,gameTimeFrame,level);
	}
	
	/**
	 * This method give the main character which is included inside the JPanel
	 * @return the main Character which name is Stubi
	 */
	public MainCharacter getStubi(){
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
    	int lag = stubi.lag;
    	update(lag);
	    level.print(g,stubi.X(),lag);
	    stubi.print(g,lag);
	    repaint();
    }
    
    public void update(int lag) {
    	int x = stubi.X();
    	x = x / level.scale;
    	level.getBackground().setX(x);
    }
}
