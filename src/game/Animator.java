package game;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Animator {
	/**
	 * This class is mainly used to have animation for the characters
	 */
	private ArrayList<BufferedImage> frames;
    public BufferedImage sprite;
	    
    private volatile boolean running = false;
	    
    private long previousTime, refreshTime;
    private int frameAtPause, currentFrame;
    
    /**
     * Main constructor.
     * @param frames is an ArrayList of BufferedImage that will be printed on the screen
     */
    public Animator(ArrayList<BufferedImage> frames){
        this.frames = frames;
    }
    
    public void setRefreshTime(long refreshTime){
        this.refreshTime = refreshTime;
    }
    
    /**
     * This is the core method of this class that update the animation every refreshTime ms.
     * @param time
     */
    public void update(long time){
        if(running){
            if(time - previousTime >= refreshTime){
                //Update the animation
                currentFrame++;
                try{
                    sprite = frames.get(currentFrame);
                } catch(IndexOutOfBoundsException e){
                    currentFrame = 1;
                    sprite = frames.get(currentFrame);
                }
                previousTime = time;
            }
        }
    }
    
    public void play(){
        running = true;
        previousTime = 0;
        frameAtPause = 1;
        currentFrame = 1;
    }
    
    public void stop(){
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    
    public void pause(){
        frameAtPause = currentFrame;
        running = false;
    }
    
    public void resume(){
        currentFrame = frameAtPause;
        running = true;
    }
    
}
