package org.q10viking.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class DigestThread extends Thread{
	private String fileName;
	
	public DigestThread(String fileName) {
		this.fileName = fileName;
	}
	
	@Override 
	public void run() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in,sha);
			
			while(din.read() != -1);
			din.close();
			byte[] digest = sha.digest();
			
			/*
			StringBuilder result = new StringBuilder(fileName);
			result.append(":");
			
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
			*/
			synchronized(System.out) {
				System.out.print(fileName+": ");
				System.out.print(DatatypeConverter.printHexBinary(digest));
				System.out.println();
			}
		}catch(IOException ex) {
			System.err.println(ex);
		}catch(NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}
	
	
	public static void main(String[] args) {
/*		String fileName = "myinputfile.txt";
		Thread t = new DigestThread(fileName);
		t.start();*/
		
		String[] filenames= {"One.txt","Two.txt","Three.txt","Four.txt"};
		for(String name: filenames) {
			Thread t = new DigestThread(name);
			t.start();
		}
	}

}
