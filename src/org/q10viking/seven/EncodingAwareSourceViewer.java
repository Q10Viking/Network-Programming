package org.q10viking.seven;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//用正确的字符集下载一个web页面
public class EncodingAwareSourceViewer {

	public static void main(String[] args) {
		String website = "https://music.163.com/#/user/home?id=260142383";
		//设置默认的编码方式
		String encoding = "ISO-8859-1";
		
		try {
			URL u = new URL(website);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			System.out.println(contentType);
			int encodingStart = contentType.indexOf("charset=");
			System.out.println(encodingStart);
			System.out.println(encoding);
			if(encodingStart != -1) {
				encoding = contentType.substring(encodingStart+8);
			}
			System.out.println(encoding);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*text/html;charset=utf8
10
ISO-8859-1
utf8
*/