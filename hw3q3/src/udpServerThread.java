import java.io.*;
import java.util.*;
import java.net.*;
public class udpServerThread extends Thread{
	private static int udpPort = Server.udpPort;
	private DatagramSocket datasocket;
	private DatagramPacket datapacket;
	public void run(){
	    try {
	    	datasocket = new DatagramSocket(udpPort);
	    	int len = 65507;
	    	byte[] buf = new byte[len];
	        while (true) {
	          try {
	        		  datapacket = new DatagramPacket (buf, buf.length); 
	        		  datasocket.receive(datapacket);
	        		  
	        		  String message = new String(datapacket.getData());
	        		  //int length = Integer.valueOf(message.substring(0,1));
	        		  message = message.substring(message.indexOf(" ")+1, Integer.valueOf(message.substring(0,message.indexOf(" ")))+message.indexOf(" ")+1);
	        		  //if(message.substring(0,4).equals("list"))
	        			  //message = message.substring(0,4);
	        		  InetAddress sender=datapacket.getAddress();
	        		  int senderPort=datapacket.getPort();
	        		  String response = MultithreadedServer.processMessage(message);
	        		  //message = "";
	        		  byte[] responseBytes=response.getBytes();
	        		  DatagramPacket sendPack=new DatagramPacket(responseBytes, responseBytes.length, sender, senderPort);
	        		  datasocket.send(sendPack);
	                 } catch (IOException e) {
	                	 System.err.println(e); 
	               } 
	        	}
	        }
	        catch (SocketException se) {
	            System.err.println (se); 
	        }
	}
}