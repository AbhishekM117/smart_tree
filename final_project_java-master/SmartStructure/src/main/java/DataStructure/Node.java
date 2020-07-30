package DataStructure;

public class Node<T> {
	
	T data;
	Node<T> left;
	Node<T> right;
	Node<T> parent;
	int visits;
	double priority;
	
	
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}

	/**
	 * @return the parent
	 */
	public Node<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	/**
	 * @return the visits
	 */
	public int getVisits() {
		return visits;
	}

	/**
	 * @param visits the visits to set
	 */
	public void setVisits(int visits) {
		this.visits = visits;
	}

	/**
	 * @return the priority
	 */
	public double getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(double priority) {
		this.priority = priority;
	}

	public Node(T data, Node<T> parent) {
		this.data = data;
		this.parent = parent;
		this.left = null;
		this.right = null;
		this.visits = 0;
		this.priority = 0;
	}
	
	public boolean hasLeft() {
		return left!=null;
	}
	public boolean hasRight() {
		return right!=null;
	}
	public boolean hasParent() {
		return parent!=null;
	}
	public boolean isLeaf() {
		return !hasLeft()&&!hasRight();
	}
	public boolean isRoot() {
		return !hasParent();
	}
	public boolean isInternal() {
		return (hasLeft()||hasRight())&&hasParent();
	}
	public Position getParentPosition() {
		if(parent.getLeft()==this) {
			return Position.RIGHT;
		}else if (parent.getRight()==this) {
			return Position.LEFT;
		}else {
			return Position.NULL;
		}
	}
	
	
	
	
}
