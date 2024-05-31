
public class Producer implements Runnable {
	
	private MailBox mailBox;
	private final int NO_OF_ITEMS;
	

	public Producer(MailBox mailBox, int noOfItems) {
		super();
		this.mailBox = mailBox;
		NO_OF_ITEMS = noOfItems;
	}



	@Override
	public void run() {
		for (int i = 0; i < NO_OF_ITEMS; i++) {
			mailBox.put(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName()+" produced : "+i);
		}

	}

}
