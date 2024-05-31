

public class SimpleMailBox implements MailBox {
	
	private int content = 0;
	private boolean available = false;
	
	public synchronized int take() {	
		while(!available){
			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available=false;
		notifyAll();
		return content;
	}

	public synchronized void put(int data) {
		while(available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.content = data;
		available = true;
		notifyAll();
	}
	
	

}
