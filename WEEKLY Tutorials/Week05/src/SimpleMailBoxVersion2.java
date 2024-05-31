import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleMailBoxVersion2 implements MailBox {
	
	private int content;
	private boolean available;
	
	Lock lock = new ReentrantLock();
	Condition bufferFull = lock.newCondition();
	Condition bufferEmpty = lock.newCondition();

	@Override
	public void put(int value) {
		lock.lock();
		while (available) {
			try {
				bufferFull.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		content = value;
		available = true;
		System.out.println(Thread.currentThread().getName()+" produced : "+value);
		bufferEmpty.signalAll();
		lock.unlock();
		
	}

	@Override
	public int get() {
		lock.lock();
		while(!available) {
			try {
				bufferEmpty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = false;
		System.out.println(Thread.currentThread().getName()+" consumed : "+content);
		bufferFull.signalAll();
		lock.unlock();
		return content;
	}

}
