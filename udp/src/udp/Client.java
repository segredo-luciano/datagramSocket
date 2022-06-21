package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import udp.enums.StandardMessagesEnum;
import vo.NestVO;

public class Client {

	private DatagramSocket datagramSocket;
	private InetAddress inetAddress;
	private byte[] buffer;
	
	public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}
	
	public void sendClientPacket() throws IOException {
		List<NestVO> nests = generateMockNests();
		
		for (int i = 0; i < nests.size(); i++) {
			String messageTo = StandardMessagesEnum.PREOCUPANTE.getDescription();
			buffer = messageTo.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
			datagramSocket.send(datagramPacket);
			datagramSocket.receive(datagramPacket);
			String messageFrom = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("Servidor recebeu: " + messageFrom);
		}
	}
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		Client client = new Client(datagramSocket, inetAddress);
		Runnable drawRunnable = new Runnable() {
		    public void run() {
		    	try {
					client.sendClientPacket();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		};
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(drawRunnable , 0, 10, TimeUnit.SECONDS);
	}
	
	private List<NestVO> generateMockNests() {
		List<NestVO> mockNests = new ArrayList<NestVO>();
		int[] densities = {1105, 930, 785, 916, 630};
		String[] names = {"Ipê", "Angelim", "Angelim-Pedra", "Eucalipto", "Mogno"};
		
		int qtd = 10;
		for (int i = 0; i < names.length; i++) {
			Integer currDens = densities[i];
			NestVO nest = new NestVO();
			// capacidade em KG/M³
			nest.setCapacity((5 * currDens.floatValue()));
			nest.setName(names[i]);
			nest.setRegister(new Random().nextLong());
			nest.setCurrentWeight(new Random().nextFloat());
			nest.setWoodDensity(currDens.floatValue());
			nest.setWoodType("Tipo de Madeira Qualquer " + (i + 1));
			mockNests.add(nest);
		}
		return mockNests;
	}
}
