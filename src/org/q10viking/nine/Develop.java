package org.q10viking.nine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Develop {
	public final static int DEFAULT_PORT = 80;
	public final static String DEFAULT_HOST = "localhost";
	public final static String DEFAUTL_FILE = "/index.html";
	private int port = DEFAULT_PORT;
	private String host;
	private String filename;

	public Develop(String host,int port,String filename) {
		this.host = host;
		if(port == -1)
			this.port = DEFAULT_PORT;
		else
			this.port = port;
		this.filename = filename;
	}
	
	public Develop(String hostname,int port) throws IOException {
		this(hostname,port,DEFAUTL_FILE);
	}
	
	public Develop(String hostname)throws UnknownHostException {
		this(hostname,DEFAULT_PORT,DEFAUTL_FILE);
	}
	
	public Develop() throws IOException {
		this(DEFAULT_HOST,DEFAULT_PORT);
	}
	
	public String sendRequest() {
		Socket socket = new Socket();
		try {
			SocketAddress address = new InetSocketAddress(InetAddress.getByName(host),port);
			socket.connect(address);
			
			Writer out 
			= new OutputStreamWriter(socket.getOutputStream(),"ASCII");
		
			BufferedReader in = new BufferedReader(new
					InputStreamReader(socket.getInputStream(),"UTF-8"));
			//deal with GET request
			sendHeader(out);
			
			StringBuilder response = new StringBuilder();
			String theLine = null;
			
			while((theLine=in.readLine()) != null) {
				response.append(theLine);
				response.append("\r\n");
			}
			
			return response.toString();
			
			
		} catch (IOException e) {
			System.out.println("Can't connect to server.");
			return null;
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	
	private void sendHeader(Writer out)
			throws IOException{
		out.write("GET "+filename+" HTTP/1.0\r\n");
		out.write("User-Agent: Q10Viking-Java/1.8.0_141\r\n");
		out.write("Host: "+host+":"+port+"\r\n");
		out.write("Accept: text/html, image/gif, image/jpeg\r\n");
		out.write("Connection: keep-alive\r\n\r\n");
		out.flush();
	//	out.close();
	}
	
	
}
