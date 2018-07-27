package org.q10viking.seven;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
public class ErrorPage {

	public static void main(String[] args) {
		String website = "http://www.ibiblio.org/gonewalkabout";
		try {
			URL u = new URL(website);
			HttpURLConnection uc = (HttpURLConnection)u.openConnection();
			//请求不存在的页面时，服务器并不是简单的返回404错误码，而是发送另外一个搜索页面
			try(InputStream raw = uc.getInputStream()){
				printFromStream(raw);
			}catch(IOException ex) {
			//	System.err.println("===========注意==============");
				printFromStream(uc.getErrorStream());
			}
			
		} catch (MalformedURLException e) {
			System.out.println(website+" is not a parseable URL");
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	private static void printFromStream(InputStream raw) throws IOException,FileNotFoundException{
		try (InputStream buffer = new BufferedInputStream(raw)){
			PrintStream ps = new PrintStream("sevenErrorPage.html");
			Reader reader =  new InputStreamReader(buffer);
			int c;
			while((c=reader.read()) != -1) {
				System.out.print((char)c);
				ps.print((char)c);
			}
		}
	}

}
