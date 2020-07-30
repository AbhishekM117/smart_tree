package MLapi.defaultImplementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import MLapi.common.CSVFileIO;
import fileIO.CustomFileReader;
import fileIO.CustomFileWriter;

/**
 * @author Jaydev
 *
 */
public class DefaultCSVFileIO implements CSVFileIO {
	
	File csvFile;
	@Override
	public boolean createCSVFile(String objectID) throws IOException {
		String currentDirectory = System.getProperty("user.dir");
		csvFile = new File(currentDirectory+"\\"+objectID+".csv");
		return csvFile.createNewFile();
		
	}
	@Override
	public String getCSVFilePath() throws IOException {
		return csvFile.getCanonicalPath();
	}
	
	public boolean deleteCSVFile() {
		return csvFile.delete();
	}

	@Override
	public List<String> readFromCSVFile() {
		CustomFileReader fileReader = new CustomFileReader();
		List<String> lines =  fileReader.read(csvFile);
		return lines;
	}
	@Override
	public void writeToCSVFile(List<String> data) {
		CustomFileWriter fileWriter = new CustomFileWriter();
		fileWriter.write(data, csvFile, false);
	}
		

}
