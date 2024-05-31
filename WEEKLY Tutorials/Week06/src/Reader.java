import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader extends Thread {
	
	private Data data;
	private ReentrantReadWriteLock readWriteLock;
	
	public Reader(Data data, ReentrantReadWriteLock readWriteLock, String name) {
		super(name);
		this.data = data;
		this.readWriteLock = readWriteLock;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
			readLock.lock();
			int value = data.getData();
			System.out.println(Thread.currentThread().getName()+" The data read is "+value);
			readLock.unlock();
			
		}
	}
	
	

}
