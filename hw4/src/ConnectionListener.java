//sr39533_am73676
import java.io.*;
import java.net.*;

public class ConnectionListener extends Thread{
	private ServerSocket ss;
	InputStream input;
	OutputStream output;
	PrintStream printStream;
	InputStreamReader inputStream;
	BufferedReader bufferedReader;
	public ConnectionListener(int port){
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ConnectionListener error - couldn't set up ServerSocket");
		}
	}
	
	@Override
	public void run(){
		Socket s = null; //the socket that connect to the server
		while(true){
			try {
				s = ss.accept();
			} catch (IOException e) {
				System.out.println("ConnectionListener error - connection could not be accepted");
			}
			
			if (s == null)//if the accepted connection is null, try again
				continue;
			
			//Read the first line of the input buffer to determine if it is a client or another server trying to contact you
	  		try {
				input = s.getInputStream();
			} catch (IOException e) {
				System.out.println("ConnectionListener error - couldn't get input stream");
			}
	  		
			try {
				output = s.getOutputStream();
			} catch (IOException e) {
				System.out.println("ConnectionListener error - couldn't get output stream");
			}
			
			printStream = new PrintStream(output);
			inputStream = new InputStreamReader(input);
			bufferedReader = new BufferedReader(inputStream);
			String message = null;
			
			try{
				message = bufferedReader.readLine();
			}
			catch(IOException i){
				System.out.println("ConnectionListener error - couldn't read buffer");
			}
			
  			if(message.equals("hi")){
  				printStream.println(message);
  				Thread t = new MultithreadedServer(s);
  				t.start();
  			}
  			else if(message.equals("hello")){
  				printStream.println(message);
				Thread l = new Lamport(s);
				l.start();
  			}
  			
		}
	}
}
