// System.out.print();

package part2;

import java.sql.Timestamp;

public class Ex05Program_multi_critical_section {
	public static void main(String[] args) {
		CharList3 list = new CharList3(); 
		Runnable subLoad = () -> { list.load(); };
		Runnable subPrintAll = () -> { list.printAll(100); };
		
		Thread.currentThread().setName("Main");	
		Thread th1 = new Thread(subLoad, "sub1");
		Thread th2 = new Thread(subPrintAll, "sub2");
		Thread th3 = new Thread(subPrintAll, "sub3");
		
		th1.start(); 
		
		try {
			Thread.sleep(30);
			th1.join(); th2.join(); th3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.start(); th3.start();
		
		System.out.println("=== Exit ===");
	}
}