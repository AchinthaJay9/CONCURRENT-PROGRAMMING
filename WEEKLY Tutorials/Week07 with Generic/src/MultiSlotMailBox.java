import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MultiSlotMailBox<T> implements Mailbox<T> {
	
	public static final int CAPACITY = 20;
	
	Semaphore freeSlots = new Semaphore(CAPACITY, true);
	Semaphore availableSlots = new Semaphore(0, true);
	Semaphore mutex = new Semaphore(1, true);
	
	Queue<T> queue = new LinkedList<T>();

	@Override
	public void put(T value) {
		try {
			freeSlots.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.offer(value);
		mutex.release();
		availableSlots.release();
		
	}

	@Override
	public T get() {
		try {
			try {
				availableSlots.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		return queue.poll();
		}finally {
			mutex.release();
			freeSlots.release();
			
		}
	}

}
