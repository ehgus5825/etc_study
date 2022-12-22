//System.out.print();

package part1;

public class Ex04Program {
	public static void main(String[] args) {
		Runnable subMain = () -> {print();};
		
		Thread.currentThread().setName("Main");
		Thread th1 = new Thread(subMain, "sub1");
		
		System.out.printf("%s : %s\n", th1.getName(), th1.getState());
		th1.start();
		System.out.printf("%s : %s\n", th1.getName(), th1.getState());
		
		try {
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("%s : %s\n", Thread.currentThread().getName(), Thread.currentThread().getState());
		System.out.printf("%s : %s\n", th1.getName(), th1.getState());
	}

	private static void print() {		
		Thread th = Thread.currentThread();

		for(int i=0; i < 10; i++) {
			try {
				Thread.sleep(200);
				System.out.printf("%s[%d]\t", th.getName(), i+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
}