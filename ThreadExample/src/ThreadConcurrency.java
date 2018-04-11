import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadConcurrency implements Runnable {

	// nome da thread
	private String name;

	// contador unico global
	private static int contador = 0;

	public ThreadConcurrency(String n) {
		name = n;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			synchronized (System.out) {
				contador = contador + 1;
				System.out.println(name + " => " + contador);
			}

			// paraliza temporariamente a thread
			// try {
			// Thread.sleep(200);
			// } catch (InterruptedException e) {}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(4);

		for (int i = 1; i <= 100; i++) {
			pool.execute(new ThreadConcurrency("t" + i));
		}

		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
	}
}
