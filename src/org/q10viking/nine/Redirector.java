package org.q10viking.nine;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//HTTP重定向服务器
public class Redirector {
	private static final Logger logger = Logger.getLogger("Redirector");
	private final int port;
	private final String newSite;
	
	public Redirector(String newSite,int port) {
		this.port = port;
		this.newSite = newSite;
	}
	
	public void start() {
		System.out.println("Server start...");
		try(ServerSocket server = new ServerSocket(port)){
			logger.info("Redirecting connections on port "+
							server.getLocalPort()+" to "+newSite);
			while(true) {
				try {
					Socket s = server.accept();
					Thread t = new RedirectThread(s);
					t.start();
				}catch(IOException e) {
					logger.warning("Exception accepting connection");
				}catch(RuntimeException ex) {
					logger.log(Level.SEVERE,"Unexpected error",ex);
				}
			}
		} catch (BindException e) {
			logger.log(Level.SEVERE,"Couldn't start server.",e);
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Error opening server socket", e);
		}
	}
	
	private class RedirectThread extends Thread{
		private final Socket connection;
		RedirectThread(Socket connection){
			this.connection = connection;
		}
		
		public void run() {
			try {
				Writer out = new BufferedWriter(
						new OutputStreamWriter(
								connection.getOutputStream(),"US-ASCII"));
				
				Reader in = new InputStreamReader(
						new BufferedInputStream(
								connection.getInputStream()));
				
				//只读取第一行，这就是我们需要的内容
				StringBuilder request = new StringBuilder(80);
				while(true) {
					int c = in.read();
					if(c == '\r' || c=='\n' || c==-1) break;
					request.append((char)c);
				}
				
				String get = request.toString();
				System.out.println(get);
				
				String[] pieces = get.split(" ");
				System.out.println(pieces.length);
				String theFile = pieces[1];
				System.out.println(theFile);
				
				//如果是HTTP/1.0及以后的版本，则发送一个MIME首部
				if(get.indexOf("HTTP") != -1) {
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: "+now+"\r\n");
					out.write("Server: Redirector 1.1\r\n");
					out.write("Location: "+newSite+theFile+"\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
					//并不是所有浏览器都支持重定向，所以我们要
					//生成HTML指出文档转移到了哪里
					out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
					out.write("<BODY><H1>Document moved</H1>\r\n");
					out.write("The document "+theFile
							+ " has moved to\r\n<a href=\""+newSite+theFile+"\">"
							+ newSite+theFile
							+ "</a>.\r\n Please update your bookmarks<P>");
					out.write("</BODY></HTML>");
					out.flush();
					logger.log(Level.INFO, "Redirected "+connection.getRemoteSocketAddress());
					
				
			}catch (IOException e) {
				logger.log(Level.WARNING, "Error talking to "+connection.getRemoteSocketAddress(),e);
			}finally {
				try {
					connection.close();
				}catch(IOException ex) {} 
			}
		}
	}
	public static void main(String[] args) {
		
		int thePort = 8080;
		String theSite="http://www.cau.edu.cn/";
		if(theSite.endsWith("/")) {
			theSite = theSite.substring(0,theSite.length()-1);
		}
	//	System.out.println(theSite);
		
		Redirector redirector = new Redirector(theSite,thePort);
		redirector.start();
		
	}

}
