import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

	public static void main(String[] args) {
		Data sharedObj = new Data();
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
		
		Reader r1 = new Reader(sharedObj, readWriteLock, "Reader 01");
		Reader r2 = new Reader(sharedObj, readWriteLock, "Reader 02");
		Reader r3 = new Reader(sharedObj, readWriteLock, "Reader 03");
		
		Writer w1 = new Writer(sharedObj, readWriteLock, "Writer 01");
		Writer w2 = new Writer(sharedObj, readWriteLock, "Writer 02");
		Writer w3 = new Writer(sharedObj, readWriteLock, "Writer 03");
		
		w1.start();
		w2.start();
		w3.start();
		
		r1.start();
		r2.start();
		r3.start();

	}

}
