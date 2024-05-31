import java.util.LinkedList;
import java.util.Queue;

public class MultipleSlotMailbox implements Mailbox {
	
	private Queue<Integer> content = new LinkedList<Integer>();
	public static final int CAPACITY = 10;

	@Override
	public synchronized void put(int number) {
		while(content.size() == CAPACITY) {
			try {
				wait(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.content.offer(number);
		System.out.println(Thread.currentThread().getName()+" : "+number);
		notifyAll();
	}

	@Override
	public synchronized int take() {
		while(content.size() == 0) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		int number = this.content.poll();
		System.out.println(Thread.currentThread().getName()+" : "+number);
		notifyAll();
		return number;
	}

}
