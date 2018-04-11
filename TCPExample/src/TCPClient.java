import java.net.Socket;

public class TCPClient {

	private static Socket clientSocket;

	public static void main(String argv[]) throws Exception {
		clientSocket = new Socket("localhost", 6789);

		System.out.println("Conexao com servidor estabelecida...");

		Thread threadInput = new Thread(new PipeStreamLine(clientSocket.getInputStream(), System.out));
		Thread threadOutput = new Thread(new PipeStreamLine(System.in, clientSocket.getOutputStream()));

		threadInput.start();
		threadOutput.start();
	}
	
}