package org.q10viking.four;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWebLog {
	private final static int NUM_THREADS = 4;
	private final static String WBN = "weblog.txt";
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		
		try(FileInputStream fin = new FileInputStream(WBN);
				Reader in = new InputStreamReader(fin,"UTF-8");
				BufferedReader bin = new BufferedReader(in);
				){
			
			for(String entry=bin.readLine();entry != null;entry=bin.readLine()) {
				LookupTask task = new LookupTask(entry);
				Future<String> future = executor.submit(task);
				LogEntry result = new LogEntry(entry,future);
				results.add(result);
			}
			
		}  
		 
		//打印结果，每次结果未准备就绪时就会阻塞
		for(LogEntry result: results) {
			try {
				System.out.println(result.future.get());
			}catch(InterruptedException | ExecutionException ex) {
				System.out.println(result.original);
			}
		}
	
	}
	
	private static class LogEntry{
		String original;
		Future<String> future;
		
		LogEntry(String original,Future<String> future){
			this.original = original;
			this.future = future;
		}
	}

}

/*www.ibiblio.org - - [07/Mar/2018:16:05:49 -0800] "GET /mailman/listinfo/hsdivision HTTP/1.1" 200 6291
*/