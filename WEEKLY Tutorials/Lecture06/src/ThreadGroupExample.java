
public class ThreadGroupExample {

	public static void main(String[] args) {
		ThreadGroup[] tgArr = new ThreadGroup[6];
		Thread[] tArr = new Thread[7];
		
		tgArr[0] = Thread.currentThread().getThreadGroup();
		tgArr[1] = new ThreadGroup("Thread Group A");
		tgArr[2] = new ThreadGroup("Thread Group B");
		tgArr[3] = new ThreadGroup("Thread Group C");
		tgArr[4] = new ThreadGroup(tgArr[2], "Thread Group B1");
		tgArr[5] = new ThreadGroup(tgArr[2], "Thread Group B2");
		
		tArr[0] = new Thread("Thread 01");
		tArr[1] = new Thread(tgArr[1], "Thread 02");
		tArr[2] = new Thread(tgArr[1], "Thread 03");
		tArr[3] = new Thread(tgArr[3], "Thread 04");
		tArr[4] = new Thread(tgArr[4], "Thread 05");
		tArr[5] = new Thread(tgArr[4], "Thread 06");
		tArr[6] = new Thread(tgArr[5], "Thread 07");
		
		for(Thread t: tArr) {
			System.out.println(t.getPriority());
		}
		
		System.out.println(tgArr[0].activeGroupCount());
		
		Thread[] activeThreads = new Thread[tgArr[0].activeCount()*2];
		
		tgArr[0].enumerate(activeThreads);
		
		for(Thread t:activeThreads) {
			if (t != null) {
				System.out.println(t.getName());
			}
			
		}
		
		
		

	}

}
