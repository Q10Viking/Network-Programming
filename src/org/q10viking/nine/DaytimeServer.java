package org.q10viking.nine;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
	public final static int PORT = 13;
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(PORT)){
			System.out.println("Daytime server start...");
			while(true) {
				try(Socket connection = server.accept()){
					System.out.println("Get a connection: "+connection.toString());
					Writer out = new OutputStreamWriter(connection.getOutputStream(),"ASCII");
					Date now = new Date();
					out.write("Q10Viking: "+now.toString()+"\r\n");
					out.flush();
					connection.close();
				}catch(IOException e) {}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println("Daytime server stop...");
	}
}

/*
Daytime server start...
Get a connection: Socket[addr=/0:0:0:0:0:0:0:1,port=64621,localport=13]
*/
		