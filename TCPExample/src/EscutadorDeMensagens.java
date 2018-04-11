import java.io.InputStream;
import java.util.Scanner;

public class EscutadorDeMensagens implements Runnable {

	private InputStream cliente;
	private TCPServer servidor;

	public EscutadorDeMensagens(InputStream cliente, TCPServer servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		Scanner s = new Scanner(this.cliente);
		while (s.hasNextLine()) {
			servidor.distribuirMensagens(s.nextLine());
		}
		s.close();
	}

}
