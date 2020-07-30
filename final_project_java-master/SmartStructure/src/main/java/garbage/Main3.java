package garbage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main3 {

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newCachedThreadPool();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					System.out.println("runnable awake");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		service.execute(runnable);
		service.shutdown();
		while (!service.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
			System.out.println("awaitetermination");
		}

	}

}
