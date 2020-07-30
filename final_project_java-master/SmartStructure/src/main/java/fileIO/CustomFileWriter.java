package fileIO;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.List;
/**
 * writes data into file
 * @author jay dev
 *
 */
public class CustomFileWriter{
	 
	/**
	 * writes list of string to file in filepath
	 */
	public void write(List<String> data,String filePath,boolean append) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filePath,append);
			for(String singleData : data) {
				fileWriter.write(singleData+"\n");
			}
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void write(List<String> data,File file,boolean append) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file,append);
			for(String singleData : data) {
				fileWriter.write(singleData+"\n");
			}
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	 

}
