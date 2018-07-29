package org.q10viking.eight;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//查看指定主机上前1024个端口中哪些安装有TCP服务器
public class LowPortScanner {

	public static void main(String[] args) {
		String host = args.length>0 ?args[0]:"localhost";
		for(int i=1;i<1024;i++) {
			try {
				Socket s = new Socket(host,i);
				System.out.println("There is a server on port "+i+" of "+host);
				s.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				//这个端口在服务器未被开启
			}
		}
	}

}

/*
There is a server on port 23 of localhost
There is a server on port 135 of localhost
There is a server on port 443 of localhost
There is a server on port 445 of localhost
There is a server on port 623 of localhost
There is a server on port 902 of localhost
There is a server on port 912 of localhost
*/
