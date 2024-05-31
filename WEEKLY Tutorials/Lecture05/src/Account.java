
public class Account {
	
	private String accNo;
	private double balance;
	
	public Account(String accNo, double balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	public String getAccNo() {
		return accNo;
	}
	
	public synchronized void deposit(double amt) {
		while(this.balance >= 5000) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		System.out.println(Thread.currentThread().getName()+" : "+
				"Available Balance before Deposit "+this.balance);
		this.balance+=amt;
		System.out.println(Thread.currentThread().getName()+" : "+
				"Available Balance after Deposit "+this.balance);
		notifyAll();
	}
	
	public synchronized void withdraw(double amt) {
		while(this.balance < 5000) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+" : "+
				"Available Balance before withdrawal "+this.balance);
		this.balance -= amt;
		System.out.println(Thread.currentThread().getName()+" : "+
				"Available Balance after withdrawal "+this.balance);
		notifyAll();
		
	}
	public synchronized double getBalance() {
		return balance;
	}
	
	

}
