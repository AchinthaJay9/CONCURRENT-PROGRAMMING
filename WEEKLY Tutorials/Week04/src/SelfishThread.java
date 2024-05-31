
public class SelfishThread extends Thread {
	
	int tick = 1;

	public SelfishThread(int id) {
		super("Selfish "+id);
	}
	
	@Override
	public void run() {
		while(tick < 400000) {
			tick++;
			if(tick % 50000 == 0) {
				System.out.println(Thread.currentThread().getName()+" : "+tick);
			}
		}
	}
	
	

}
