import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.net.*;
public class Client {
	private static Socket s;
	private static InputStream input;
    private static OutputStream output;
    private static PrintStream printStream;
    private static InputStreamReader inputStream;
    private static BufferedReader bufferedReader;
    private static String message = null;
    private static DatagramPacket sPacket, rPacket;
    private static InetAddress ia;
    private static DatagramSocket datasocket;
    private final static int len = 65507;
    //private static BufferedReader stdinp;
	
  public static void main (String[] args) {
    String hostAddress;
    int tcpPort;
    int udpPort;
    boolean tcpOn = true;
    if (args.length != 3) {
      System.out.println("ERROR: Provide 3 arguments");
      System.out.println("\t(1) <hostAddress>: the address of the server");
      System.out.println("\t(2) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(3) <udpPort>: the port number for UDP connection");
      System.exit(-1);
    }

    hostAddress = args[0];
    tcpPort = Integer.parseInt(args[1]);
    udpPort = Integer.parseInt(args[2]);
    
    try {
    	ia = InetAddress.getByName(hostAddress);
    	datasocket = new DatagramSocket(); 
    } catch ( UnknownHostException e){
    			System.err.println (e);
    } catch ( SocketException se ){
    	System.err.println (se);
    }

    
    try {
		s=new Socket(hostAddress, tcpPort);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
    try {
		input = s.getInputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  	
    try {
		output = s.getOutputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    

    
    printStream = new PrintStream(output);
  	inputStream = new InputStreamReader(input);
  	bufferedReader = new BufferedReader(inputStream);
  	message = null;

    Scanner sc = new Scanner(System.in);
    while(sc.hasNextLine()) {
      String cmd = sc.nextLine();
      String[] tokens = cmd.split(" ");

      if (tokens[0].equals("setmode")) {
        if(tokens[1].equals("T")){
        	tcpOn = true;
        	System.out.println("TCP");
        	//printStream.println(output);
        }	
        else{
        	tcpOn = false;
        	System.out.println("UDP");
        }
        	
    	  // TODO: set the mode of communication for sending commands to the server 
        // and display the name of the protocol that will be used in future
      }
      else if (tokens[0].equals("purchase")) {
    	if(tcpOn)
    		tcpMethod(cmd, tokens[0]);
    	else
    		udpMethod(cmd, tokens[0], udpPort);
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("cancel")) {
      	if(tcpOn)
      		tcpMethod(cmd, tokens[0]);
    	else
    		udpMethod(cmd, tokens[0], udpPort);
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("search")) {
      	if(tcpOn)
      		tcpMethod(cmd, tokens[0]);
    	else
    		udpMethod(cmd, tokens[0], udpPort);
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("list")) {
      	if(tcpOn)
    		tcpMethod(cmd, tokens[0]);
    	else
    		udpMethod(cmd, tokens[0], udpPort);
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else {
        System.out.println("ERROR: No such command");
      }
    }
  }
  private static void tcpMethod(String output, String action){
	  	printStream.println(output);
	  	if(action.equals("purchase")){
	    	try {
				message = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	interpretMessage(message);	
	  	}
	  	else if(action.equals("cancel")){
	    	try {
				message = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	interpretMessage(message);
	  	}
	  	else if(action.equals("search")){
	    	try {
				message = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int n = Integer.valueOf(message);
	    	for(int i = 0; i<n; i++){
		    	try {
					message = bufferedReader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	interpretMessage(message);
	    	}	
	  	}
	  	else if(action.equals("list")){
	    	try {
				message = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int n = Integer.valueOf(message);
	    	for(int i = 0; i<n; i++){
		    	try {
					message = bufferedReader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	interpretMessage(message);
	    	}
	  	}
	  		

  }
  private static void udpMethod(String output, String action, int port){
	  	//printStream.println(output);
		byte [] buffer = new byte[output.length()]; 
		buffer = output.getBytes();
		sPacket = new DatagramPacket (buffer, buffer.length , ia, port); 
		try {
			datasocket.send(sPacket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	  	if(action.equals("purchase")){
			byte[] rbuffer = new byte[len];
			rPacket = new DatagramPacket(rbuffer, rbuffer.length);
			try {
				datasocket.receive (rPacket);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String message = new String (rPacket.getData(), 0, rPacket.getLength());
	    	interpretMessage(message);
	  	}
	  	else if(action.equals("cancel")){
			byte[] rbuffer = new byte[len];
			rPacket = new DatagramPacket(rbuffer, rbuffer.length);
			try {
				datasocket.receive (rPacket);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String message = new String (rPacket.getData(), 0, rPacket.getLength());
	    	interpretMessage(message);
	  	}
	  	else if(action.equals("search")){
			byte[] rbuffer = new byte[len];
			rPacket = new DatagramPacket(rbuffer, rbuffer.length);
			try {
				datasocket.receive (rPacket);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String message = new String (rPacket.getData(), 0, rPacket.getLength());
	    	interpretMessage(message);
	  	}
	  	else if(action.equals("list")){
			byte[] rbuffer = new byte[len];
			rPacket = new DatagramPacket(rbuffer, rbuffer.length);
			try {
				datasocket.receive (rPacket);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String message = new String (rPacket.getData(), 0, rPacket.getLength());
	    	interpretMessage(message);
	  	}	  		
  }
  private static void interpretMessage(String m){
	  System.out.println(m);
  }

}
