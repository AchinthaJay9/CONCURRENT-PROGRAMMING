
public class MutexProcess extends Thread {
	
	private Semaphore mutex;
	
	public MutexProcess(String name, Semaphore mutex) {
		super(name);
		this.mutex = mutex;
	}
	
	@Override
	public void run() {
		int i = 0;
		for(;;) {
			this.mutex.claim();
			System.out.println("["+Thread.currentThread().getName()+"] Critical Section : "+i++);
			this.mutex.release();
		}
	}

}
