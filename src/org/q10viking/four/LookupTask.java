package org.q10viking.four;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String> {

	private String line;
	public LookupTask(String line) {
		this.line = line;
	}
	@Override
	public String call() throws Exception {
		try {
			int index = line.indexOf(' ');
			String ip = line.substring(0,index);
			String theRest = line.substring(index);
			String hostname = InetAddress.getByName(ip).getHostName();
			return hostname+theRest;
		}catch(UnknownHostException ex) {
			return line;
		}
	}
}
