import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class LerMensagem implements Runnable {

	private DataOutputStream output;
	private Scanner scanner;

	public LerMensagem(OutputStream os) {
		output = new DataOutputStream(os);
		scanner = new Scanner(System.in);
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
