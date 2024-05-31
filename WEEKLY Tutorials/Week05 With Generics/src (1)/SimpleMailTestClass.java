
public class SimpleMailTestClass {

	public static void main(String[] args) {
		Mailbox<Integer> mailbox = new SimpleMailbox<Integer>();
		final int NO_OF_ITEMS = 10;
		
		Runnable producer = new Producer(mailbox, NO_OF_ITEMS);
		Runnable consumer = new Consumer(mailbox, NO_OF_ITEMS);
		
		Thread producerThread = new Thread(producer, "Producer");
		Thread consumerThread = new Thread(consumer, "Consumer");
		producerThread.start();
		consumerThread.start();
	}

}
