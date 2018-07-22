package org.q10viking.five;

import java.io.*;
import java.net.*;
import java.util.*;

public class ProxyTest
{ 
	//HTTPS代理服务器，来自福建，联通 
	final String PROXY_ADDR = "220.249.185.178";
	final int PROXY_PORT = 9797;
	// 定义需要访问的网站地址
	String urlStr = "http://www.cau.edu.cn";
	public void init() 
		throws IOException , MalformedURLException
	{
		URL url = new URL(urlStr);
		// 创建一个代理服务器对象
		Proxy proxy = new Proxy(Proxy.Type.HTTP 
			, new InetSocketAddress(PROXY_ADDR , PROXY_PORT));

		// 使用指定的代理服务器打开连接
		URLConnection conn = url.openConnection(proxy);
		
		// 设置超时时长。
	//	conn.setConnectTimeout(3000);
		try( 
			// 通过代理服务器读取数据的Scanner
			Scanner scan = new Scanner(conn.getInputStream());
			PrintStream ps = new PrintStream("index.html"))
		{
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				// 在控制台输出网页资源内容
				System.out.println(line);
				// 将网页资源内容输出到指定输出流
				ps.println(line);
			}
		}
	}
	public static void main(String[] args)
		throws IOException , MalformedURLException
	{
		new ProxyTest().init();
	}
	
	
}

/*
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
<meta name='Maketime' content='2018-07-21 09:36:46'>*/
