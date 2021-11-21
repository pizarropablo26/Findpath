
/**
 * This class represent a node of the graph.
 * @author jy
 *
 */
public class Node {
	
//	private Node newnode;
	private int Name;
	private boolean mark;

	/**
	 * This is the constructor for the class that creates a node 
	 * with the given name 
	 * @param name a integer value representing the number of nodes in the graph
	 */
	public Node(int Name) {
		this.Name = Name;
//		newnode = new Node(this.Name);
	}
	
	/**
	 * This method marks the node with the specified value, either true or false
	 * @param mark the value that the node is been marked with
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	/**
	 * This method  returns the value with which the node has been marked.
	 * @return mark 
	 */
	public boolean getMark() {
		return mark;
		
	}
	
	/**
	 * This method returns the name of the vertex.
	 * @return name of vertex
	 */
	public int getName() {
		return Name;
		
	}

}
