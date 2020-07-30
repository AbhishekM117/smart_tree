package MLapi.common;

import java.util.List;

import DataStructure.Node;

/**
 * @author Jaydev
 *
 */
public interface CSVToObject<T> {
	
	public void setCSVData(List<String> data);
	public void setPriority(Node<T> node);

}
