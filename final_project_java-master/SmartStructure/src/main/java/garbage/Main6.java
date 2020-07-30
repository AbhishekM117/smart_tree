package garbage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jaydev
 *
 */
public class Main6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] s = new String[]{"1","2","3"} ;
		String[] s1 = new String[]{"4","5","6"} ;
		String[] s2 = new String[] {"8","9","10"};
		List<String[]> yo = Arrays.asList(s,s1,s2);
		
		Map<Integer, Integer> map = yo.stream().collect(Collectors.toMap(val->Integer.parseInt(val[0]), val->Integer.parseInt(val[val.length-1])));
		System.out.println();
	}

}
