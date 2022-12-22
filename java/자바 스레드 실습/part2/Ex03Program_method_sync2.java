// System.out.print();

package part2;

import java.sql.Timestamp;

public class Ex03Program_method_sync2 {
	public static void main(String[] args) {
		Timestamp time1 = new Timestamp(System.currentTimeMillis());
		System.out.println(time1);
		
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
		
		try {
			th1.join(); th2.join(); th3.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		Timestamp time2 = new Timestamp(System.currentTimeMillis());
		System.out.println(time2);
		System.out.println(time2.getTime() - time1.getTime());
		
		System.out.println("=== Exit ===");
	}
}