import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph implements GraphADT {
	private Node newNode;
//	private Graph newGraph;
	private LinkedList<Node> newnodelist;
//	private LinkedList<Edge>[][] array; 
	private Edge[][] edgeArray; 
	
	/**
	 * This is the constructor for the class that creates a graph with n nodes and no edges. 
	 * @param n number of nodes
	 */
	public Graph(int n) {
		newnodelist = new LinkedList<Node>();
		edgeArray = new Edge[n][n];
		for (int i = 0; i<n; i++) {
			newNode = new Node(i);
			newnodelist.add(newNode);
		}
//		newGraph = new Graph(n);
		
	}
	
	/**
	 * This method returns the node with the given name
	 * @param name a given name
	 * @return returns the node with the given name 
	 * or throw a graph exception if no such node exist
	 */
	public Node getNode(int name) throws GraphException{
		for(int i = 0; i<newnodelist.size(); i++) {
			if (newnodelist.get(i).getName() == name) {
				return newnodelist.get(i);
			}
		}
		throw new GraphException();
		
	}
	
	/**
	 * This method inserts a Edge into the graph
	 * @param u the starting node
	 * @param v the ending node
	 * @param edgeType the type of the edge
	 * @throws GraphException a new graph exception
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		if(!newnodelist.contains(v)||!newnodelist.contains(u)) { 
			throw new GraphException();
		}
		Edge newEdge = new Edge(u, v, edgeType);
		if(edgeArray[u.getName()][v.getName()] == newEdge || edgeArray[v.getName()][u.getName()] == newEdge) {
			throw new GraphException();
		}
		edgeArray[u.getName()][v.getName()] = newEdge;
		edgeArray[v.getName()][u.getName()] = newEdge;
	}
	
	/**
	 * This method return a iterator of all incident edges of a given node
	 * @param u a given node
	 * @return a iterator of all incident edges of a given node 
	 * @throws GraphException throw a graph exception if no such edge exist
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException{
		if(!newnodelist.contains(u)) {
			throw new GraphException();
		}
		Edge[] incidentEdgearray = new Edge[edgeArray.length];
		int count = 0;
		for (int i = edgeArray.length-1; i>= 0; i--) {
			if (edgeArray[u.getName()][i]!= null) {
				incidentEdgearray[count] = edgeArray[u.getName()][i];
				count ++;
			}
		}
		if(count == 0) {
			return null;
		}
		Edge[] newincidentEdgearray = Arrays.copyOf(incidentEdgearray, count);
		return new ArrayIterator<Edge>(newincidentEdgearray, count);
		
	}
	
	/**
	 * This method returns a edge that links the given nodes
	 * @param u a given initial node
	 * @param v a given end node
	 * @return the Edge that links the given nodes
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		if(!newnodelist.contains(v)||!newnodelist.contains(u)) { 
			throw new GraphException();
		}
		else {
			return edgeArray[u.getName()][v.getName()];
		}
		
	}
	
	/**
	 * This method check if two nodes are adjacent
	 * @param u a given initial node
	 * @param v a given end node
	 * @return the Edge that links the given nodes
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		if(!newnodelist.contains(v)||!newnodelist.contains(u)) { 
			throw new GraphException();
		}
		if(edgeArray[u.getName()][v.getName()]!=null) {
			return true;
		}
		return false;
		
	}
	
	

}
