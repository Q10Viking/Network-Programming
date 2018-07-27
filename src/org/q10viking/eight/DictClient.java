package org.q10viking.eight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

//基于网络Socket的英语-拉丁翻译程序
public class DictClient {
	public static final String SERVER = "dict.org";
	public static final int PORT=2628;
	public static final int TIMEOUT=15000;
	
	public static void main(String[] args) {
		Socket socket = null;
		String[] words = {"I","love","you"};
		try {
			socket = new Socket(SERVER,PORT);
			socket.setSoTimeout(TIMEOUT);
			
			OutputStream out = socket.getOutputStream();
			Writer writer = new OutputStreamWriter(out,"UTF-8");
			
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in,"UTF-8"));
			
			for(String word: words) {
				define(word,writer,reader);
			}
			define("gold",writer,reader);
			writer.write("quit\r\n");
			writer.flush();
		
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
/*	
	define fd-eng-lat gold----------->这是命令，下面是结果
	150 1 definitions retrieved
	151 "gold" fd-eng-lat "English-Latin FreeDict Dictionary ver. 0.1.1"
	gold /gould/
	 1. aurarius; aureus; chryseus
	 2. aurum; chrysos
	.
	250 ok [d/m/c = 1/0/10; 0.000r 0.000u 0.000s]

*/
	//处理以上字符信息
	static void define(String word,Writer writer,BufferedReader reader) throws IOException {
		writer.write("define fd-eng-lat "+word+"\r\n");
		writer.flush();
		for(String line=reader.readLine();line != null;line=reader.readLine()) {
			if(line.startsWith("250 ")) { //ok
				return;
			}else if(line.startsWith("552 ")) {//no match
				System.out.println("No definition found for "+word);
				return;
			}else if(line.matches("\\d\\d\\d .*")) {
				continue;
			}else if(line.trim().equals(".")) {
				continue;
			}else {
				System.out.println(line);
			}
		}
	}
}



/*
No definition found for I
love /lʌv/
 1. amare
 2. agape; amor; caritas
 3. ætaluta; amor
 4. caritas
you /jau/
 1. tibi
 2. tibi
 3. tibi
 4. tibi
gold /gould/
 1. aurarius; aureus; chryseus
 2. aurum; chrysos
*/







