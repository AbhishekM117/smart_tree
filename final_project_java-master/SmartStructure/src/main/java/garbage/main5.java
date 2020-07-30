package garbage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DataStructure.SmartBSTException;
import test.Student;

/**
 * @author Jaydev
 *
 */
public class main5 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		Student student = new Student(0, null, 0);
		Student s = student;
		System.out.println(student.hashCode()+"---"+s.hashCode());
//		Field[] field = Student.class.getFields();
//		for(Field field2 : field) {
//			System.out.println(field2.getType().newInstance() instanceof Integer);
//		}
//		ArrayList<String> al = new ArrayList<>();
//		Integer a= 10;
//		int b = Integer.class.cast(a);
//		System.out.println(b);
//		File file = new File("");
//		try {
//			System.out.println(file.getCanonicalPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		ArrayList yo = new ArrayList();
//		LinkedList ge = new LinkedList();
//		yo.add( 1);
//		yo.add("dev");
//		yo.add(4.2);
//		yo.forEach(main5::ya);
		
	}
	static void ya(Object o) {
		System.out.println(o.toString());
	}

}
