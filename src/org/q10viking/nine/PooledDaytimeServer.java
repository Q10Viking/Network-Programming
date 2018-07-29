package org.q10viking.nine;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledDaytimeServer {
	public final static int PORT = 13;
	public static void main(String[] args) {
			System.out.println("Server start...");
			ExecutorService pool = Executors.newFixedThreadPool(50);
			try(ServerSocket server = new ServerSocket(PORT)){
				while(true) {
					Socket connection = server.accept();
					System.out.println("Get a connection...");
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	private static class DaytimeTask implements Callable<Void>{
		
		private Socket connection;
		DaytimeTask(Socket connection){
			this.connection = connection;
		}
		@Override
		public Void call() throws Exception {
			try {
				Writer out =  new OutputStreamWriter(connection.getOutputStream(),"ASCII");
				Date now = new Date();
				out.write("Q10Viking: "+now.toString()+"\r\n");
				out.flush();
			}catch(IOException e) {
				
			}finally {
				try {
				connection.close();
				}catch(IOException e) {}
			}
			return null;
		}
		
	}

}
