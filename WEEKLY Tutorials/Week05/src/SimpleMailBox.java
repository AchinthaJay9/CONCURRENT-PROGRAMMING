
public class SimpleMailBox implements MailBox {
	
	private int content;
	public boolean available;

	@Override
	public synchronized void put(int value) {
		
		while (available) {
			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.content = value;
		this.available = true;
		notifyAll();

	}

	@Override
	public synchronized int get() {
		
		while(!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.available = false;
		notifyAll();
		return content;
	}

}
