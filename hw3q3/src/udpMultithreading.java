//am73676_sr39533
import java.io.*;
import java.util.*;
import java.net.*;
public class udpMultithreading extends Thread{
	private DatagramPacket datapacket;
	private DatagramSocket datasocket;
	public udpMultithreading(DatagramPacket packet, DatagramSocket socket){
		datapacket = packet;
		datasocket = socket;
	}
	public void run(){
		  String message = new String(datapacket.getData());
		  InetAddress sender=datapacket.getAddress();
		  int senderPort=datapacket.getPort();
		  String response = MultithreadedServer.processMessage(message);
		  byte[] responseBytes=response.getBytes();
		  DatagramPacket sendPack=new DatagramPacket(responseBytes, responseBytes.length, sender, senderPort);
		  try {
			datasocket.send(sendPack);
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
}