import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class MultithreadedServer extends Thread{
	Socket theClient;
    public MultithreadedServer(Socket s){
    	theClient = s;	
    }
    public void run(){
  	  try{
  		  InputStream input = theClient.getInputStream();
  		  OutputStream output = theClient.getOutputStream();
  		  PrintStream printStream = new PrintStream(output);
  		  InputStreamReader inputStream = new InputStreamReader(input);
  		  BufferedReader bufferedReader = new BufferedReader(inputStream);
  		  String message = null;
  		  message = bufferedReader.readLine();
  		  //implement methods that take the input of the message
  		  
  	  }
  	  catch(Exception e){
  		  System.err.println(e);
  	  }
    }
}
