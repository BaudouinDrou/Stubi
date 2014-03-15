import javax.swing.JFrame;


public class Stubi extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel pan;
	public static int WINDX = 800;
	public static int WINDY = 600;
	
	public Stubi(){
		setTitle("Stubi");
		setSize(WINDX, WINDY);
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        pan = new MyPanel();
        setContentPane(pan);
        pan.init();
        

        pan.getStubi().play();
        try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        pan.getStubi().stop();
	}
	
	public String toString(){
		return "Successful test !";
	}
    
    public static void main(String [] arg) {
		Stubi test = new Stubi();
		System.out.println(test);
	}
}