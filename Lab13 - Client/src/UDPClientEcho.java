import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientEcho {
	
	//Since I'm running it from the main function and not through objects...
	static DatagramSocket socket;

	public static void main(String args[]){
		// First open up a socket.
		try {
			//Create the UDP socket. 
			socket = new DatagramSocket();
			byte[] message = new String("Test!").getBytes();
			InetAddress address = InetAddress.getByName("localhost");
			DatagramPacket sendPacket = new DatagramPacket(message, message.length, address, 1336);
			
			//Send the packet through the socket.
			socket.send(sendPacket);
			
			byte[] recievedMessage = new byte[250];
			DatagramPacket recievePacket = new DatagramPacket(recievedMessage, recievedMessage.length);
			
			//Receive a message.
			socket.receive(recievePacket);
			
			String finalMessage = new String(recievePacket.getData());
			System.out.println("Echo the message from the server: "+ finalMessage);
			
			
		} catch (SocketException e) {
			System.out.println("Problem creating the socket...");
			e.printStackTrace();
			
		} catch (UnknownHostException e) {
			System.out.println("Host does not exist... Change it to something better.");
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println("Input or output error..");
			e.printStackTrace();
		} finally {
			
			// Close the socket to prevent memory leaks
			socket.close();
		}
	}
	
}
