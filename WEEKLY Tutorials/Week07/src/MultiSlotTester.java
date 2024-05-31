
public class MultiSlotTester {

	public static void main(String[] args) {
		MailBox mailbox = new MultiSlotMailbox();
		Consumer consumer1 = new Consumer(mailbox, 10);
		Producer producer1 = new Producer(mailbox, 10);
		Consumer consumer2 = new Consumer(mailbox, 10);
		Producer producer2 = new Producer(mailbox, 10);
		
		Thread conThread1 = new Thread(consumer1, "Consumer1");
		Thread proThread1 = new Thread(producer1, "Producer1");
		Thread conThread2 = new Thread(consumer2, "Consumer2");
		Thread proThread2 = new Thread(producer2, "Producer2");
		
		conThread1.start();
		proThread1.start();
		conThread2.start();
		proThread2.start();
		

	}

}
