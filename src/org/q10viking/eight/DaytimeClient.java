package org.q10viking.eight;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class DaytimeClient {

	public static void main(String[] args) {
		//在NIST选择一个时间服务器
		String hostname = "time.nist.gov";
		int port = 13;
		Socket socket = null;
		
		try {
			//在网络上建立连接
			socket = new Socket(hostname,port);
			//设置超时时间15秒
			socket.setSoTimeout(15000);
			InputStream in = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in,"ASCII");
			//存储时间
			StringBuilder time = new StringBuilder();
			int c;
			while((c = reader.read()) != -1) {
				time.append((char)c);
			}
			
			System.out.println(time.toString());
		}catch (IOException e) {
			System.err.println("服务器未在端口13上监听而失败");
			
		}finally {
			//关闭Socket
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					
				}
			}
		}
				
	}
}
/*
58326 18-07-27 12:56:27 50 0 0 751.5 UTC(NIST) *

*/