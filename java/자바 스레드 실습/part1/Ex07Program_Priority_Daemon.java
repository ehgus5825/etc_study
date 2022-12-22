//System.out.print();

package part1;

public class Ex07Program_Priority_Daemon {
	public static void main(String[] args) {
		Runnable subMain = () -> {print();};
		
		Thread.currentThread().setName("Main");
		Thread th1 = new Thread(subMain);    th1.setName("sub1");
		Thread th2 = new Thread(subMain);    th2.setName("sub2");
		th1.setPriority(Thread.MAX_PRIORITY);
		th2.setPriority(Thread.MIN_PRIORITY);
		th2.setDaemon(true);
		
		th1.start();  th2.start();
		print();
	}

	private static void print() {		
		Thread th = Thread.currentThread();
		
		for(int i=0; i < 100; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s[%d] : %d\n", th.getName(), th.getId(), i+1);
		}
	}
}