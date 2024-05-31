
public class ThreadGroupExample02 {

	public static void main(String[] args) {
		
		ThreadGroup[] threadGroups = new ThreadGroup[6];
		Thread[] threads = new Thread[8];
		threadGroups[0] = Thread.currentThread().getThreadGroup();
		threadGroups[1] = new ThreadGroup("Thread Group A");
		threadGroups[2] = new ThreadGroup("Thread Group B");
		threadGroups[3] = new ThreadGroup("Thread Group C");
		
		threads[0] = Thread.currentThread();
		threads[1] = new Thread("Thread 1");
		threads[2] = new Thread(threadGroups[1], "Thread 2");
		threads[3] = new Thread(threadGroups[1], "Thread 3");
		threads[4] = new Thread(threadGroups[3], "Thread 4");
		
		threadGroups[4] = new ThreadGroup(threadGroups[2], "Thread Group B1");
		threadGroups[5] = new ThreadGroup(threadGroups[2], "Thread Group B2");
		
		threads[5] = new Thread(threadGroups[4], "Thread 5");
		threads[6] = new Thread(threadGroups[4], "Thread 6");
		
		threads[7] = new Thread(threadGroups[5], "Thread 7");
		
		for (int i=1; i < threads.length; i++ ) {
			threads[i].start();
		}
		
//		for (Thread t : threads) {
//			if (!t.isAlive()) {
//				t.start();
//			}
//		}
		
		for (ThreadGroup group : threadGroups) {
			listActiveThreads(group, false);
			listActiveThreadGroup(group, false);
		}
		

	}
	
	public static void listActiveThreads(ThreadGroup tg, boolean recur) {
		int noOfActiveThreadsInMain = tg.activeCount();
		
		Thread[] threads = new Thread[noOfActiveThreadsInMain*2];
		
		tg.enumerate(threads, recur);
		
		for (Thread t : threads) {
			if(t != null) {
				System.out.println(t.getName());
			}
			
		}
	}
	
	public static void listActiveThreadGroup(ThreadGroup tg, boolean recur) {
		int noOfActiveThreadGroupInMain = tg.activeGroupCount();
		
		ThreadGroup[] threadGroups = new ThreadGroup[noOfActiveThreadGroupInMain*2];
		
		tg.enumerate(threadGroups, recur);
		
		for (ThreadGroup group : threadGroups) {
			if(group != null) {
				System.out.println(group.getName());
			}
			
		}
	}

}
