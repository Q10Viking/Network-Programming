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
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDaytimeServer {
	public final static int PORT = 7;
	private final static Logger auditLogger = Logger.getLogger("requests");
	private final static Logger errorLogger = Logger.getLogger("errors");
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(50);
		try(ServerSocket server = new ServerSocket(PORT)){
			System.out.println("Server start...");
			while(true) {
				try {
					Socket connection = server.accept();
					System.out.println("Get a connection...");
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				}catch(IOException ex) {
					errorLogger.log(Level.SEVERE, "accept error ",ex);
				}catch(RuntimeException ex) {
					errorLogger.log(Level.SEVERE, "unexcepted error", ex);
				}
			}
			
			
		} catch (IOException e) {
			errorLogger.log(Level.SEVERE, "Couldn't start server ",e);
		} catch(RuntimeException ex) {
			errorLogger.log(Level.SEVERE, "Couldn't start server: ",ex.getMessage());
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
				Date now = new Date();
				//先写入日志记录以防万一客户端开连接
				auditLogger.info(now+" "+connection.getRemoteSocketAddress());
				Writer out = new OutputStreamWriter(connection.getOutputStream(),"ASCII");
				out.write(now.toString()+"\r\n");
				out.flush();
			}catch(IOException ex) {
				
			}finally {
				try {
					connection.close();
				}catch(IOException ex) {
					
				}
			}
			return null;
		}
		
	}

}

/*
Server start...
Get a connection...
七月 29, 2018 11:09:24 下午 org.q10viking.nine.LoggingDaytimeServer$DaytimeTask call
信息: Sun Jul 29 23:09:24 CST 2018 /0:0:0:0:0:0:0:1:20978
Get a connection...
七月 29, 2018 11:10:24 下午 org.q10viking.nine.LoggingDaytimeServer$DaytimeTask call
信息: Sun Jul 29 23:10:24 CST 2018 /0:0:0:0:0:0:0:1:21062

*/
