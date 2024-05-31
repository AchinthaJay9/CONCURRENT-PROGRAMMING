import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader extends Thread {
	
	private Account accObj;
	private ReentrantReadWriteLock rw;
	
	public Reader(String name, Account accObj, ReentrantReadWriteLock rw) {
		super(name);
		this.accObj = accObj;
		this.rw = rw;
	}
	
	@Override
	public void run() {
		
		for (int i=0; i<10; i++) {
			rw.readLock().lock();
			System.out.println(Thread.currentThread().getName()+" : "+accObj.getBalance());
			rw.readLock().unlock();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
