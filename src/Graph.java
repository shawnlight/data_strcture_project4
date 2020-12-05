/**
* The Graph class defines a directed graph of Node objects.
* 
*/
public class Graph {
	// private class data
	private int numNodes;
	private int numEdges;
	private Node[] nodeList;

	/**
	* This is the constructor for the Graph class.
 	*
 	* @param n is an int specifying the number of nodes in the graph,
 	* they are indexed by 0 through n-1. 
 	*/
	Graph(int n) {
		this.numNodes = n;
		this.numEdges = 0;
		this.nodeList = new Node[numNodes];
		for(int i = 0; i < n; i++)
			this.nodeList[i] = new Node(i);
		return;
	}

	/**
	* This method adds a directed edge from the Node at index n1 to
	*  the Node at index n2 (n1 --> n2).
	*
	* @param n1 is the tail of the edge.
	*
	* @param n2 is the head of the edge.
	*/
	public void addEdge(int n1, int n2) {
		this.nodeList[n1].addNeighbor(nodeList[n2]);
		numEdges++;
		return;
	}

	/**
	* This method gets the Node object at index id.
	*
	* @param id is the index to fetch.
	*
	* @return a Node object at index id.
	*/
	public Node getNode(int id) {
		return this.nodeList[id];
	}

	/**
	* This method gets the number of nodes in the graph.
	*
	* @return the number of nodes in the graph.
	*/
	public int getNumNodes() {
		return this.numNodes;
	}

	/**
	* This method gets the number of edges in the graph.
	*
	* @return the number of directed edges in the graph (a two way edge
	* counts twice).
	*/
	public int getNumEdges() {
		return this.numEdges;
	}

	/**
	* This method prints some metadata about the graph.
	*
	* @return a String specifying the number of nodes and edges.
	*/
	public String toString() {
        return "Nodes: " + Integer.toString(this.numNodes) + "\nEdges: "
            + Integer.toString(this.numEdges);
	}
	
	
}
