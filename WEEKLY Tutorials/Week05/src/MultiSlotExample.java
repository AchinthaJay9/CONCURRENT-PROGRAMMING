
public class MultiSlotExample {

	public static void main(String[] args) {
		MailBox box = new MultiSlotMailBox();
		int noOfItems = 20;
		Runnable producer = new Producer(box, noOfItems );
		Runnable consumer1 = new Consumer(box, noOfItems - 5 );
		Runnable consumer2 = new Consumer(box, noOfItems - 5 );
		
		Thread producerThread = new Thread(producer, "Producer");
		Thread consumerThread1 = new Thread(consumer1, "Consumer 01");
		Thread consumerThread2 = new Thread(consumer2, "Consumer 02");
		consumerThread1.setDaemon(true);
		consumerThread2.setDaemon(true);
		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();

	}

}
