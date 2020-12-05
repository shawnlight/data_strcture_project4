import student.TestCase;

/**
 * @author CS Staff
 * @version 2020-11-17
 */
public class GraphTest extends TestCase {

    public void setUp() throws Exception {
        // Not Implemented
    }


    public void tearDown() throws Exception {
    }


    public void testAddEdge() {

        Graph theGraph = new Graph(3);
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(2, 0);

        assertEquals(3, theGraph.getNumEdges());
    }


    public void testGetNode() {
        Graph theGraph = new Graph(3);
        theGraph.addEdge(0, 1);

        Node theNode = theGraph.getNode(2);
        assertEquals(2, theNode.getID());
    }


    public void testGetNumNodes() {
        Graph theGraph = new Graph(3);
        assertEquals(3, theGraph.getNumNodes());
    }


    public void testGetNumEdges() {
        Graph theGraph = new Graph(3);
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(2, 0);

        assertEquals(3, theGraph.getNumEdges());
    }


    public void testToString() {
        Graph theGraph = new Graph(3);
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(2, 0);

        assertFuzzyEquals("Nodes: " + Integer.toString(theGraph.getNumNodes())
            + "\nEdges: " + Integer.toString(theGraph.getNumEdges()), theGraph
                .toString());

    }

}
