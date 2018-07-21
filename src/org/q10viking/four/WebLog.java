package org.q10viking.four;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebLog {
	public static String WBN = "weblog.txt";
	
	public static void main(String[] args) {
		try(FileInputStream fin = new FileInputStream(WBN);
				Reader in = new InputStreamReader(fin);
				BufferedReader bin = new BufferedReader(in);) {
			
			for(String entry = bin.readLine();
					entry != null;
					entry = bin.readLine()) {
				//分解IP
				int index = entry.indexOf(' ');
				String ip = entry.substring(0, index);
				String theRest = entry.substring(index);
				try {
				InetAddress address = InetAddress.getByName(ip);
				System.out.println(address.getHostName()+theRest);
				}catch(UnknownHostException ex) {
					System.err.println(entry);
				}
			}
			
		}catch(IOException ex) {
			System.out.println("Exception "+ex);
		}
	}

}


/*www.ibiblio.org - - [07/Mar/2018:16:05:49 -0800] "GET /mailman/listinfo/hsdivision HTTP/1.1" 200 6291
*/