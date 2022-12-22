// System.out.print();

package part2;

public class Ex03Program_method_sync {
	public static void main(String[] args) {
		CharList list = new CharList(); 
		Runnable subMain = () -> {
			for(int i = 0; i < 80; i++)
				list.printNext();
		};
		
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
}