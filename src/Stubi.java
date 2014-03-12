
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
		System.out.println("A");
		Stubi s = new Stubi();
		System.out.println(s);
	}
}
