import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

	public static void main(String[] args) {
		Account accObj = new Account();
		ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
		Reader r1 = new Reader("R1", accObj, rw);
		Reader r2 = new Reader("R2", accObj, rw);
		Reader r3 = new Reader("R3", accObj, rw);
		
		Writer w1 = new Writer("W1", accObj, rw);
		Writer w2 = new Writer("W2", accObj, rw);
		Writer w3 = new Writer("W3", accObj, rw);
		
		r1.start();
		r2.start();
		r3.start();
		
		w1.start();
		w2.start();
		w3.start();
	}

}
