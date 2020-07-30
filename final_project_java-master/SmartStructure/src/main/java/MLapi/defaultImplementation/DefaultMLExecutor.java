package MLapi.defaultImplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import DataStructure.Constants;
import DataStructure.SmartBSTException;
import MLapi.common.MLExecutor;


/**
 * @author Jaydev
 *
 */
public class DefaultMLExecutor implements MLExecutor {
	
	private void validate(String mlFilePath,String csvFilePath) throws SmartBSTException {
		if(mlFilePath==null) {
			throw new SmartBSTException("mlFilePath cannot be null");
		}
		if (csvFilePath==null) {
			throw new SmartBSTException("csvFilePath cannot be null");
			
		}
	}

	@Override
	public void execute(String mlFilePath,String csvFilePath) throws Exception{
		
		validate(mlFilePath,csvFilePath);
		String command = buildCommand(mlFilePath,csvFilePath);
		
		Process process = Runtime.getRuntime().exec("cmd.exe /C conda activate jaydev && python "+Constants.ML_FILEPATH+" "+Constants.ML_ARGUMENT);
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		System.out.println(reader.readLine());
		
	}
	
	private String buildCommand(String mlFilePath,String csvFilePath) {
		return Constants.CONDA_INIT+" && python "+mlFilePath+" "+csvFilePath;
	}

	

}
