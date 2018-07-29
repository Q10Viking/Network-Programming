package org.q10viking.nine;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

//多线程daytime服务器
public class MultithreadedDaytimeServer {
	public final static int PORT = 13;
	public static void main(String[] args) {
		System.out.println("Daytime server start...");
		try(ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				try {
					Socket connection = server.accept();
					
					Thread task = new DaytimeThread(connection);
					task.start();
				}catch(IOException ex) {
				
				}
			}
		} catch (IOException e) {
			System.err.println("Can't not start server.");
		}
	}
	
	private static class DaytimeThread extends Thread{
		private Socket connection;
		DaytimeThread(Socket connection){
			this.connection = connection;
			System.out.println("Get a connection: "+connection.toString());
		}
		
		@Override
		public void run() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream(),"ASCII");
				Date now = new Date();
				out.write("Q10Viking: "+now.toString()+"\r\n");
				out.flush();
			}catch(IOException ex) {
				System.err.println(ex);
			}finally {
				try {
					if(connection != null)
						connection.close();
				}catch(IOException e) {}
			}
		}
	}

}

/*Daytime server start...
Get a connection: Socket[addr=/0:0:0:0:0:0:0:1,port=1114,localport=13]
*/		
		
