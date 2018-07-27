package org.q10viking.seven;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//提交一个表单
public class FormPoster {
	
	private URL url;
	private QueryString query = new QueryString();
	public FormPoster(URL url) {
		if(!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException("Posting only works for http URLs");
		}
		this.url = url;
	}
	
	public void add(String name,String value) {
		query.add(name, value);
	}
	public URL getURL() {
		return url;
	}
	
	public InputStream post() throws IOException{
		URLConnection uc = url.openConnection();
		uc.setDoOutput(true);
		try(OutputStreamWriter out
				= new OutputStreamWriter(uc.getOutputStream(),"UTF-8")){
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		}
		//返回响应
		return uc.getInputStream();
		
	}
	
	public static void main(String[] args) {
		URL u = null;
		try {
			u = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		FormPoster poster = new FormPoster(u);
		poster.add("name", "Q10Viking");
		poster.add("email", "1403090523@cau.edu.cn");
		
		try(InputStream in = poster.post()){
			Reader r = new InputStreamReader(in);
			int c;
			PrintStream ps = new PrintStream("postTest.html");
			
			while((c = r.read()) != -1) {
				System.out.print((char)c);
				ps.print((char)c);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
