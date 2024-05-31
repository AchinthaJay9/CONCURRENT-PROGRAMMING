
public class ThreadGroupExample01 {

	public static void main(String[] args) {
		System.out.println("Name of the thread "+Thread.currentThread().getName());
		System.out.println("The priority of the main thread "+Thread.currentThread().getPriority());
		System.out.println("Name of the thread  group "+Thread.currentThread().getThreadGroup().getName());
		
		Runnable run1 = new MyThread();
		Thread th1 = new Thread(run1, "T1");
		th1.start();
		
		System.out.println("The name of the thread group "+th1.getThreadGroup().getName()+" to which the thread "+th1.getName()+" belongs to");
		
		ThreadGroup tg01 = new ThreadGroup("TG01");
		
		System.out.println("The parent of the thread group "+tg01.getName()+" is "+tg01.getParent().getName());
		
		Runnable run2 = new MyThread();
		Thread th2 = new Thread(tg01, run2, "T2");
		th2.start();
		
		System.out.println("The name of the thread group "+th2.getThreadGroup().getName()+" to which the thread "+th2.getName()+" belongs to");
		
		ThreadGroup tg02 = new ThreadGroup(tg01, "TG02");
		
		System.out.println("The parent of the thread group "+tg02.getName()+" is "+tg02.getParent().getName());
		
		Thread th3 = new Thread(tg02, run1, "T3");
		th3.start();
		
		
		// to enumerate active threads in the thread group
		
		int noOfActiveThreadsInMain = Thread.currentThread().getThreadGroup().activeCount();
		
		Thread[] threads = new Thread[noOfActiveThreadsInMain*2];
		
		Thread.currentThread().getThreadGroup().enumerate(threads);
		
		for (Thread t : threads) {
			if(t != null) {
				System.out.println(t.getName());
			}
			
		}
		
		// to enumerate active thread groups in a thread group
		int noOfActiveThreadGroupInMain = Thread.currentThread().getThreadGroup().activeGroupCount();
		
		ThreadGroup[] threadGroups = new ThreadGroup[noOfActiveThreadGroupInMain*2];
		
		Thread.currentThread().getThreadGroup().enumerate(threadGroups);
		
		for (ThreadGroup tg : threadGroups) {
			if(tg != null) {
				System.out.println(tg.getName());
			}
			
		}
		
		
		
		// set max priority method works
		th3.setPriority(Thread.MAX_PRIORITY);
		
		tg02.setMaxPriority(Thread.MAX_PRIORITY - 2);
		
		System.out.println("The priority of the T3 thread "+th3.getPriority());
		
		System.out.println("The max priority of the TG02 thread group "+tg02.getMaxPriority());
		
		Thread th4 = new Thread(tg02, run1, "T4");
		th4.setPriority(Thread.MAX_PRIORITY);
		th4.start();
		System.out.println("The priority of the T4 thread "+th4.getPriority());
		

	}

}
