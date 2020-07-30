package MLapi.common;

import java.lang.reflect.Field;
import java.util.List;

import DataStructure.Node;

/**
 * @author Jaydev
 *
 */
public interface ObjectToCSV<T> {
	
	public void storeFieldData(Node<T> node) throws Exception;
	public void setClassFields(Field[] fields);
	public List<String> getCSVData();

}
