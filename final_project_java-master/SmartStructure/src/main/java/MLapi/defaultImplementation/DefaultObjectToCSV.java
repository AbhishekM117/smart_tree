package MLapi.defaultImplementation;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import DataStructure.Node;
import MLapi.common.ObjectToCSV;

/**
 * @author Jaydev
 *
 */
public class DefaultObjectToCSV<T> implements ObjectToCSV<T> {

	Field[] classFields;
	List<String> csvData = new LinkedList<String>();

	
	@Override
	public void storeFieldData(Node<T> node) throws Exception{
		T data = node.getData();
		List<String> stringFieldValues = new LinkedList<>();
		
		stringFieldValues.add(String.valueOf(data.hashCode()));
		
		for(int i=0;i<classFields.length;i++) {
			Object fieldData = classFields[i].get(data);
			if(fieldData instanceof Number||fieldData instanceof String) {
				stringFieldValues.add(fieldData.toString());
			}
		}
		stringFieldValues.add(String.valueOf(node.getVisits()));
		stringFieldValues.add(String.valueOf(node.getPriority()));
		csvData.add(String.join(",", stringFieldValues));
	}

	@Override
	public void setClassFields(Field[] fields) {
		this.classFields = fields;	
		List<String> header = new LinkedList<>();
		header.add("hashCode");
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			header.add(fields[i].getName());
		}
		header.add("visits");
		header.add("priority");
		csvData.add(String.join(",", header));
	}
	
	public List<String> getCSVData(){
		return csvData;
	}



}
