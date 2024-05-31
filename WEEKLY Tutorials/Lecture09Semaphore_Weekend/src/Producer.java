
public class Producer implements Runnable {
	
	private SimpleMailBox mailBox;

	public Producer(SimpleMailBox mailBox) {
		super();
		this.mailBox = mailBox;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName() +" produced  : "+i);
			mailBox.put(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
