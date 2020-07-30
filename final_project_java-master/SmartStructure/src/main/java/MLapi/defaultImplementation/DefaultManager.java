package MLapi.defaultImplementation;

import java.util.List;

import DataStructure.Constants;
import DataStructure.SmartTree;
import MLapi.common.CSVFileIO;
import MLapi.common.CSVToObject;
import MLapi.common.MLExecutor;
import MLapi.common.Manager;
import MLapi.common.ObjectToCSV;
import test.TestCsvFileIO;

/**
 * @author Jaydev
 *
 */
public class DefaultManager<T extends Comparable<T>> implements Manager<T>{
	
	CSVFileIO csvFileIO = new TestCsvFileIO();
	ObjectToCSV<T> objectToCSVString = new DefaultObjectToCSV<>();
	CSVToObject<T> csvStringToObject = new DefaultCSVToObject<>();
	MLExecutor mlExecutor = new DefaultMLExecutor();
	long intervalTime = Constants.DEFAULT_TIMEINTERVAL;
	SmartTree<T> smartBST;
	
	public void initializeLearning(SmartTree<T> tree,Class<?> class1) throws Exception {
		csvFileIO.createCSVFile(String.valueOf(tree.hashCode()));
		objectToCSVString.setClassFields(class1.getDeclaredFields());
	}
	
	public DefaultManager(SmartTree<T> smartBST) {
		this.smartBST = smartBST;
	}
	
	public DefaultManager(SmartTree<T> smartBST, long intervalTime) {
		this.smartBST = smartBST;
		this.intervalTime = intervalTime;
	}

	@Override
	public void learn(SmartTree<T> tree) throws Exception {
		// write data to csv
		tree.performOperations(t -> {
			try {
				objectToCSVString.storeFieldData(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});	
		List<String> csvData = objectToCSVString.getCSVData();
		csvFileIO.writeToCSVFile(csvData);
		System.out.println("written to csv file");
		
		// execute machine learning
		mlExecutor.execute(Constants.ML_FILEPATH, csvFileIO.getCSVFilePath());
		System.out.println("learning comleted");
		//update prioirty
		List<String> newCSVData = csvFileIO.readFromCSVFile();
		csvStringToObject.setCSVData(newCSVData);
		tree.performOperations(csvStringToObject::setPriority);
		System.out.println("restructuring");
		//restructuring
		tree.restructure();
		System.out.println("restructured");
	}
	
	@Override
	public void endLearning() {
		csvFileIO.deleteCSVFile();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println("started learning");
			while (true) {
				learn(smartBST);
				Thread.sleep(intervalTime);
			}
		}catch (InterruptedException e) {
			System.err.println("inturrupted learning");
			endLearning();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.err.println("learning finallized called");
		endLearning();
	}
	


}
