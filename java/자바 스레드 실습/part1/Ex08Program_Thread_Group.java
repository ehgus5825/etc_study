// System.out.print();

package part1;

public class Ex08Program_Thread_Group {
	public static void main(String[] args) {
		Runnable subMain = () -> {print();};
		
		ThreadGroup thGroup = new ThreadGroup("print main group"); 
		ThreadGroup thGroup1 = new ThreadGroup(thGroup, "print sub group1");
		
		Thread.currentThread().setName("Main");
		
		Thread th1 = new Thread(thGroup1, subMain);
		th1.setName("sub1");
		Thread th2 = new Thread(thGroup1, subMain);
		th2.setName("sub2");
		Thread th3 = new Thread(thGroup1, subMain);
		th3.setName("sub3");
		
		th1.start(); th2.start(); th3.start();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thGroup.list();			// 스레드 그룹 내 스레드 정보 출력
		
		/*
		if(th1.isAlive()) th1.interrupt();
		if(th2.isAlive()) th2.interrupt();
		if(th3.isAlive()) th3.interrupt();
		*/
		
		thGroup1.interrupt();	// 위의 코드가 아래로 대체됨
		
		System.out.println("=== Exit ===");
	}

	private static void print() {		
		Thread th = Thread.currentThread();

		for(int i=0; i < 100; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.err.println("interrupt 발생!");
				return;
			}
			System.out.printf("%s[%d] : %d\n", th.getName(), th.getId(), i+1);
		}
	}
}