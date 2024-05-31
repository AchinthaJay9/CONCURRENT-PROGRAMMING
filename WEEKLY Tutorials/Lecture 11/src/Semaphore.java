
public class Semaphore {
	
	private int value;
	
	public Semaphore(int initialValue) {
		this.value = initialValue;
	}
	
	public synchronized void claim() {
		while (value == 0) {

			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
		}
		value = value - 1;
	}
	
	public synchronized void release() {
		value = value + 1;
		notifyAll();
	}

}
