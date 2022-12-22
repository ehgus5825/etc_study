// System.out.print();

package part2;

public class Ex02Program_monitor_critical_section {
	public static void main(String[] args) {
		Runnable subMain = () -> {print();};
		
		Thread.currentThread().setName("Main");
		
		Thread th1 = new Thread(subMain, "sub1");
		Thread th2 = new Thread(subMain, "sub2");
		Thread th3 = new Thread(subMain, "sub3");
		
		th1.start(); th2.start(); th3.start();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static Object lockIndex = new Object(); 
	static int gIndex = 0; // 데이터/정적
	
	private static void print() {		
		int index = 0; // 스택
		
		Thread th = Thread.currentThread();

		for(int i=0; i < 100; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.err.println("interrupt 발생!");
				return;
			}
			
			// lock으로 잠금을 해야하는 상황
			synchronized (lockIndex){
				index++;
				gIndex++;
				System.out.printf("%s[%d] : %d, index:%d, gIndex:%d\n", th.getName(), th.getId(), i+1, index, gIndex);
			}
		}
	}
}