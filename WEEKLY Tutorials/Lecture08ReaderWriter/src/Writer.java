import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Writer extends Thread {

	
	private Account accObj;
	private ReentrantReadWriteLock rw;
	
	public Writer(String name, Account accObj, ReentrantReadWriteLock rw) {
		super(name);
		this.accObj = accObj;
		this.rw = rw;
	}
	
	@Override
	public void run() {
		
		for (int i=0; i<10; i++) {
			rw.writeLock().lock();// same as start_write() in reader writer example in lecture 08
			System.out.println(Thread.currentThread().getName()+" : "+i);
			accObj.setBalance(i);
			rw.writeLock().unlock();// same as end_write() 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	



}
