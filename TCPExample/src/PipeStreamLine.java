import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class PipeStreamLine implements Runnable {

	private Scanner scanner;
	private DataOutputStream output;

	public PipeStreamLine(InputStream in, OutputStream out) {
		scanner = new Scanner(in);
		output = new DataOutputStream(out);
	}

	@Override
	public void run() {
		while (true) {
			String line = scanner.nextLine();
			try {
				output.writeBytes(line + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
