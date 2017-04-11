import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerEcho {
	
	static DatagramSocket socket; 

	public static void main(String[] args) {
		try {
			// Open up a UDP socket.
			socket = new DatagramSocket(1336);
			
			// Create a byte buffer.
			byte[] buffer = new byte[250];
			
			// The received packet.
			DatagramPacket recievedPacket = new DatagramPacket(buffer, buffer.length);
			
			// Wait till a packet is received. 
			socket.receive(recievedPacket);
			
			//Get the host IP address
			String ip = recievedPacket.getAddress().getHostAddress();

			String s = Integer.toString(recievedPacket.getPort());
			
			// Print out the results.
			System.out.println("The client IP Address is: "+ ip);
			System.out.println("The client Socket port is: " + s);
			
			// Send out the message.
			String message = "Hi, There!";
			byte[] byteMessage = message.getBytes();
			
			// Create the packet then send it out through UDP packet...
			DatagramPacket sendPacket = new DatagramPacket(byteMessage, byteMessage.length, recievedPacket.getAddress(), recievedPacket.getPort());
			socket.send(sendPacket);
			
		} catch (SocketException e) {
			System.out.println("Socket error, could not initalize socket...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Input/Output exception occured. Could not recieve or sent the packet.");
			e.printStackTrace();
		} finally {
			socket.close();
		}
		
	}

}
