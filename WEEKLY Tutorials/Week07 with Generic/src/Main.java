
public class Main {

	public static void main(String[] args) {
		
		Mailbox<String> multislotMailbox = new MultiSlotMailBox<String>();
		
		final int noOfItems = 20;
		
		Producer p1 = new Producer(multislotMailbox, noOfItems);
		Producer p2 = new Producer(multislotMailbox, noOfItems);
		Producer p3 = new Producer(multislotMailbox, noOfItems);
		
		Consumer c1 = new Consumer(multislotMailbox, noOfItems);
		Consumer c2 = new Consumer(multislotMailbox, noOfItems);
		Consumer c3 = new Consumer(multislotMailbox, noOfItems);
		
		Thread prod1 = new Thread(p1, "Producer 01");
		Thread prod2 = new Thread(p2, "Producer 02");
		Thread prod3 = new Thread(p3, "Producer 03");
		
		Thread cons1 = new Thread(c1, "Consumer 01");
		Thread cons2 = new Thread(c2, "Consumer 02");
		Thread cons3 = new Thread(c3, "Consumer 03");
		
		prod1.start();
		prod2.start();
		prod3.start();
		
		cons1.start();
		cons2.start();
		cons3.start();

	}

}
