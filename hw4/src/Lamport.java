import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;

public class Lamport extends Thread{
	Socket theServer;
	private static LinkedList<String> lamport;
	private static InputStream input;
    private static OutputStream output;
    private static PrintStream printStream;
    private static InputStreamReader inputStream;
    private static BufferedReader bufferedReader;
    private static String message = null;
	public Lamport(Socket s){
    	theServer = s;	
    }
	public void run(){
		try{
	  		  input = theServer.getInputStream();
			  output = theServer.getOutputStream();
			  printStream = new PrintStream(output);
			  inputStream = new InputStreamReader(input);
			  bufferedReader = new BufferedReader(inputStream);
			  message = null;
			  message=bufferedReader.readLine();
	  		  while(message!=null){
	  	  			if(message.equals("hello")){
	  	  				  printStream.println(message);
	  	  				  message=bufferedReader.readLine();
	  	  			}
	  	  			else{
	  	  				String response=processMessage(message);
	  	  				if(!response.equals(""))
	  	  					printStream.println(response);
	  	  				message = bufferedReader.readLine();
	  	  				if(Server.wantCS==true){
	  	  					requestCS();
	  	  				}
	  	  			  }
	  			  
	  		  }
		} catch(Exception e){
			System.err.println(e);
		}
	}
	private synchronized String processMessage(String message){
		if(message.equals("release")){
			String tokens = lamport.pollFirst();
			String process = tokens.substring(tokens.indexOf(' '+1, tokens.indexOf(' ')+1));
			MultithreadedServer.processMessage(process);
			return "";
		}
		else{
			addToList(message);
			return message;
		}
	}
	private synchronized void addToList(String message){
		String requestID = message.substring(0, message.indexOf(' '+1, message.indexOf(' ')+1));
		String[] requestTick = requestID.split(" ");
		int tick = Integer.valueOf(requestTick[0]);
		int sid = Integer.valueOf(requestTick[1]);
		for(int i = 0; i<lamport.size(); i++){
			String compareID = lamport.get(i);
			int compareTick = Integer.valueOf(compareID.substring(0, compareID.indexOf(' ')));
			if(compareTick>tick)
				lamport.add(i, message);
			else if(compareTick==tick){
				int compareServer = Integer.valueOf(compareID.substring(compareID.indexOf(' ') + 1, compareID.indexOf(' '+1, compareID.indexOf(' '))));
				if (compareServer>sid)
					lamport.add(i, message);
			}
		}			
		}
		
		
		}
		order.get(tick).set(sid, true);
		
		
	}
    private synchronized static void lamport(){
    	Socket[] server = new Socket[Server.numServers];
    	for(int i = 0; i<Server.numServers; i++){
    		if(i!=Server.myPort){
        		try {
    				server[i]=new Socket(Server.addresses.get(i), Server.ports.get(i));
        		} catch (UnknownHostException e) {
    				e.printStackTrace();
        		} catch (IOException e) {
    				//alive = false;
    		  }
    		}
    	}
    	for(int i = 0; i<Server.numServers; i++){
    		if(!checkServer(server[i])){
    			Server.addresses.remove(i);
    			Server.ports.remove(i);
    			Server.numServers--;
    		}
    	}
    }
    private synchronized static void requestCS(){
    	String request = String.valueOf(LogicalClock.getClock()) + " " + String.valueOf(Server.ID) + " " + Server.message;
    	printStream.println(request);
    	lamport.add(request);
    	
    }
	private static boolean checkServer(Socket server){
		  	  boolean alive = true;
		  	  try {
		  			server.setSoTimeout(100);
		  		} catch (SocketException e2) {
		  			// TODO Auto-generated catch block
		  			e2.printStackTrace();
		  		}
		  	  try {
		  			input = server.getInputStream();
		  	  } catch (IOException e1) {
		  			e1.printStackTrace();
		  		}
		  	  	
		  	    try {
		  			output = server.getOutputStream();
		  		} catch (IOException e1) {
		  			e1.printStackTrace();
		  		}
		  	    printStream = new PrintStream(output);
		  	  	inputStream = new InputStreamReader(input);
		  	  	bufferedReader = new BufferedReader(inputStream);
		  	  	message = null;
		  	    String check = "hello";
		  	  	printStream.println(check);
		      	try {
		  			message = bufferedReader.readLine();
		  		} catch(SocketTimeoutException ste){
		  			alive = false;
		  		} catch (IOException e) {
		  			e.printStackTrace();
		  		}
		      	if(!message.equals("hello"))
		      		alive = false;
		  	    if(alive)
		  			try {
		  				server.setSoTimeout(0);
		  			} catch (SocketException e) {
		  				e.printStackTrace();
		  			}
		  	  return alive;
		    }
}