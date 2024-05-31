
public class SimpleMailbox<T> implements Mailbox<T> {
	
	private T content;
	private boolean available;

	@Override
	public synchronized void put(T value) {
		while(available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.content = value;
		System.out.println(Thread.currentThread().getName()+" produced "+content);
		available = true;
		notifyAll();
		
	}

	@Override
	public synchronized T get() {
		while(!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = false;
		System.out.println(Thread.currentThread().getName()+" consumed "+content);
		notifyAll();
		return this.content;
		
	}

}
