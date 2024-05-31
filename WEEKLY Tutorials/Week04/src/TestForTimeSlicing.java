
public class TestForTimeSlicing {

	public static void main(String[] args) {
		Thread[] threads = new Thread[2];
		
		for (int i = 0; i < 2; i++) {
			threads[i] = new SelfishThread(i);
			threads[i].setPriority(3);
		}
		
		for(Thread t : threads) {
			t.start();
		}
		int tick = 1;
		while(tick < 400000) {
			tick++;
			if(tick % 50000 == 0) {
				System.out.println(Thread.currentThread().getName()+" : "+tick);
				Thread.currentThread().yield();
			}
		}

	}

}
