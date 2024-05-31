import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultipleSlotMailbox implements Mailbox {
	
	//private int content;// queue
	private Queue<Integer> content = new LinkedList<Integer>();
	public static final int CAPACITY = 10;
	private Lock lock = new ReentrantLock();
	//private boolean available = false;//no need instead add a constant to indicate maximum capacity of queue
	
	private Condition queueFull = lock.newCondition();
	private Condition queueEmpty = lock.newCondition();

	@Override
	public void put(int number) {
		lock.lock();
		
		try {
			while (content.size() == CAPACITY) {
				try {
					queueFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.content.offer(number);
			//available = true;
			System.out.println("put()"+Thread.currentThread().getName()+" : "+number);
			queueEmpty.signalAll();
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public void take() {
		lock.lock();
		
		try {
			while (content.isEmpty()) {
				try {
					queueEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("get()"+Thread.currentThread().getName()+" : "+content.poll());
			//available = false;
			queueFull.signalAll();
			
			//return this.content;
		} finally {
			lock.unlock();
		}
	}

}
