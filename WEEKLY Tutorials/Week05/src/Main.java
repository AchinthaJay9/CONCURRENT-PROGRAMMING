
public class Main {

	public static void main(String[] args) {
		MailBox box = new SimpleMailBox();
		int noOfItems = 10;
		Runnable producer = new Producer(box, noOfItems );
		Runnable consumer = new Consumer(box, noOfItems );
		Thread producerThread = new Thread(producer, "Producer");
		Thread consumerThread = new Thread(consumer, "Consumer");
		producerThread.start();
		consumerThread.start();

	}

}
