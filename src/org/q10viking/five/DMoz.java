package org.q10viking.five;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

//用百度搜索引擎进行搜索
public class DMoz {

	public static void main(String[] args) {
		String target="";
		//要搜索的内容
		String[] arrStr = {"tomcat","eclipse","配置"};  
		for(int i=0;i<arrStr.length;i++) {
			target += arrStr[i]+" ";
		}
		target = target.trim();
		System.out.println(target);
		QueryString query = new QueryString();
		query.add("wd", target);
		System.out.println(query);
		try {
			URL url = new URL("http://www.baidu.com/s?wd"+query);
			try(InputStream in = new BufferedInputStream(url.openStream())){
				InputStreamReader theHTML = new InputStreamReader(in);
				PrintStream ps = new PrintStream("cauCIEE.html");
				int c;
				while((c=theHTML.read()) != -1) {
					System.out.print((char)c);
					ps.print((char)c);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
