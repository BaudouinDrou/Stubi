import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MyImage extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image bg;	// Background pic
	private Image perso;
	private String path = "../img/";	// On eclipse : "img/" On Windows : "../img/"

	MyImage(int state,Dimension size) {
		try {
			this.bg = ImageIO.read(new File(path + "bg.png"));
			this.perso = ImageIO.read(new File(path + "perso"+ state +".png"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		setPreferredSize(size);
		setBackground(Color.WHITE);
	}
	
	public void reloadPerso(int state){
		try {
			this.perso = ImageIO.read(new File(path + "perso"+ state +".png"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(this.perso, 30, -130, this);
	}
}