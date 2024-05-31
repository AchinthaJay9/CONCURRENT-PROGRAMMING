
public class CareerMindedWife implements Runnable {
	
	private String name;
	private BankAccount account;
	
	public CareerMindedWife(String name, BankAccount account) {
		this.name = name;
		this.account = account;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			
			double balance = account.deposit(1000);
			System.out.println(Thread.currentThread().getName()+" The balance after withdrawal "+balance);
			
			/*try {			
			//	Thread.sleep(100);
			}  catch(InterruptedException e) {
				System.out.println("Interrupted");
			}*/
			
		}
		
	}



}
