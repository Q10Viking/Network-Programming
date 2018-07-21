package org.q10viking.four;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
public class InterfaceLister {

	public static void main(String[] args) throws SocketException {
		
		NetworkInterface i = NetworkInterface.getByName("eth11");
		Enumeration addresses = i.getInetAddresses();
		while(addresses.hasMoreElements()) {
			System.out.println(addresses.nextElement());
		}
		System.out.println(i.getName());
		System.out.println(i.getDisplayName());
		
		
		System.out.println("===================================");
		NetworkInterface i2 = NetworkInterface.getByName("eth4");
		Enumeration addresses2 = i2.getInetAddresses();
		while(addresses2.hasMoreElements()) {
			System.out.println(addresses2.nextElement());
		}
		System.out.println(i2.getName());
		System.out.println(i2.getDisplayName());
		
		
	}

}

/*
/100.64.15.126
/fe80:0:0:0:61ae:d49c:2b:6735%eth11
eth11
TAP-Windows Adapter V9
===================================
/219.225.104.143
/2001:250:209:1609:18a0:7b8b:8adf:d389
/2001:250:209:1609:38ad:3e2e:742:d2f1
/fe80:0:0:0:18a0:7b8b:8adf:d389%eth4
eth4
Realtek PCIe GBE Family Controller
*/