package org.q10viking.seven;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class BinarySaver {

	public static void main(String[] args) {
		try {
			URL root = new URL("https://www.oreilly.com/favicon.ico");
		//	System.out.println(root.getFile());
			saveBinaryFile(root);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void saveBinaryFile(URL u) throws IOException {
	
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			//根据长度来读取相应的二进制文件字节数
			int contentLength = uc.getContentLength();
			
			if(contentType.startsWith("text/") || contentLength == -1) {
				throw new IOException("This is not a binary file");
			}
			
			try(InputStream raw = uc.getInputStream()){
				InputStream in = new BufferedInputStream(raw);
				byte[] data = new byte[contentLength];
				int offset= 0;
				while(offset<contentLength) {
					int bytesRead = in.read(data,offset,data.length-offset);
					if(bytesRead == -1) break;
					offset += bytesRead;
				}
				
				if(offset != contentLength) {
					throw new IOException("Only read "+ offset+" bytes;Expected "+contentLength+ " bytes");
				}
				
				String fileName = u.getFile();
				fileName = fileName.substring(fileName.lastIndexOf('/')+1);
				try(FileOutputStream fout = new FileOutputStream(fileName)){
					fout.write(data);
					fout.flush();
				}
			}
			

	}

}
