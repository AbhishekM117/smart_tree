package garbage;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import test.Student;

public class Main4 {
	


	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		FieldReader<Student> reader = new FieldReader<Student>();
		reader.stageClass(Student.class);
		System.out.println();
		Integer inte = new Integer(10);
		Student s= new Student(0, "a", 100);
		for(int i=0;i<10;i++) {
			reader.storeFieldData(new Student(10, "jaydev", 100));
		}
		reader.storeFieldData(s);
		System.err.println();

	}

}
