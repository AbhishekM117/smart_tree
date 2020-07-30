package MLapi.common;

import DataStructure.SmartTree;

/**
 * @author Jaydev
 *
 */
public interface Manager<T extends Comparable<T>> extends Runnable {
	
	public void initializeLearning(SmartTree<T> tree,Class<?> class1)throws Exception;
	
	public void learn(SmartTree<T> tree) throws Exception;
	
	public void endLearning();	
	
	

}
