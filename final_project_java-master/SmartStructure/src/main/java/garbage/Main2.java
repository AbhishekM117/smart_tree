package garbage;

import java.util.ArrayList;
import java.util.LinkedList;

import test.Student;

public class Main2 {

	public static void main(String[] args) throws Exception {
		
		LinkedList<Student> studentList = new LinkedList<>();
		studentList.add(new Student(10,"jay",100));
		
		ArrayList<Student> studentsArray = new ArrayList<>();
		studentsArray.add(new Student(10,"jay",100));
	}

}
