//am73676_sr39533
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
    private static boolean death = false;
    private static String[] ip;
    private static int[] port;
    private static int ind = 0;
    private static boolean first = true;
    
	
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.valueOf(sc.nextLine());
    ip = new String[n];
    port = new int[n];
    for(int i=0; i<n; i++){
    	String iport = sc.nextLine();
    	String[] tokens = iport.split(":");
    	ip[i] = tokens[0];
    	port[i] = Integer.valueOf(tokens[1]);
    }

    while(sc.hasNextLine()) {
      String cmd = sc.nextLine();
      String[] tokens = cmd.split(" ");
      boolean isIntP = true;
      boolean isIntO = true;
  	  try {
		    Integer.parseInt(tokens[3]);
		  } catch (Exception e) {
		    isIntP = false;
		  }
  	  try {
		    Integer.parseInt(tokens[1]);
		  } catch (Exception e) {
		    isIntO = false;
		  }
      if (tokens[0].equals("purchase") && tokens.length==4 && isIntP) {
    	  if(first == true){
    		  first = false;
    	      while(!checkServer(ind)){
    		  	   ind++;
    	      }
    	  }
    	  tcpMethod(cmd, tokens[0]);
    	  while(death==true){
        	  while(!checkServer(ind)){
            		ind++;
        	  }
        	  tcpMethod(cmd, tokens[0]);
        	  if(death==true)
        		  ind++;
    	  }
      } else if (tokens[0].equals("cancel") && tokens.length==2 && isIntO) {
    	  if(first == true){
    		  first = false;
    	      while(!checkServer(ind)){
    		  	   ind++;
    	      }
    	  }
    	  tcpMethod(cmd, tokens[0]);
    	  while(death==true){
        	  while(!checkServer(ind)){
            		ind++;
        	  }
        	  tcpMethod(cmd, tokens[0]);
        	  if(death==true)
        		  ind++;
    	  }
      } else if (tokens[0].equals("search") && tokens.length==2) {
    	  if(first == true){
    		  first = false;
    	      while(!checkServer(ind)){
    		  	   ind++;
    	      }
    	  }
    	  tcpMethod(cmd, tokens[0]);
    	  while(death==true){
        	  while(!checkServer(ind)){
            		ind++;
        	  }
        	  tcpMethod(cmd, tokens[0]);
        	  if(death==true)
        		  ind++;
    	  }
      } else if (tokens[0].equals("list") && tokens.length==1) {
    	  if(first == true){
    		  first = false;
    	      while(!checkServer(ind)){
    		  	   ind++;
    	      }
    	  }
    	  tcpMethod(cmd, tokens[0]);
    	  while(death==true){
        	  while(!checkServer(ind)){
            		ind++;
        	  }
        	  tcpMethod(cmd, tokens[0]);
        	  if(death==true)
        		  ind++;
    	  }
      } else {
        System.out.println("ERROR: No such command");
      }
    }
  }
  private static void tcpMethod(String output, String action){
	  	death=false;
	  	printStream.println(output);
	  	if(action.equals("purchase")){
	    	try {
				message = bufferedReader.readLine();
	    	} catch(SocketTimeoutException ste){
	    		death = true;
	    		//System.out.println("1");
				return;	
			} catch (IOException e) {
				death = true;
				//e.printStackTrace();
				return;
			}
	    	if(message==null){
	    		death=true;
	    		return;
	    	}
	    	System.out.println(message);
	  	}
	  	else if(action.equals("cancel")){
	    	try {
				message = bufferedReader.readLine();
	    	} catch(SocketTimeoutException ste){
	    		death = true;
	    		//System.out.println("2");
				return;	
			} catch (IOException e) {
				death = true;
				//e.printStackTrace();
				return;
			}
	    	if(message==null){
	    		death=true;
	    		return;
	    	}
	    	System.out.println(message);
	  	}
	  	else if(action.equals("search")){
	    	try {
				message = bufferedReader.readLine();
	    	} catch(SocketTimeoutException ste){
	    		death = true;
	    		//System.out.println("3");
				return;	
			} catch (IOException e) {
				death = true;
				//e.printStackTrace();
				return;
			}
	    	if(message==null){
	    		death=true;
	    		return;
	    	}
	    	if(message.substring(0, 1).equals("N")){
	    		System.out.println(message);
	    	}
	    	else{
		    	int n = Integer.valueOf(message);
		    	for(int i = 0; i<n; i++){
			    	try {
						message = bufferedReader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
			    	System.out.println(message);
		    	}
	    	}
	  	}
	  	else if(action.equals("list")){
	    	try {
				message = bufferedReader.readLine();
			} catch(SocketTimeoutException ste){
				death = true;
				//System.out.println("4");
				return;
			} catch (IOException e) {
				death = true;
				//e.printStackTrace();
				return;
			}
	    	if(message==null){
	    		death=true;
	    		return;
	    	}	
	    	int n = Integer.valueOf(message);
	    	for(int i = 0; i<n; i++){
		    	try {
					message = bufferedReader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	System.out.println(message);
	    	}
	  	}
	  		

  }
  
  private static boolean checkServer(int index){
	  boolean alive = true;
	  if (index==port.length)
		  System.out.println("NO MORE SERVERS! YOU ARE NOT TESTING THIS PROPERLY!");
		  try {
				s=new Socket(ip[index], port[index]);
		  } catch (UnknownHostException e) {
				e.printStackTrace();
		  } catch (IOException e) {
				alive = false;
				return alive;
		  }
		  try {
				s.setSoTimeout(100);
			} catch (SocketException e2) {
				alive = false;
				return alive;
			}
		  
		  try {
				input = s.getInputStream();
		  } catch (IOException e1) {
			  	alive = false;
				return alive;
			}
		  	
		    try {
				output = s.getOutputStream();
			} catch (IOException e1) {
				alive = false;
				return alive;
			}
		    
		    printStream = new PrintStream(output);
		  	inputStream = new InputStreamReader(input);
		  	bufferedReader = new BufferedReader(inputStream);
		  	message = null;
		  	
		    String check = "hi";
		  	printStream.println(check);
		  	
	    	try {
				message = bufferedReader.readLine();
			} catch(SocketTimeoutException ste){
				alive = false;
				return alive;
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	  
	  
	    	if(!message.equals("hi")){
	    		alive = false;
	    		return alive;
	    	}
	    	else{
	  		  try {
					s.setSoTimeout(0);
				} catch (SocketException e2) {
					alive = false;
					return alive;
				}
	    	}
	    	
	    	return alive;
  }

}