import java.util.Scanner;

public class ThreadDemo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Thread t1 = new NumberThread("T1");
		Thread t2 = new NumberThread("T2");
		Thread t3 = new NumberThread("T3");
//		t1.setDaemon(true);
//		t2.setDaemon(true);
//		t3.setDaemon(true);
		t2.setPriority(10);
		t3.setPriority(10);
		
		/*
		 * System.out.println(t1.getName()+" State : "+t1.getState());
		 * 
		 * System.out.println("T1 Priority "+t1.getPriority());
		 * System.out.println("T2 Priority "+t2.getPriority());
		 * System.out.println("T3 Priority "+t3.getPriority());
		 */
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		t3.start();
		
		Runnable r1 = new RunnableThread();
		Thread thread = new Thread(r1, "R1");
		thread.start();
		
		for (int i=0;i <=10; i++) {
			System.out.println(Thread.currentThread().getName()+" : "+i);
			/*
			 * System.out.println(Thread.currentThread().getName()+" State : "+Thread.
			 * currentThread().getState());
			 * System.out.println("Inside for "+t1.getName()+" State : "+t1.getState());
			 * System.out.println(Thread.currentThread().getPriority());
			 */
			/*
			 * if (i == 5) { int num=sc.nextInt(); }
			 */		
		}
		
//		System.out.println("End of main "+t1.getName()+" State : "+t1.getState());

	}

}
