//System.out.print();

package part1;

public class Ex06Program_Interrupt {
	public static void main(String[] args) {
		Runnable subMain = () -> {print();};
		
		Thread.currentThread().setName("Main");
		Thread th1 = new Thread(subMain, "sub1");
		th1.start();
		
		if(th1.isAlive()) {
			try {
				th1.join(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		th1.interrupt();
		System.out.println("======= Main Exit =======");
	}

	private static void print() {		
		Thread th = Thread.currentThread();
		
		for(int i=0; i < 10000; i++) {
			/* if(th.isInterrupted()) {
				System.out.println("----- Th Interrupt -----");
				return;
			}*/
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println("----- Th Interrupt -----");
				return;
			}
		
			System.out.printf("%s[%d]\n", th.getName(), i+1);
		}
	}
}