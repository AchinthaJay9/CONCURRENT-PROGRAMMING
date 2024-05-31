
public class Test {

	public static void main(String[] args) {
		Plate plate = new Plate();
		
		Runnable mum = new Mother(plate);
		Runnable kid = new Child(plate);
		
		Thread momThread = new Thread(mum, "Mother");
		Thread childThread = new Thread(kid, "Child");
		
		momThread.start();
		childThread.start();
		
	}

}
