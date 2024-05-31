import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Writer extends Thread {
	
	private Data data;
	private ReentrantReadWriteLock readWriteLock;
	
	public Writer(Data data, ReentrantReadWriteLock readWriteLock, String name) {
		super(name);
		this.data = data;
		this.readWriteLock = readWriteLock;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
			writeLock.lock();
			data.setData(i);
			System.out.println(Thread.currentThread().getName()+" The data wrote is "+i);
			writeLock.unlock();
			
		}
	}
	
	

}
