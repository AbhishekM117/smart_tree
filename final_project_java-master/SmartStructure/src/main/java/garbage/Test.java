package garbage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import DataStructure.Constants;

/**
 * @author Jaydev
 *
 */
public class Test {

	public static void main(String[] args) {
		
		String finalCsv = "D:\\\\final_year_project\\\\ml\\\\final.csv";
			try{
			 
			ProcessBuilder pb = new ProcessBuilder("python","E:/coding/test.py");
//			pb.directory(new File("C:\\Users\\jay dev\\Anaconda\\condabin"));
 	    	Process p = Runtime.getRuntime().exec("cmd.exe /C "+Constants.CONDA_INIT+" && python "+Constants.ML_FILEPATH+" "+Constants.CSV_FILEPATH);
//			Process p = pb.start();
			
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String ret = in.readLine();
			System.out.println("value is : "+ret);
			}catch(Exception e){e.printStackTrace();}
			
			
			 

	}

}
