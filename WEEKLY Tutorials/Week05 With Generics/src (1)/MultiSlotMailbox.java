import java.util.LinkedList;
import java.util.Queue;

public class MultiSlotMailbox<T> implements Mailbox<T> {
	
	private Queue<T> content = new LinkedList<T>();
	private final int MAX_NO_ITEMS = 5;

	@Override
	public synchronized void put(T value) {
		while(content.size() == MAX_NO_ITEMS) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.content.offer(value);
		System.out.println(Thread.currentThread().getName()+" produced "+value);
		notifyAll();
		
	}

	@Override
	public synchronized T get() {
		while(content.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(Thread.currentThread().getName()+" consumed "+content.peek());
		notifyAll();
		return this.content.poll();
	}

}
