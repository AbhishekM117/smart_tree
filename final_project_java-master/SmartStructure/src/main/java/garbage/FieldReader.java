package garbage;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FieldReader<T> {
	
	Map<String, List<?>> fieldvalues = new HashMap<String, List<?>>();
	Field[] classFields;
	
	public void stageClass(Class<?> classtype) {
		classFields = classtype.getDeclaredFields();
		for(Field field : classFields) {
			field.setAccessible(true);
			if(field.getType().getName().equals("int")||field.getType().getName().equals("java.lang.Integer")) {
				fieldvalues.put(field.getName(), new LinkedList<Integer>());
			}else if (field.getType().getName().equals("float")||field.getType().getName().equals("java.lang.Float")) {
				fieldvalues.put(field.getName(), new LinkedList<Float>());
			}else if (field.getType().getName().equals("double")||field.getType().getName().equals("java.lang.double")) {
				fieldvalues.put(field.getName(), new LinkedList<Double>());
			}else if (field.getType().getName().equals("java.lang.String")) {
				fieldvalues.put(field.getName(), new LinkedList<String>());
			}else if (field.getType().getName().equals("boolean")||field.getType().getName().equals("java.lang.Boolean")) {
				fieldvalues.put(field.getName(), new LinkedList<Boolean>());
			}else {
				System.err.println("WARNING : "+field.getType().getName()+" is not supported for learning process.... ");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void storeFieldData(T object) throws IllegalArgumentException, IllegalAccessException {
		for(Field field:classFields) {
			if(field.getType().getName().equals("int")||field.getType().getName().equals("java.lang.Integer")) {
				
				List<Integer> integerList = (List<Integer>) fieldvalues.get(field.getName());
				integerList.add(Integer.class.cast(field.get(object)));
				fieldvalues.put(field.getName(), integerList);
			
			}else if (field.getType().getName().equals("float")||field.getType().getName().equals("java.lang.Float")) {
				
				List<Float> floatList = (List<Float>) fieldvalues.get(field.getName());
				floatList.add(Float.class.cast(field.getFloat(object)));
				fieldvalues.put(field.getName(), floatList);
				
			}else if (field.getType().getName().equals("double")||field.getType().getName().equals("java.lang.double")) {
				
				List<Double> doubleList = (List<Double>) fieldvalues.get(field.getName());
				doubleList.add(Double.class.cast(field.getDouble(object)));
				fieldvalues.put(field.getName(), doubleList);
				
			}else if (field.getType().getName().equals("java.lang.String")) {
				
				List<String> stringList = (List<String>) fieldvalues.get(field.getName());
				stringList.add(String.class.cast(field.get(object).toString()));
				fieldvalues.put(field.getName(), stringList);
				
			}else if (field.getType().getName().equals("boolean")||field.getType().getName().equals("java.lang.Boolean")) {
				
				List<Boolean> booleanList = (List<Boolean>) fieldvalues.get(field.getName());
				booleanList.add(field.getBoolean(object));
				fieldvalues.put(field.getName(), booleanList);
			
			}
		}
	}
	
	


}
