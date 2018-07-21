package org.q10viking.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReadWriteDemo {

	public static void main(String[] args) throws IOException{
		FileReadWriteDemo fileReadWriteDemo = new FileReadWriteDemo();
		fileReadWriteDemo.readAndWriteFile();
	}
	private void readAndWriteFile() throws IOException{
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			String inputFileName = "myinputfile.txt";
			String outputFileName = "myoutputfile.txt";
			
			fileInputStream = new FileInputStream(inputFileName);
			fileOutputStream = new FileOutputStream(outputFileName);
			
			int i;
			
			while((i = fileInputStream.read()) != -1) {
				fileOutputStream.write(i);
			}
			System.out.println("Successfully read and"
								+" write to the \'"+outputFileName
								+ "\'file");
		}finally {
			if(fileInputStream != null) {
				fileInputStream.close();
			}if(fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}
}
