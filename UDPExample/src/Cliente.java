import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

	public static void main(String args[]) throws Exception {
		int porta = 9876;
		DatagramSocket clientSocket = new DatagramSocket();

		// obtem endereço IP do servidor com o DNS
		InetAddress IPAddress = InetAddress.getByName("localhost");

		String mensagem = "Eu sou o cliente";
		byte[] sendData = mensagem.getBytes();

		// cria pacote
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, porta);

		// envia o pacote
		clientSocket.send(sendPacket);

		byte[] receiveData = new byte[1024];
		// declara o pacote a ser recebido
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);

		// recebe pacote do servidor
		clientSocket.receive(receivePacket);

		// separa somente o dado recebido
		String mensagemModificada = new String(receivePacket.getData());

		System.out.println("FROM SERVER:" + mensagemModificada);

		clientSocket.close();
	}
}
