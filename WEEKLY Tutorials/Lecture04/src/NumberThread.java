
public class NumberThread extends Thread {
	
	public NumberThread(String name) {
		super(name);
	}
	
	public void run() {
		for (int i=0; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName()+" : "+i);
			//System.out.println(Thread.currentThread().getName()+" State : "+Thread.currentThread().getState());
			
			  try { 
				  Thread.sleep(10);
				  //this.interrupt();
			  } catch (InterruptedException e) {
				  e.printStackTrace(); 
			  }
			 
		}
	}

}
