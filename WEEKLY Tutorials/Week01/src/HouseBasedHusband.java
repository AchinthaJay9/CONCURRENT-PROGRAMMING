
public class HouseBasedHusband implements Runnable {
	
	private String name;

	private BankAccount account;
	
	public HouseBasedHusband(String name, BankAccount account) {
		this.name = name;
		this.account = account;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			
			try {
				double balance = account.withdraw(1000);
				System.out.println(Thread.currentThread().getName()+" The balance after withdrawal "+balance);
				//Thread.sleep(100);
			} catch (IllegalArgumentException e) {
				System.out.println("Insufficient balance");
			} /*
				 * catch(InterruptedException e) { System.out.println("Interrupted"); }
				 */
			
		}
		
	}

}
