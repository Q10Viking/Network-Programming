package org.q10viking.five;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL组成部分
 */
public class URLSplitter {

	public static void main(String[] args) {
		String website="https://music.163.com/#/user/home?id=260142383";
		
		try {
			URL u = new URL(website);
			
			System.out.println("The URL is: "+u);
			System.out.println("The scheme is: "+u.getProtocol());
			System.out.println("The user info is: "+u.getUserInfo());
			
			System.out.println("The host is: "+u.getHost());
			System.out.println("The port is: "+u.getPort());
			System.out.println("The default port is: "+u.getDefaultPort());
			
			System.out.println("The path is: "+u.getPath());
			System.out.println("The ref is: "+u.getRef());
			System.out.println("The query string is: "+u.getQuery());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}

/*The URL is: https://music.163.com/#/user/home?id=260142383
The scheme is: https
The user info is: null
The host is: music.163.com
The port is: -1
The default port is: 443
The path is: /
The ref is: /user/home?id=260142383
The query string is: null

*/
