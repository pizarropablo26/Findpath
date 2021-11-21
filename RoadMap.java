import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class represent the road map and there there is a road, return it
 * @author jy
 *
 */
public class RoadMap {
	private Graph newGraph;
	private int StartingNode;
	private int DestinationNode;
	private int InitialMoney;
	private int toll;
	private int gain;
	private Stack<Node> pathStack; 

	
	/**
	 * Constructor for building a graph from the input file specified in the parameter; 
	 * this graph represents the road map.
	 * @param inputFile the inputed file 
	 * @throws MapException a exception thrown if the file is not opened
	 */
	public RoadMap(String inputFile) throws MapException{
		BufferedReader in;
		int width; //vertical road
		int length; //horizontal road
		String[][] maparray;
		pathStack = new Stack<>();
		try {
			in = new BufferedReader(new FileReader(inputFile));
			in.readLine(); // Ignore first line
			StartingNode = Integer.parseInt(in.readLine());
			DestinationNode = Integer.parseInt(in.readLine());
			width = Integer.parseInt(in.readLine());
			length = Integer.parseInt(in.readLine());
			maparray = new String [length*2-1][width*2-1];
			InitialMoney = Integer.parseInt(in.readLine());
			toll = Integer.parseInt(in.readLine());
			gain = Integer.parseInt(in.readLine());
			for(int i = 0; i<(length*2)-1; i++) {
				maparray[i] = in.readLine().split("");
			}
			in.close();
		} catch (IOException e) {
			throw new MapException();
		}
		newGraph = new Graph(width*length);
		int count = 0;
		for(int row = 0; row<length*2-1; row = row+2) {
			for(int col = 0; col<width*2-1; col = col+2) {
				if (maparray[row][col].equals("+")) {
					maparray[row][col] = String.valueOf(count); 
					count ++;
				}
				else {
					count ++;
				}
			}
		}
		int name1 = 0;
		int name2 = 0;
		for(int row = 0; row<length*2-1; row = row+2) {
			for(int col = 1; col<width*2-1; col = col+2) {
//				0 (if the edge represents a public road), 1 (if the edge represents a
//						private road), or -1 (if the edge represents a reward road).
				if(!maparray[row][col-1].equals("X") && !maparray[row][col+1].equals("X")) {
					name1 = Integer.parseInt(maparray[row][col-1]);
					name2 = Integer.parseInt(maparray[row][col+1]);
					try {
						if (maparray[row][col].equals("F")) { //public
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), 0);
						}
						else if (maparray[row][col].equals("T")) { //private
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), 1);
						}
						else if (maparray[row][col].equals("C")) { //reward
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), -1);
						}
					} catch (GraphException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		for(int col = 0; col<width*2-1; col = col+2) {
			for(int row = 1; row<length*2-1; row = row+2) {
//				0 (if the edge represents a public road), 1 (if the edge represents a
//						private road), or -1 (if the edge represents a reward road).
				if(!maparray[row-1][col].equals("X") && !maparray[row+1][col].equals("X")) {
					name1 = Integer.parseInt(maparray[row-1][col]);
					name2 = Integer.parseInt(maparray[row+1][col]);
					try {
						if (maparray[row][col].equals("F")) { //public
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), 0);
						}
						else if (maparray[row][col].equals("T")) { //private
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), 1);
						}
						else if (maparray[row][col].equals("C")) { //reward
							newGraph.insertEdge(newGraph.getNode(name1), newGraph.getNode(name2), -1);
						}
					} catch (GraphException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}

	}
	
	/**
	 * This method returns the graph
	 * @return a newe graph containing the nodes
	 */
	public Graph getGraph() {
		return newGraph;
		
	}
	
	/**
	 * This method returns the starting node
	 * @return the starting node
	 */
	public int getStartingNode() {
		return StartingNode;
		
	}
	
	/**
	 * This method returns the destination node
	 * @return the destination node
	 */
	int getDestinationNode() {
		return DestinationNode;
		
	}
	
	/**
	 * This method returns the initial money
	 * @return the initial money
	 */
	int getInitialMoney() {
		return InitialMoney;
		
	}
	
	/**
	 * This method returns a Java Iterator containing the nodes 
	 * of a path from the start node to the destination node as specified above, 
	 * if such a path exists.
	 * @param start value of starting node
	 * @param destination value of the destination node
	 * @param initialMoney the initial given money
	 * @return a iterator containing the node of the path leading to the destination node
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		if(searchPath(start, destination, initialMoney)) {
			Iterator<Node> stackIter = pathStack.iterator();
			return stackIter;
		}

		return null;

	}
	
	/**
	 * This method returns true if there is a path
	 * from the start node to the destination node as specified above.
	 * @param start value of starting node
	 * @param destination value of the destination node
	 * @param initialMoney the initial given money
	 * @return This method returns true if such a path exists.
	 */
	private boolean searchPath(int start, int destination, int initialMoney) {
		StartingNode = start;
		DestinationNode = destination;
		InitialMoney = initialMoney;
		Node initNode;
		Node endNode;
		Node nextNode;
		try {
			initNode = newGraph.getNode(StartingNode);
			endNode = newGraph.getNode(DestinationNode);
			initNode.setMark(true); // true means already visited
			pathStack.push(initNode);
			if(initNode.equals(endNode)) {
				return true;
			}
			Iterator<Edge> edgeIter = newGraph.incidentEdges(initNode);
			while(edgeIter.hasNext()) {
				Edge nextEdge = edgeIter.next();
				if(nextEdge.getType() == 1) { // if next edge is private
					InitialMoney = InitialMoney - toll;
				}
				if(nextEdge.getType() == -1) {// if next edge is reward
					InitialMoney = InitialMoney + gain;
				}
				if(initNode == nextEdge.firstEndpoint()) {
					nextNode = nextEdge.secondEndpoint();
				}
				else {
					nextNode = nextEdge.firstEndpoint();
				}
				if (nextNode.getMark() == false && InitialMoney >= 0) {
					if(searchPath(nextNode.getName(), DestinationNode, InitialMoney)) {
						return true;
					}
					
				}
				if(nextEdge.getType() == 1) { // if next edge is private
					InitialMoney = InitialMoney + toll;
				}
				if(nextEdge.getType() == -1) {// if next edge is reward
					InitialMoney = InitialMoney - gain;
				}
			}
			Node popnode = pathStack.pop();
			popnode.setMark(false);
//			initNode.setMark(false);

			
//			return null;

		} catch (GraphException e) {
			e.printStackTrace();
		}
		return false;
	}

}
