
public class RunnableThread implements Runnable {
	
	private String name;
	private Thread myThread;
	
	public String getName() {
		return name;
	}

	public Thread getMyThread() {
		return myThread;
	}

	public RunnableThread(String name) {
		super();
		this.name = name;
		
		myThread = new Thread(this, name);
		myThread.start();
		
	}

	@Override
	public void run() {
		for (int i = 0; i <10; i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
