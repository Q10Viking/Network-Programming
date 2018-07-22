package org.q10viking.five;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProxySelectorTest {

	//代理服务器的地址和端口
	final String PROXY_ADDR = "220.249.185.178";
	final int PROXY_PORT = 3124;
	//定义需要访问的网站
	String urlStr = "http://www.cau.edu.cn";
	
	public void init() throws IOException {
		ProxySelector.setDefault(new ProxySelector() {

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				System.out.println("无法连接到指定代理服务器!");
			}

			@Override
			public List<Proxy> select(URI uri) {
				//程序总是返回特定的对应的代理服务器
				List<Proxy> result = new ArrayList<>();
				result.add(new Proxy(Proxy.Type.HTTP,
						new InetSocketAddress(PROXY_ADDR,PROXY_PORT)));
				return result;
			}
			
		});
		
		URL url = new URL(urlStr);
		// 没有指定代理服务器、直接打开连接
		URLConnection conn = url.openConnection();
		//设置时长
		//conn.setConnectTimeout(30000);
		try(
				Scanner scan = new Scanner(conn.getInputStream());
				PrintStream ps = new PrintStream("index2.html")){
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				// 在控制台输出网页资源内容
				System.out.println(line);
				// 将网页资源内容输出到指定输出流
				ps.println(line);
						
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		new ProxySelectorTest().init();
	}

}

/*
无法连接到指定代理服务器!
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
<meta name='Maketime' content='2018-07-21 09:36:46'>

*/
