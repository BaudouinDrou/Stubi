import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class MyWindow implements ActionListener {
	private int moveState = 0;	
	private int[] movePattern = {1,2,3,4,5,4,3,2};
	private int moveIndex = 0;
	
	private Timer trigger;
	private int dimX = 900;
	private int dimY = 350;
	
	private int reloadTime = 350;
	private int rdzX1 = 150;	// Adapt with character size ...
	private int rdzY1 = 0;
	private int rdzX2 = 650;
	private int rdzY2 = 350;
	private MyImage img;
	
	public MyWindow(){
		moveState = 0;
		trigger = new Timer(reloadTime, this);
		trigger.start();
		Dimension size = new Dimension(dimX, dimY);
		img = new MyImage(moveState,size);
	}
	
	public MyImage getImg(){
		return this.img;
	}
	
	public String toString(){
		return "The character is in step # : " + moveState;
	}
	
	public void changeState(int n) {
		this.moveIndex += n;
		this.moveIndex %= movePattern.length;
		this.moveState = movePattern[moveIndex];
	}
	
	public void changeState() {
		changeState(1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.trigger) {
			this.changeState(1);
			this.getImg().reloadPerso(moveState);
			this.getImg().repaint(rdzX1, rdzY1, rdzX2, rdzY2);	// Zone to be redrawn
		}
		else {
			String commande = e.getActionCommand();
			if (commande.equals("arreter"))
 				this.trigger.stop();
			else if(commande.equals("reprendre"))
				this.trigger.restart();
		}
	}
	
	public void printAnimation(){
		JFrame window = new JFrame();
        window.setContentPane(this.getImg());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocation(300, 100);	// Place the windows in x=300 y=100 (starting from top right corner)
		window.setVisible(true);
	}
	
}
