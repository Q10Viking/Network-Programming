package org.q10viking.four;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {
 
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("47.91.169.15");
		//	InetAddress address = InetAddress.getLocalHost();
			int result = getVersion(address);	
			System.out.println("Ipv"+result+": "+address.getCanonicalHostName());
		} catch (UnknownHostException e) {
			System.out.println("Could not find this computer's address.");
		}
		
	}
	
	public static int getVersion(InetAddress ia) {
		byte[] address = ia.getAddress();
		if(address.length == 4) return 4;
		else if(address.length == 16) return 6;
		else return -1;
	}

}
/*
Ipv4: 152.19.134.40
Ipv4: 119.75.216.20
*/

