package org.q10viking.seven;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HeaderViewer {
	
	//final static String website = "http://www.oreilly.com";
	final static String website = "https://www.cau.edu.cn";
	public static void main(String[] args) {
		try {
			URL u = new URL(website);
			URLConnection uc = u.openConnection();
			System.out.println("Content-Type: "+uc.getContentType());			
			if(uc.getContentEncoding() != null) {
				System.out.println("Content-encoding: "+uc.getContentEncoding());
			}
			
			if(uc.getDate() != 0) {
				System.out.println("Date: "+new Date(uc.getDate()));
			}
			
			if(uc.getLastModified() != 0) {
				System.out.println("Last modified: "+new Date(uc.getLastModified()));
			}
			if(uc.getExpiration() != 0) {
				System.out.println("Expiration date: "+new Date(uc.getExpiration()));
			}
			if(uc.getContentLength() != -1) {
				System.out.println("Content-length: "+uc.getContentLength());
			}

		} catch (MalformedURLException e) {
			System.out.println(website+" is not a URL I understand");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}

/*Content-Type: text/html
Date: Fri Jul 27 14:49:27 CST 2018
Last modified: Thu Jul 26 12:01:56 CST 2018
Content-length: 27539
*/