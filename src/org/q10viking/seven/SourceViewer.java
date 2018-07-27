package org.q10viking.seven;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//包含响应码和消息的SourceViewer
public class SourceViewer {

	public static void main(String[] args) {
		try {
			URL u = new URL("https://www.cau.edu.cn");
			HttpURLConnection uc = (HttpURLConnection)u.openConnection();
			int code = uc.getResponseCode();
			String response = uc.getResponseMessage();
			//请求行
			
			System.out.println("HTTP/1.x "+code+" "+response);
			//请求体
			for(int i=1;;i++) {
				String header = uc.getHeaderField(i);
				String key = uc.getHeaderFieldKey(i);
				if(header == null || key == null) break;
				System.out.println(key+": "+header);
			}
			System.out.println();
			//请求的内容
			
			try(InputStream in = new BufferedInputStream(uc.getInputStream())){
				Reader r = new InputStreamReader(in);
				int c;
				while((c = r.read()) != -1) {
					System.out.print((char)c);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}


/*
HTTP/1.x 200 OK
Date: Fri, 27 Jul 2018 09:18:05 GMT
Server: Apache/2.4.33 (Unix)
Last-Modified: Fri, 27 Jul 2018 09:09:06 GMT
ETag: "6b69-571f776b53080"
Accept-Ranges: bytes
Content-Length: 27497
Vary: Accept-Encoding
Keep-Alive: timeout=15
Connection: Keep-Alive
Content-Type: text/html

<!DOCTYPE HTML>

<html lang="zh-cn">

<head>

	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"><script language="javascript" src="/module/jslib/jquery/jquery.js"></script>
<script language="javascript" src="/module/jslib/urite/urite.min.js"></script>
<link href="/module/jslib/tag/css/infotag.css" type=text/css rel=stylesheet>
<meta name='WebId' content='1'>


	<title>中国农业大学</title>
<meta name="Keywords" content="中国农业大学、农大、北京农业大学、国家211工程、985工程">
<meta name="Titles" content="开放的中国农业大学欢迎您！">
<meta name="Descriptions" content="中国农业大学是国家“211工程”和“985工程”重点建设的教育部直属高校。">
... ...
*/