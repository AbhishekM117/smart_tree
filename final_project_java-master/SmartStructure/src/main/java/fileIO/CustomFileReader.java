package fileIO;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 * reads the data from file
 * @author jay dev
 *
 */
public class CustomFileReader{

/**
 * reads the list of string(lines) from the file at path
 */
	public List<String> read(String path) {
		List<String> lines = new LinkedList<>();
		try {
			
			BufferedReader br= new BufferedReader(new FileReader(path));
			String st;
			while ((st = br.readLine()) != null) { 
			   lines.add(st);
			} 
			br.close();
		} catch (IOException e) {
            e.printStackTrace();
        } 
		return lines;
		
	}
	/**
	 * reads the list of lines at file
	 * @param file file from which data needs to be fetched
	 * @return the list of lines
	 */
	public List<String> read(File file) {
		List<String> lines = new LinkedList<>();
		try {
			BufferedReader br= new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) { 
			   lines.add(st);
			}
			br.close();
		} catch (IOException e) {
            e.printStackTrace();
        } 
		return lines;
		
	}

}
