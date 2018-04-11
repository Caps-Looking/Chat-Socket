import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {
	private static ServerSocket welcomeSocket;

	public static void main(String argv[]) throws Exception {
		welcomeSocket = new ServerSocket(6789);

		System.out.println("Servidor iniciado.");
		
		// espera por uma conexao (bloqueia a aplicacao).
		// cria um novo socket local que ira tratar esta nova conexao
		Socket connectionSocket = welcomeSocket.accept();

		System.out.println("Conexao estabelecida...");

		Thread threadInput = new Thread(new PipeStreamLine(connectionSocket.getInputStream(), System.out));
		Thread threadOutput = new Thread(new PipeStreamLine(System.in, connectionSocket.getOutputStream()));

		threadInput.start();
		threadOutput.start();		
	}
	
}
