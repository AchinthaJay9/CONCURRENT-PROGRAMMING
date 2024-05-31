
public class MailboxTest {

	public static void main(String[] args) {
		Mailbox mailbox = new MultipleSlotMailbox();
		Thread producer1 = new Producer(1, mailbox, MultipleSlotMailbox.CAPACITY);
		Thread consumer1 = new Consumer(2, mailbox, MultipleSlotMailbox.CAPACITY);
		//Thread producer2 = new Producer(3, mailbox, MultipleSlotMailbox.CAPACITY);
		Thread consumer2 = new Consumer(4, mailbox, MultipleSlotMailbox.CAPACITY);
		//consumer1.setDaemon(true);
		//consumer2.setDaemon(true);
		producer1.start();
		consumer1.start();
		//producer2.start();
		consumer2.start();

	}

}
