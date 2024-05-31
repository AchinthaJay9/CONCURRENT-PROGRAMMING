
public class CareerMindedWife implements Runnable {

	private Account obj;
	
	public CareerMindedWife(Account obj) {
		super();
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			obj.deposit(5000);
		}
		
	}

}
