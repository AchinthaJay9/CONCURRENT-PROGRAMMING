
public class MyThread implements Runnable {

	@Override
	public void run() {
		
		for(;;) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
