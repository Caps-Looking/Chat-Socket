import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PipeStreamLine implements Runnable {

	private Scanner scanner;
	private PrintStream output;

	public PipeStreamLine(InputStream in, OutputStream out) {
		scanner = new Scanner(in);
		output = new PrintStream(out);
	}

	@Override
	public void run() {
		while (true) {
			String line = scanner.nextLine();
			output.println(line + "\n");
		}
	}

}
