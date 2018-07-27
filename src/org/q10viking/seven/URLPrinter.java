package org.q10viking.seven;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLPrinter {

	public static void main(String[] args) {
		
		try {
			URL u = new URL("https://www.cau.edu.cn");
			URLConnection uc = u.openConnection();
			System.out.println(uc.getURL());
			uc.setConnectTimeout(3000);
			System.out.println(uc.getConnectTimeout());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
https://www.cau.edu.cn*/