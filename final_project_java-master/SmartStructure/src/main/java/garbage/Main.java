package garbage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DataStructure.SmartTree;
import test.Student;

public class Main {

	public static void main(String[] args) throws Exception {
		Student jay = new Student(50,"jay",100);
		Student dev = new Student(25,"dev",99);
		Student rai = new Student(75,"rai",98);
		Student sahil = new Student(43,"sahil",75);
		Student varma = new Student(98,"verma",66);
		Student nafees = new Student(30,"nafees",88);
		Student ansari = new Student(52,"ansari",50);
		Student lengdo = new Student(66,"lengdo",45);
		
		Map<Integer, Student> map = new HashMap<>();
		SmartTree<Student> smart = new SmartTree<>();
		for(int i=0;i<10000;i++) {
			int id = (int) (Math.random()*10000);
			Student newObject = new Student(id,"abc",i);
			map.put(id, newObject);
			smart.insert(newObject);
		}
//		map.put(50, jay);
//		map.put(25, dev);
//		map.put(75, rai);
//		map.put(45, sahil);
//		map.put(98, varma);
//		map.put(30, nafees);
//		map.put(52, ansari);
//		map.put(66, lengdo);
		
//		smart.insert(jay);
//		smart.insert(dev);
//		smart.insert(rai);
//		smart.insert(sahil);
//		smart.insert(varma);
//		smart.insert(nafees);
//		smart.insert(ansari);
//		smart.insert(lengdo);
		
		
		long start = System.nanoTime();
		Student newdata = smart.search(new Student(500));
		long end = System.nanoTime();
		System.out.println(newdata.getMarks()+" smart - "+(end-start));
		long start1 = System.nanoTime();
		Student newData2 = map.get(500);
		long end1 = System.nanoTime();
		System.out.println(newData2.getMarks()+" map - "+(end1-start1));
	}

}
