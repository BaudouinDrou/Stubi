import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Stubi {
	private int a;
	
	public Stubi(){
		this.a = 0;
	}
	
	public int getA(){
		return this.a;
	}
	
	public String toString(){
		return "A equal : " + a;
	}
	
	public static void main(String [] arg){
		// test 1
		System.out.println("A");
		Stubi s = new Stubi();
		System.out.println(s);
		
		// window test
		JFrame window = new JFrame();
        window.setContentPane(new IHMImages());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocation(300, 100);	// Place the windows in x=300 y=100 (starting from top right corner)
		window.setVisible(true);
	}
}

class IHMImages extends JPanel {
	private static final long serialVersionUID = 1L;
	Image img1, img2;

	IHMImages() {
		try {
			img1 = ImageIO.read(new File("img/perso.jpg"));
			img2 = ImageIO.read(new File("img/char.png"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(img2, 30, 30, this);
	}
}