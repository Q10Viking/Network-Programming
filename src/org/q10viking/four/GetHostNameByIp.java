package org.q10viking.four;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostNameByIp {
	public static void main(String[] args) {
		try {
			//InetAddress address = InetAddress.getByName("www.baidu.com");
			byte[] address = {(byte)152,19,(byte)134,40};
			InetAddress add = InetAddress.getByAddress(address);
		
			System.out.println(add.getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

/*www.baidu.com/119.75.213.61
*/