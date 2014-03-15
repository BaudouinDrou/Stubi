import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;


public class Stubi extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Character stubi;
	private Background map;
	
	public Stubi(){
		setTitle("Stubi");
		setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
	}
	
	public void init() {
		stubi = new Character();
		map = new Background();	
	}
	
	Image dbImage;
    Graphics dbg;
    
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
    
    public static void main(String [] arg) {
		Stubi test = new Stubi();
	}
}