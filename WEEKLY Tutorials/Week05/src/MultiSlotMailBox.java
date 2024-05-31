import java.util.LinkedList;
import java.util.Queue;

public class MultiSlotMailBox implements MailBox {
	
	private Queue<Integer> queue = new LinkedList<Integer>();
	private final int MAX_NO_OF_ITEM = 10;

	@Override
	public synchronized void put(int value) {
		while(queue.size() == MAX_NO_OF_ITEM) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+" produced : "+value);
		queue.offer(value);
		notifyAll();

	}

	@Override
	public synchronized int get() {
		while(queue.isEmpty()) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		System.out.println(Thread.currentThread().getName()+" comsumed : "+queue.peek());
		return queue.poll();
	}

}
