
public class HouseBasedHusband implements Runnable {
	
	private Account obj;
	
	public HouseBasedHusband(Account obj) {
		super();
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			obj.withdraw(5000);
		}

	}
	

}
