import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class SimpleMailBox implements MailBox {
	
	private Queue<Integer> content = new LinkedList<Integer>();
	public static final int CAPACITY = 10;

	
	private Semaphore freeSlot = new Semaphore(CAPACITY);
	private Semaphore availableSlot = new Semaphore(0);
	
	
	public int take() {	
		try {
			try {
				availableSlot.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("["+Thread.currentThread().getName() +" consumed the data  : "+this.content.peek()+"]");
			
			return content.poll();
		} finally {
			freeSlot.release();
		}	
	}

	public void put(int data) {
		try {
			freeSlot.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.content.offer(data);
		//System.out.println("["+Thread.currentThread().getName() +" produced  : "+data+"]");
		availableSlot.release();

		
	}
	
	

}
