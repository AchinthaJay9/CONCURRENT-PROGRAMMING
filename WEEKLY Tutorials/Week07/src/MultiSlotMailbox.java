import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MultiSlotMailbox implements MailBox {
	
	private final int CAPACITY = 10;
	
	private Semaphore mutex = new Semaphore(1);
	private Semaphore freeslots = new Semaphore(CAPACITY);
	private Semaphore itemslots = new Semaphore(0);
	
	//freeslots + itemslots = CAPACITY
	
	private Queue<String> slots = new LinkedList<String>();
	

	@Override
	public void put(String value) {
		try {
			freeslots.acquire();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		slots.offer(value);//critical section
		
		mutex.release();
		itemslots.release();
	}

	@Override
	public String get() {
		try {
			try {
				itemslots.acquire();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			return slots.poll();//critical section
		}
		finally {
			mutex.release();
			freeslots.release();
		}
	}

}
