
public class Plate {
	
	private String food;
	private boolean available;
	
	
	
	public Plate() {
		super();
		this.food = null;
		this.available = false;
	}

	public synchronized void produce(String food) {
		
		while(available) {
			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.food = food;
		System.out.println(Thread.currentThread().getName()+" produced "+food);
		available = true;
		notifyAll();
	}
	
	public synchronized String consume() {
		while(!available) {
			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String food = this.food;
		System.out.println(Thread.currentThread().getName()+" consumed "+food);
		available = false;
		notifyAll();
		return food;
	}

}
