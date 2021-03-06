package testing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
//This class contains threads to run
import edu.umd.cs.mtc.MultithreadedTestCase;
public class TestUDPTCP1 extends MultithreadedTestCase{
	//Issue: if output from any program is not terminated by new line code 
	//will go past .ready() and hang on .readLine()
	public void thread1() throws InterruptedException{
		ServerTester s=new ServerTester("server");
		waitForTick(0);
		s.start();
//		waitForTick(1);
//		System.out.println("Tick 1");
//		waitForTick(2);
//		System.out.println("Tick 2");
//		waitForTick(3);
//		System.out.println("Tick 3");
//		waitForTick(4);
//		System.out.println("Tick 4");
//		waitForTick(5);
//		System.out.println("Tick 5");
//		waitForTick(6);
//		System.out.println("Tick 6");
//		waitForTick(7);
//		System.out.println("Tick 7");
//		waitForTick(8);
//		System.out.println("Tick 8");
//		waitForTick(9);
//		System.out.println("Tick 9");

		waitForTick(100);
		s.getErrors();
		s.flushBuffer();
		s.terminate();
		
		
	}

	public void thread2() throws InterruptedException{
		ClientTester a=new ClientTester("Client1");
		waitForTick(1);
		a.start();
		waitForTick(2);
		a.executeCommand("purchase User1 ps4 17");
		waitForTick(3);
		a.executeCommand("purchase User1 xbox 8");
		waitForTick(4);
		a.executeCommand("purchase User1 camera 10");
		waitForTick(5);
		a.executeCommand("list");
		waitForTick(6);
		a.executeCommand("cancel 1");
		waitForTick(7);
		a.executeCommand("purchase User1 ps4 1");
		waitForTick(8);
		a.executeCommand("list");
		waitForTick(9);
		a.executeCommand("setmode U");
		waitForTick(10);
		a.executeCommand("list");
		waitForTick(11);
		a.executeCommand("purchase xbox laptop 15");
		waitForTick(12);
		a.executeCommand("purchase xbox phone 5");
		waitForTick(13);
		a.executeCommand("list");
		waitForTick(14);
		a.executeCommand("cancel 6");
		waitForTick(15);
		a.executeCommand("cancel 7");
		waitForTick(16);
		a.executeCommand("purchase ps4 laptopwe 213123");
		waitForTick(17);
		a.executeCommand("cancel 8");
		waitForTick(18);
		a.executeCommand("search xbox");
		waitForTick(19);
		a.executeCommand("list");
		waitForTick(20);
		
		a.getErrors();
		a.terminate();
		waitForTick(99);
		
	}
	public void thread3() throws InterruptedException{
		ClientTester a=new ClientTester("Client2");
		waitForTick(1);
		a.start();
		waitForTick(2);
		a.executeCommand("purchase User2 ps4 17");
		waitForTick(3);
		a.executeCommand("search User1");
		waitForTick(4);
		a.executeCommand("list");
		waitForTick(5);
		a.executeCommand("list");
		waitForTick(6);
		a.executeCommand("setmode U");
		waitForTick(7);
		a.executeCommand("purchase User2 ps4 16");
		waitForTick(8);
		a.executeCommand("search User1");
		waitForTick(9);
		a.executeCommand("search User2");
		waitForTick(10);
		a.executeCommand("setmode T");
		waitForTick(11);
		a.executeCommand("purchase xbox laptop 15");
		waitForTick(12);
		a.executeCommand("purchase xbox phone 15");
		waitForTick(13);
		a.executeCommand("search xbox");
		waitForTick(14);
		a.executeCommand("cancel 6");
		waitForTick(15);
		a.executeCommand("cancel 7");
		waitForTick(16);
		a.executeCommand("purchase ps4 ps4 1340");
		waitForTick(17);
		
		
		
		a.getErrors();
		a.terminate();
		waitForTick(99);
		
	}
	public void thread4() throws InterruptedException{
		ClientTester a=new ClientTester("Client3");
		waitForTick(1);
		a.start();
		waitForTick(2);
		a.executeCommand("setmode U");
		waitForTick(3);
		a.executeCommand("search xbox");
		waitForTick(13);
		a.executeCommand("search xbox");
		waitForTick(14);
		a.executeCommand("search xbox");
		waitForTick(15);
		a.executeCommand("search xbox");
		waitForTick(16);
		a.executeCommand("search xbox");
		waitForTick(17);
		a.getErrors();
		a.terminate();
		waitForTick(99);
		
	}
	public void thread5() throws InterruptedException{
		ClientTester a=new ClientTester("Client4");
		waitForTick(1);
		a.start();
		waitForTick(2);
		a.executeCommand("setmode U");
		waitForTick(13);
		a.executeCommand("list");
		waitForTick(14);
		a.executeCommand("list");
		waitForTick(15);
		a.executeCommand("list");
		waitForTick(16);
		a.executeCommand("list");
		waitForTick(17);
		
		
		a.getErrors();
		a.terminate();
		waitForTick(99);
		
	}
	



}
class ClientTester{
	//Address of server
		final static String serverAddr="localhost";
		//Tcp port
		final static int tcpPort=1027;
		//Udp port
		final static int udpPort=1030;
		//Max Delay to get response and write out; if response is not received within this time then truncated
		final static long timeOut=1000;
		//Folder where Client.class is located
		final static String clientFolder="/Users/atreyamisra/Google Drive/Documents/Spring 2017/Concurrent/Workspace/hw3q3test/bin";
		
