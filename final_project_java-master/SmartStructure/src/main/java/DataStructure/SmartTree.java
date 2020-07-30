package DataStructure;

import java.util.Collections;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import MLapi.common.Manager;
import MLapi.defaultImplementation.DefaultManager;

public class SmartTree<T extends Comparable<T>> {
	
	Node<T> rootNode;
	boolean learning;
	List<Node<T>> leafNodes=Collections.synchronizedList(new LinkedList<>());
	ExecutorService learnerPool =  Executors.newSingleThreadExecutor();
	Manager<T> learningManager;
	
	/**
	 * 
	 */
	public SmartTree() {
		learningManager = new DefaultManager<>(this);
	}
	public SmartTree(long learningInterval) {
		learningManager = new DefaultManager<>(this,learningInterval);
	}
	
	public void insert(T data) {
		synchronized (this) {
			if(this.rootNode==null) {
				this.rootNode = new Node<T>(data, null);
				try {
					learningManager.initializeLearning(this, data.getClass());
				} catch (Exception e) {
					e.printStackTrace();
				}
				learnerPool.submit(learningManager);
			}else {
				Node<T> current = search(data, rootNode);
				try {
					insert(data,current);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public T search(T data) {
		
		synchronized (this) {
			Node<T> foundNode =  search(data, this.rootNode);
			if (foundNode.getData().compareTo(data)==0) {
				foundNode.setVisits(foundNode.getVisits()+1);
				return foundNode.getData();
			}else {
				return null;
			}
		}
	}
		
	private void insert(T data,Node<T> node) throws Exception {
		if(data.compareTo(node.getData())==-1) {
			node.setLeft(new Node<T>(data, node));
		}else if(data.compareTo(node.getData())==1) {
			node.setRight(new Node<T>(data, node));
		}else {
			throw new SmartBSTException("cannot insert an object with same field value used in compareTo");
		}
		
			
	}
	
	private Node<T> search(T data,Node<T> currentNode){
		if(data.compareTo(currentNode.getData())==0) {
			return currentNode;
		}else if (data.compareTo(currentNode.getData())==-1) {
			if(currentNode.getLeft()!=null) {
				return search(data, currentNode.getLeft());
			}
			return currentNode;
				
		}else {
			if(currentNode.getRight()!=null) {
				return search(data, currentNode.getRight());
			}
			return currentNode;
			
		}
	}
	
	public void leftRotate(Node<T> node) {
		if(node==rootNode) {
			return;
		}else {
			Node<T> parent = node.getParent();
			if(parent==rootNode) {
				parent.setRight(node.getLeft());
				if(node.hasLeft()) {
					node.getLeft().setParent(parent);
				}
				node.setLeft(parent);
				parent.setParent(node);
				rootNode = node;
			}else if (parent.isInternal()) {
				Node<T> superParent = parent.getParent();
				parent.setRight(node.getLeft());
				if(node.hasLeft()) {
					node.getLeft().setParent(parent);
				}
				node.setLeft(parent);
				parent.setParent(node);
				if(superParent.getRight()==parent) {
					superParent.setRight(node);
					node.setParent(superParent);
				}else if(superParent.getLeft()==parent) {
					superParent.setLeft(node);
					node.setParent(superParent);
				}
			}
		}
	}
	
	public void rightRotate(Node<T> node) {
		if(node==rootNode) {
			return;
		}else {
			Node<T> parent = node.getParent();
			if(parent==rootNode) {
				parent.setLeft(node.getRight());
				if(node.hasRight()) {
					node.getRight().setParent(parent);
				}
				node.setRight(parent);
				parent.setParent(node);
				rootNode = node;
			}else if (parent.isInternal()) {
				Node<T> superParent = parent.getParent();
				parent.setLeft(node.getRight());
				if(node.hasRight()) {
					node.getRight().setParent(parent);
				}
				node.setRight(parent);
				parent.setParent(node);
				if(superParent.getRight()==parent) {
					superParent.setRight(node);
					node.setParent(superParent);
				}else if(superParent.getLeft()==parent) {
					superParent.setLeft(node);
					node.setParent(superParent);
				}
			}
		}
	}

	public void rotate(Node<T> node) {
		while (node!=rootNode&&node.getPriority()>node.getParent().getPriority()) {
			if(node.getParentPosition()==Position.LEFT) {
				leftRotate(node);
			}else if (node.getParentPosition()==Position.RIGHT) {
				rightRotate(node);
			}
		}
	}
	
	public void getLeafNodes(Node<T> currentNode){
		if(currentNode==null) {
			return;
		}else if(currentNode.isLeaf()) {
			leafNodes.add(currentNode);
		}else {
			getLeafNodes(currentNode.getLeft());
			getLeafNodes(currentNode.getRight());
		}
	}
	
	public void restructure() throws InterruptedException {
		getLeafNodes(rootNode);
		
		synchronized (this) {
			for(Node<T> node : leafNodes) {
				rotate(node);
			}
		}
	}
	
	public void performOperations(Consumer<Node<T>> action) {
		performOperations(action,this.rootNode);
	}
	
	private void performOperations(Consumer<Node<T>> action,Node<T> currentNode) {
		if(currentNode!=null) {
			action.accept(currentNode);
			performOperations(action,currentNode.getLeft());
			performOperations(action,currentNode.getRight());
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.err.println("bst finilized called");
		learnerPool.shutdownNow();
	}
	
	public void close() {
		learnerPool.shutdownNow();
	}
	
}
