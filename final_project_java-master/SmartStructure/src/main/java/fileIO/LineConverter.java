package fileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * converts lines to tokens
 * @author jay dev
 *
 */
public class LineConverter {
	/**
	 * converts the list of lines to list of tokenized lines
	 * @param lines list of lines
	 * @param delimiter seperator
	 * @return list of tokenized lines
	 */
	public List<String[]> tokenize(List<String> lines,String delimiter){
		List<String[]> tokens = new ArrayList<String[]>();
//		List<T[]> genericToken = new LinkedList<T[]>();
		for(String line : lines) {
			String[] cell = line.split(delimiter);
			tokens.add(cell);
			
		}
		return tokens;
	}
//	@SuppressWarnings("unchecked")
//	public <T> List<T[]> genericTokenize(List<String> lines,String delimiter){
//		T data1 = null;
//		List<T[]> tokens = new ArrayList<T[]>();
//		if(data1 instanceof String) {
//			for(String line : lines) {
//				String[] cell = line.split(delimiter);
//				tokens.add((T[]) cell);
//				
//			}
//		}else if (data1 instanceof Integer) {
//			for(String line : lines) {
//				String[] cell = line.split(delimiter);
//				int[] intCell = Arrays.stream(cell).mapToInt(Integer::parseInt).toArray();
//				Arrays.stream(intCell).
//				tokens.add((T[]) intCell);
//				
//			}
//		}
//		
//		return null;
//	}

}
