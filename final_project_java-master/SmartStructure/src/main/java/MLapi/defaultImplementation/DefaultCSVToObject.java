package MLapi.defaultImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DataStructure.Node;
import MLapi.common.CSVToObject;
import fileIO.LineConverter;

/**
 * @author Jaydev
 *
 */
public class DefaultCSVToObject<T> implements CSVToObject<T>{
	
	Map<Integer, Double> priorityMap = new HashMap<Integer, Double>();

	@Override
	public void setCSVData(List<String> data) {
		LineConverter converter = new LineConverter();
		List<String[]> tokens = converter.tokenize(data, ",");
		priorityMap = tokens.stream().skip(1).collect(Collectors.toMap(val->Integer.parseInt(val[0]), val->Double.parseDouble(val[val.length-1]))); 
		
	}

	@Override
	public void setPriority(Node<T> node) {
		
		if(priorityMap.containsKey(node.getData().hashCode())) {
			node.setPriority(priorityMap.get(node.getData().hashCode()));
		}else {
			System.err.println("could not find priority for "+node.getData().hashCode());
		}
		
	}

}
