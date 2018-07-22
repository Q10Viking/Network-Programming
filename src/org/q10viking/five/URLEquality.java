package org.q10viking.five;

import java.net.MalformedURLException;
import java.net.URL;

public class URLEquality {

	public static void main(String[] args) {
		try {
			URL url1 = new URL("http://www.cnfeat.com");
			URL url2 = new URL("http://cnfeat.com");
			if(url1.equals(url2)) {
				System.out.println(url1+" is the same as "+url2);
			}else {
				System.out.println(url1+" is not the same as "+url2);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
		
	}

}