		//Folder to output text files of client commands
		final static String logsFolder="/Users/atreyamisra/Google Drive/Documents/Spring 2017/Concurrent/Workspace/hw3q3/src/logs/";
		String threadName;
		BufferedReader inConsole;
		BufferedWriter outConsole;
		BufferedWriter outFile;
		Process javaP;
		public ClientTester(String threadName){
			this.threadName=threadName;
		}
		public void start(){
			
			System.out.println(threadName+" started");
			List<String> args=new ArrayList<String>();//Set arguments to setup client
			args.add("java");
			args.add("-cp");
			args.add(clientFolder);
			args.add("Client");
			args.add(serverAddr);
			args.add(tcpPort+"");
			args.add(udpPort+"");
			ProcessBuilder pb=new ProcessBuilder(args);
			javaP=null;
			try {
				javaP=pb.start();//Start the java process on console(not shown)
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inConsole=initializeInputStream(javaP);//Stream that gets input from console
			 outConsole=initializeOutputStream(javaP);//Stream that writes output to console
			 outFile=null;//File to write out in logs
			try {
				outFile=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logsFolder+"\\"+threadName+".txt")));
			}  catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
			
			
		}
		public void getErrors() throws InterruptedException {
			
			
			
			
	        String error="";
			try {
				error = loadStream(javaP.getErrorStream());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(error.trim().length()==0){
				System.out.println("No errors for " + threadName);
			}else{
	        System.out.println("Standard Error For " + threadName+" :");
	        System.out.println(error);
			}
			
			
			
			

		}
		
		public void terminate(){
			try {
				inConsole.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outConsole.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			javaP.destroy();//Destroy the current process
			System.out.println(""+threadName+" Terminated");
		}
		
		
		private  BufferedReader initializeInputStream(Process process){
	        InputStream stdout = process.getInputStream();
	        return new BufferedReader(new InputStreamReader(stdout));

		}
		
		private  BufferedWriter initializeOutputStream(Process process){
			OutputStream stdin = process.getOutputStream(); 
			return new BufferedWriter(new OutputStreamWriter(stdin));
		}
		 private  String loadStream(InputStream s) throws Exception
		    {
		        BufferedReader br = new BufferedReader(new InputStreamReader(s));
		        StringBuilder sb = new StringBuilder();
		        String line;
		        long startTimeMilli=System.currentTimeMillis();
		        while(Math.abs(System.currentTimeMillis()-startTimeMilli)<=timeOut){
		        	if(br.ready()){
		        		line=br.readLine();
		        		sb.append(line).append("\n");
		        	}
		            
		           
		        }
		        sb.append("");
		        return sb.toString();
		    }
		 public void executeCommand(String cmd){
				try {
					outConsole.write(cmd+"\n");
					outConsole.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outFile.write(cmd+"\n");
					outFile.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long startTimeMilli=System.currentTimeMillis();
				String resp;
				boolean success=true;
				while(Math.abs(startTimeMilli-System.currentTimeMillis())<=timeOut || success){
					try {
						
						if(inConsole.ready()){
							resp=inConsole.readLine();
							if(resp!=null && resp.trim().length()>0){
								outFile.write(resp+"\n");
								outFile.flush();
								
								success=true;
							}
							else{
								success=false;
							}
						}
						else{
							success=false;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				

			}
	
	
}
class ServerTester{
	//Tcp port
	final static int tcpPort=1027;
	//Udp port
	final static int udpPort=1030;
	//Folder where Server.class is located
	final static String serverFolder="/Users/atreyamisra/Google Drive/Documents/Spring 2017/Concurrent/Workspace/hw3q3/bin";
	//Full file path to inventory file
	final static String inventoryFile="/Users/atreyamisra/Google Drive/Documents/Spring 2017/Concurrent/Workspace/hw3q3/src/inventory.txt";
	//Max Delay to get response and write out; if response is not received within this time then truncated
	final static long timeOut=500;
	//Folder to output text files of client commands
	final static String logsFolder="/Users/atreyamisra/Google Drive/Documents/Spring 2017/Concurrent/Workspace/hw3q3/src/logs/";
	String threadName;
	BufferedReader inConsole;
	BufferedWriter outConsole;
	BufferedWriter outFile;
	Process javaP;
	public ServerTester(String threadName){
		this.threadName=threadName;
	}
	public void start(){
		
		System.out.println(threadName+" started");
		List<String> args=new ArrayList<String>();//Set arguments to setup client
		args.add("java");
		args.add("-cp");
		args.add(serverFolder);
		args.add("Server");
		args.add(tcpPort+"");
		args.add(udpPort+"");
		args.add(inventoryFile);
		ProcessBuilder pb=new ProcessBuilder(args);
		javaP=null;
		try {
			javaP=pb.start();//Start the java process on console(not shown)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inConsole=initializeInputStream(javaP);//Stream that gets input from console
		 outConsole=initializeOutputStream(javaP);//Stream that writes output to console
		 outFile=null;//File to write out in logs
		try {
			outFile=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logsFolder+"\\"+threadName+".txt")));
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		
		
	}
	public void flushBuffer(){
		try {
			while(inConsole.ready()){
				try {
					outFile.write(inConsole.readLine()+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getErrors() throws InterruptedException {
		
		
		
		
        String error="";
		try {
			error = loadStream(javaP.getErrorStream());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(error.trim().length()==0){
			System.out.println("No errors for " + threadName);
		}else{
        System.out.println("Standard Error For " + threadName+" :");
        System.out.println(error);
		}
		
		
		
		

	}
	
	public void terminate(){
		try {
			inConsole.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outConsole.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javaP.destroy();//Destroy the current process
		System.out.println(""+threadName+" Terminated");
	}
	
	private static BufferedReader initializeInputStream(Process process){
        InputStream stdout = process.getInputStream();
        return new BufferedReader(new InputStreamReader(stdout));

	}
	
	private static BufferedWriter initializeOutputStream(Process process){
		OutputStream stdin = process.getOutputStream(); 
		return new BufferedWriter(new OutputStreamWriter(stdin));
	}
	 private static String loadStream(InputStream s) throws Exception
	    {
	        BufferedReader br = new BufferedReader(new InputStreamReader(s));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        long startTimeMilli=System.currentTimeMillis();
	        while(Math.abs(System.currentTimeMillis()-startTimeMilli)<=timeOut){
	        	if(br.ready()){
	        		line=br.readLine();
	        		sb.append(line).append("\n");
	        	}
	            
	           
	        }
	        sb.append("");
	        return sb.toString();
	    }
	
}
