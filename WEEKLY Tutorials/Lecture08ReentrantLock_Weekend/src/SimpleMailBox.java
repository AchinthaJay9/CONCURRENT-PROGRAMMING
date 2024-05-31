import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleMailBox implements MailBox {
	
	private Lock lock = new ReentrantLock();
	
	private Condition freeSlot = lock.newCondition();
	private Condition availableSlot = lock.newCondition();
	
	private int content;
	private boolean available = false;

	public  int take() {
		lock.lock();
		try {
			while(!available) {
				try {
					availableSlot.await(); // the current thread goes into wait state and put into waitset on availableSlot condition (available == false)
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			available=false;
			freeSlot.signalAll();
			return content;
		} finally {
			lock.unlock();
		}
		
	}

	public  void put(int data) {
		lock.lock();
		try {
			while(available) {
				try {
					freeSlot.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.content = data;
			available = true;
			availableSlot.signalAll();
		} finally {
			lock.unlock();
		}
		
	}
	
	

}
