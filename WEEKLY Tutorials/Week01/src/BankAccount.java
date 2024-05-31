
public class BankAccount {
	
	private double balance;
	private String accNo;
	
	public BankAccount(double balance, String accNo) {
		super();
		this.balance = balance;
		this.accNo = accNo;
	}
	
	public synchronized double deposit(double amount) {
		balance += amount;
		return balance;
	}
	
	public synchronized double withdraw(double amount) {
		if (amount <= balance) {
			balance -= amount;
		} else {
			throw new IllegalArgumentException("Insufficient balance!!!");
		}
		return balance;
	}

	public synchronized double getBalance() {
		return balance;
	}

	public String getAccNo() {
		return accNo;
	}
	
}
