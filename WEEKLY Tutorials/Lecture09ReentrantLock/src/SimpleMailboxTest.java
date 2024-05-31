
public class SimpleMailboxTest {

	public static void main(String[] args) {
		Mailbox mailbox = new MultipleSlotMailbox();
		final int NO_OF_ITEMS = 10;
		Thread producer1 = new Producer(1, mailbox, NO_OF_ITEMS);
		Thread consumer1 = new Consumer(3, mailbox, NO_OF_ITEMS);
		Thread producer2 = new Producer(2, mailbox, NO_OF_ITEMS);
		Thread consumer2 = new Consumer(4, mailbox, NO_OF_ITEMS);
		producer1.start();
		consumer1.start();
		producer2.start();
		consumer2.start();

	}

}
