package MLapi.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jaydev
 *
 */
public interface CSVFileIO {
	
	
	public boolean createCSVFile(String objectID) throws Exception;
	public List<String> readFromCSVFile();
	public void writeToCSVFile(List<String> data);
	public boolean deleteCSVFile();
	public String getCSVFilePath() throws Exception ;

}
