// System.out.print();

package part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex09Program_Thread_Pool {
	public static void main(String[] args) {
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(() ->{
			System.out.println(Thread.currentThread().getName() + ": " + (5+7));
		});
		exr.submit(() ->{
			System.out.println(Thread.currentThread().getName() + ": " + (7-5));
		});
		exr.submit(() ->{
			System.out.println(Thread.currentThread().getName() + ": " + (5 * 7));
		});
		
		exr.shutdown();
	}
}