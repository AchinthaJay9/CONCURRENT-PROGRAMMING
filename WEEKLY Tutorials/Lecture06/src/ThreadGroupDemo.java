
public class ThreadGroupDemo {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getThreadGroup().getName());
		Thread t1 = new Thread();
		System.out.println(t1.getName());
		System.out.println(t1.getThreadGroup().getName());
		ThreadGroup tg1 = new ThreadGroup("Thread Group 01");
		Thread t2 = new Thread(tg1, "T2");
		
		ThreadGroup main = Thread.currentThread().getThreadGroup();
		int activeThreadCount = main.activeCount();
		System.out.println("No of active Thread is "+activeThreadCount);
		
		ThreadGroup tg2 = new ThreadGroup(tg1, "ThreadGroup 02");
		System.out.println("The parent of Thread Group 02 is : "+tg2.getParent().getName());
		System.out.println(main.parentOf(tg1));
		System.out.println(tg1.parentOf(main));
		
		

	}

}
