
public class Consumer extends Thread {
	
	private Mailbox mailbox;
	private int noOfItems;
	
	public Consumer(int id, Mailbox mailbox, int noOfItems) {
		super("Consumer#"+id);
		this.mailbox = mailbox;
		this.noOfItems = noOfItems;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < noOfItems; i++) {
			int value = mailbox.take();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName()+" : "+value);
		}
	}

}
