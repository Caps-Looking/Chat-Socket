import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

	private ServerSocket serverSocket;
	private List<PrintStream> clientes = new ArrayList<PrintStream>();

	public static void main(String argv[]) throws Exception {
		new TCPServer().iniciarServidor();
	}

	private void iniciarServidor() throws IOException {
		serverSocket = new ServerSocket(6789);
		System.out.println("Servidor iniciado!");
		
		while (true) {
			Socket cliente = serverSocket.accept();

			System.out.println("Nova conexão estabelecida!");
			System.out.println("Endereço do meliante: " + cliente.getInetAddress().getHostAddress());

			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.clientes.add(ps);

			EscutadorDeMensagens escMsg = new EscutadorDeMensagens(cliente.getInputStream(), this, cliente.getInetAddress().getHostAddress());
			new Thread(escMsg).start();
		}
	}

	public void distribuirMensagens(String msg) {
		clientes.forEach(cliente -> cliente.println(msg));
	}

}
