import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		// criando um socket cliente. Conexao com socket servidor
		@SuppressWarnings("resource")
		Socket clientSocket = new Socket("localhost", 6789);

		System.out.println("Conexao com servidor estabelecida...");

		Thread threadInput = new Thread(new PipeStreamLine(clientSocket.getInputStream(), System.out));
		Thread threadOutput = new Thread(new PipeStreamLine(System.in, clientSocket.getOutputStream()));

		threadInput.start();
		threadOutput.start();
	}

}
