package garbage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import test.Student;

/**
 * @author Jaydev
 *
 */
public class PathMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		String currentDirectory = System.getProperty("user.dir");
//		String fileName = "test.csv";
//		File csvfile = new File(currentDirectory+"\\"+fileName);
//		csvfile.createNewFile();
//		Arrays.asList(new File(currentDirectory).list()).forEach(System.out::println);
//		System.out.println();
		Student s = null;
		System.out.println(s.getClass());
	}

}
