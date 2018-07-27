package org.q10viking.seven;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//显示整个HTTP首部
public class AllHeaders {
	final static String website = "https://www.cau.edu.cn";
	public static void main(String[] args) {
		try {
			URL u = new URL(website);
			URLConnection uc = u.openConnection();
			for(int i=1;;i++) {  //0 是首部字段
				String header = uc.getHeaderField(i);
				if(header == null) break;
				System.out.println(uc.getHeaderFieldKey(i)+": "+header);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

/*Date: Fri, 27 Jul 2018 07:11:38 GMT
Server: Apache/2.4.33 (Unix)
Last-Modified: Thu, 26 Jul 2018 04:01:56 GMT
ETag: "6b93-571df0e5a5500"
Accept-Ranges: bytes
Content-Length: 27539
Vary: Accept-Encoding
Keep-Alive: timeout=15
Connection: Keep-Alive
Content-Type: text/html
*/
