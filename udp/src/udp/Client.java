package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private DatagramSocket datagramSocket;
	private InetAddress inetAddress;
	private byte[] buffer;
	
	public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}
	
	public void sendClientPacket() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				String messageTo = scanner.nextLine();
				buffer = messageTo.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
				datagramSocket.send(datagramPacket);
				datagramSocket.receive(datagramPacket);
				String messageFrom = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				System.out.println("Servidor recebeu: " + messageFrom);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		Client client = new Client(datagramSocket, inetAddress);
		System.out.println("Pacotes a serem enviados ao servidor: ");
		client.sendClientPacket();
	}
}