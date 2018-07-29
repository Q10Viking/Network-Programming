package org.q10viking.eight;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketInfo {

	public static void main(String[] args) {
		String[] hosts = {"www.oreilly.com","www.oreilly.com","www.cau.edu.cn","login.ibiblio.org"};
		
		for(String host: hosts) {
			try(Socket theSocket = new Socket(host,80)) {
				//远程地址
				String remoteAddress = theSocket.getInetAddress().toString();
				//远程端口
				int remoteport = theSocket.getPort();
				//本地地址
				String localAddress = theSocket.getLocalAddress().toString();
				//本地端口
				int localport = theSocket.getLocalPort();
				System.out.println("Connected to "+remoteAddress
						+" on port "+ remoteport
						+" from port "+localport
						+" of "+localAddress);
			} catch (UnknownHostException e) {
				System.out.println("I can't find "+host);
			} catch (IOException e) {
				System.err.println("Could not connect to "+host);
			}
		}
	}

}


/*
Connected to www.oreilly.com/104.102.146.109 on port 80 from port 4285 of /100.64.15.230
Connected to www.oreilly.com/104.102.146.109 on port 80 from port 4286 of /100.64.15.230
Connected to www.cau.edu.cn/202.205.80.179 on port 80 from port 4287 of /100.64.15.230
Could not connect to login.ibiblio.org
*/