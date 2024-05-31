
public class Main {

	public static void main(String[] args) {
		Thread myThreadOne = new MyThread("T1");
		Thread myThreadTwo = new MyThread("T2");
		myThreadOne.setPriority(Thread.MIN_PRIORITY);
		myThreadTwo.setPriority(Thread.MAX_PRIORITY);
		myThreadTwo.setDaemon(true);
		System.out.println(myThreadOne.getState());
		System.out.println(myThreadTwo.getState());
		Runnable myThreadThree = new RunnableThread("R1");
		System.out.println(((RunnableThread)myThreadThree).getMyThread().getState());
		System.out.println(((RunnableThread)myThreadThree).getMyThread().getPriority());
		/*try {
			((RunnableThread) myThreadThree).getMyThread().join();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}*/
		myThreadOne.start();
		myThreadTwo.start();
		System.out.println(myThreadOne.getState());
		System.out.println(myThreadTwo.getState());
		System.out.println(myThreadOne.getPriority());
		System.out.println(myThreadTwo.getPriority());
		System.out.println(((RunnableThread)myThreadThree).getMyThread().getState());
		
		  
		 
		/*
		for (int i = 0; i <10; i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}

}
