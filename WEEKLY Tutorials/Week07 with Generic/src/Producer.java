
public class Producer implements Runnable {
	
	private Mailbox<String> mailbox;
	private final int NO_OF_ITEMS;
	
	

	public Producer(Mailbox<String> mailbox, int noOfItems) {
		super();
		this.mailbox = mailbox;
		NO_OF_ITEMS = noOfItems;
	}



	@Override
	public void run() {
		for(int i = 0; i < NO_OF_ITEMS;i++) {
			mailbox.put(Thread.currentThread().getName()+" produced "+i);
			System.out.println(Thread.currentThread().getName()+" produced "+i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}