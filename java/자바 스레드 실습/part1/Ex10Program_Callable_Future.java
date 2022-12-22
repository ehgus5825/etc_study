// System.out.print();

package part1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex10Program_Callable_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		Callable<Integer> task = () -> {
			int sum = 0;
			for(int i = 0; i < 10; i++)
				sum += i;
			return sum;
		};

		ExecutorService exr = Executors.newSingleThreadExecutor();
		Future<Integer> fur = exr.submit(task);
		
		int r = fur.get();
		System.out.print("result: " + r);
		exr.shutdown();
	}
}