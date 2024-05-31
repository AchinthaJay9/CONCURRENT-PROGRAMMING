
public class SafeCounter {
	
	private int count;
	
	public SafeCounter(int initialValue) {
		this.count = initialValue;
	}
	
	public synchronized void increment() {
		int temp = read();//read[v]
		write(temp);//write[v+1]
	}
	
	public static void main(String[] args) {
		SafeCounter counter = new SafeCounter(0);//US_COUNT[ 0 ]
		counter.increment();
	}
	
	private int read() {
		return count;
	}
	
	private void write(int value) {
		value++;
		count = value;
	}

}
