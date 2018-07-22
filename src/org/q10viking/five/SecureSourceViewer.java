package org.q10viking.five;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {

	public static void main(String[] args) {
		Authenticator.setDefault(new DialogAuthenticator());
		String website = "http://api.fanfou.com/account/verify_credentials.json";
		URL u;
		try {
			u = new URL(website);
			try(InputStream in=new BufferedInputStream(u.openStream())){
				Reader r = new InputStreamReader(in);
				int c;
				while((c = r.read()) != -1) {
					System.out.print((char)c);
				}
				System.out.println(c);
			} 
		} catch (MalformedURLException e1) {
			System.err.println(website+ " is not a parseable URL");
		}catch (IOException e) {
			e.printStackTrace();
		}  
		//由于使用了AWT，所以必须显示退出
		System.out.println("Over");
		System.exit(0);
	}
}
