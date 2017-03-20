import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;

public class LamportClientThread extends Thread{
	private static Socket s;
	private static InputStream input;
    private static OutputStream output;
    private static PrintStream printStream;
    private static InputStreamReader inputStream;
    private static BufferedReader bufferedReader;
    private static String message = null;
    private static boolean first=true;
    private static int port;
    private static String ip;
    private static int sid;
    public LamportClientThread(String serverIP, int portNumber, int serverID){
    	ip = serverIP;
    	port=portNumber;
    	int sid=serverID;
    }
    public void run(){
    	while(true){
    		while(!Server.request.get(sid));
    		if(checkServer()){
    			requestCS();
        		Server.request.set(sid, false);
    		}
    		else
    			break;
    	}
    }
    private synchronized static void requestCS(){
		Server.clock.tick();
    	String request = String.valueOf(Server.clock.getClock()) + " " + String.valueOf(Server.ID) + " " + Server.message;
    	printStream.println(request);
    		
    }
    private static boolean checkServer(){
  	  boolean alive = true;
  	  if(first){
  		  try {
  				s=new Socket(ip, port);
  				first=false;
  		  } catch (UnknownHostException e) {
  				e.printStackTrace();
  		  } catch (IOException e) {
  				alive = false;
  		  }
  	  }
  	  try {
  			s.setSoTimeout(100);
  		} catch (SocketException e2) {
  			// TODO Auto-generated catch block
  			e2.printStackTrace();
  		}
  	  try {
  			input = s.getInputStream();
  	  } catch (IOException e1) {
  			e1.printStackTrace();
  		}
  	  	
  	    try {
  			output = s.getOutputStream();
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
  				s.setSoTimeout(0);
  			} catch (SocketException e) {
  				e.printStackTrace();
  			}
  	  return alive;
    }
}