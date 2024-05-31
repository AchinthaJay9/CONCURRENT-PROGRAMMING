
public class Producer extends Thread {
	
	private Mailbox mailbox;
	private int noOfItems;
	
	public Producer(int id, Mailbox mailbox, int noOfItems) {
		super("Producer#"+id);
		this.mailbox = mailbox;
		this.noOfItems = noOfItems;
		
	}
	
	@Override
	public void run() {
		for (int i = 0; i < noOfItems; i++) {
			//System.out.println(Thread.currentThread().getName()+" : "+i);
			mailbox.put(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
