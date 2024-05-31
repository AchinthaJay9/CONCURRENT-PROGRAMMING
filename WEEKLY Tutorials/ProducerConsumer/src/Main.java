
public class Main {

	public static void main(String[] args) {
		SimpleMailBox simpleMailBox = new SimpleMailBox();
		Thread prod = new Thread(new Producer(simpleMailBox),"Producer");
		Thread cons = new Thread(new Consumer(simpleMailBox),"Consumer");
		prod.start();
		cons.start();

	}

}
