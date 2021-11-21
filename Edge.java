
/**
 * This class represents an edge of the graph.
 * @author jy
 *
 */
public class Edge {
	private Node firstEndpoint;
	private Node secondEndpoint;
	private int type;
	
	/**
	 * The constructor for the class
	 * @param u Node representing the first endpoint
	 * @param v Node representing the second endpoint
	 * @param type the Type of the edge
	 */
	public Edge(Node u, Node v, int type) {
		firstEndpoint = u;
		secondEndpoint = v;
		this.type = type;
				
	}
	
	/**
	 * This method returns the first endpoint of the edge.
	 * @return first endpoint of the edge.
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
		
	}
	
	/**
	 * This method returns the second endpoint of the edge.
	 * @return second endpoint of the edge.
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
		
	}
	
	/**
	 * This method returns the type of the edge.
	 * @return type of the edge
	 */
	public int getType() {
		return type;
		
	}

}
