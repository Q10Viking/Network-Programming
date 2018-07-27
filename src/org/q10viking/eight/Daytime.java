package org.q10viking.eight;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daytime {

	public Date getDateFromNetWork() throws IOException, ParseException {
		try(Socket socket = new Socket("time.nist.gov",13)){
			socket.setSoTimeout(15000);
			InputStream in =  socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in,"ASCII");
			StringBuilder time = new StringBuilder();
			int c;
			while((c = reader.read()) != -1) {
				time.append((char)c);
			}
			return parseDate(time.toString());
		}

	}
	
	static Date parseDate(String s) throws ParseException {
		String[] pieces = s.split(" ");
		String dateTime = pieces[1]+" "+pieces[2]+" UTC";
		System.out.println(dateTime);
		DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
		
		return format.parse(dateTime);
	}
	
	
	public static void main(String[] args) throws IOException, ParseException {
		Daytime time = new Daytime();
		Date date = time.getDateFromNetWork();
		System.out.println(date);
		
	}
}

/*
Fri Jul 27 23:19:16 CST 2018

*/