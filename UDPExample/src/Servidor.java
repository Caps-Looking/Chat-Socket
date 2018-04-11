import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Servidor {
	public static void main(String args[]) throws Exception {
		int porta = 9876;
		DatagramSocket serverSocket = new DatagramSocket(porta);
		
		while (true) {
			byte[] receiveData = new byte[1024];
			// declara o pacote a ser recebido
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);

			// recebe o pacote do cliente
			serverSocket.receive(receivePacket);

			// pega os dados recebidos
			String sentence = new String(receivePacket.getData());

			InetAddress IPAddress = receivePacket.getAddress();
			int portaCliente = receivePacket.getPort();

			// transforma em maiúsculas
			String mensagemMaiusculas = sentence.toUpperCase();
			byte[] sendData = mensagemMaiusculas.getBytes();

			// monta o pacote com enderço IP e porta
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, portaCliente);

			// envia ao cliente
			serverSocket.send(sendPacket);
		}
	}
}
