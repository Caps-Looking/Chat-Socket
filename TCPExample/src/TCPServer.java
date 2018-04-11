import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class TCPServer {
	
	private ServerSocket serverSocket;
	private List<PrintStream> clientes = new ArrayList<PrintStream>();
	
	public static void main(String argv[]) throws Exception {
		new TCPServer().iniciarServidor();
	}
	
	/**
	 * Inicia o servidor na porta 6789
	 * @throws IOException
	 */
	private void iniciarServidor() throws IOException {
		serverSocket = new ServerSocket(6789);
		System.out.println("Servidor iniciado!");		
		escutarConexoes();
	}
	
	/**
	 * Loop infinito para escutar cada conexão
	 * @throws IOException
	 */
	private void escutarConexoes() throws IOException {		
		while(true) {
			Socket cliente = serverSocket.accept();
			
			System.out.println("Nova conexão estabelecida!");
			System.out.println("Endereço do meliante: " + cliente.getInetAddress().getHostAddress());
			
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.clientes.add(ps);
			
			EscutadorDeMensagens escMsg = new EscutadorDeMensagens(cliente.getInputStream(), this); 
			new Thread(escMsg).start();
		}
	}
	
	/**
	 * Distribui as mensagens para os clientes
	 * @param msg
	 */
	public void distribuirMensagens(String msg) {
		clientes.forEach(cliente -> {
			cliente.println(msg);
		});
	}
	
}
