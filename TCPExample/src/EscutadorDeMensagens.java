import java.io.InputStream;
import java.util.Scanner;

public class EscutadorDeMensagens implements Runnable {

	private InputStream cliente;
	private TCPServer servidor;
	private String usuario;

	public EscutadorDeMensagens(InputStream cliente, TCPServer servidor, String usuario) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.usuario = usuario;
	}

	@Override
	public void run() {
		Scanner s = new Scanner(this.cliente);
		while (s.hasNextLine()) {
			servidor.distribuirMensagens(usuario + ": " + s.nextLine());
		}
		s.close();
	}

}
