
public class Consumer implements Runnable {
	
	private SimpleMailBox mailBox;

	public Consumer(SimpleMailBox mailBox) {
		super();
		this.mailBox = mailBox;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName() +" consumed the data  : "+mailBox.take());
		}

	}

}
