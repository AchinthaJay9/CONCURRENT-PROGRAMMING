
public class SemaDemo {

	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		
		MutexProcess p1 = new MutexProcess("P1", mutex);
		MutexProcess p2 = new MutexProcess("P2", mutex);
		MutexProcess p3 = new MutexProcess("P3", mutex);
		
		p1.start();
		p2.start();
		p3.start();

	}

}
