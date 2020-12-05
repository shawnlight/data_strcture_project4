import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import student.TestCase;

/**
 * @author CS Staff
 * @version 2020-11-17
 */
public class GraphMethodsTest extends TestCase {

    private String[] args;
    private String curPath;
    private Graph theGraph;

    /**
     * set the initial value
     */
    public void setUp() throws Exception {
        args = new String[3];
        curPath = System.getProperty("user.dir");
        String input = curPath + "/src/input.txt";
        args[0] = input;
        args[1] = "39";
        args[2] = "0";

        theGraph = new Graph(6);
        theGraph.addEdge(1, 3);
        theGraph.addEdge(1, 5);
        theGraph.addEdge(3, 1);
        theGraph.addEdge(5, 1);
        theGraph.addEdge(3, 2);
        theGraph.addEdge(5, 0);
        theGraph.addEdge(2, 3);
        theGraph.addEdge(0, 5);
        theGraph.addEdge(2, 4);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(4, 2);
        theGraph.addEdge(4, 0);

    }


    /**
     * get the output string of input file
     * 
     * @param path
     *            path of file
     * @return
     *         output string
     * @throws IOException
     *             IO exception for file reader
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * test BFS
     * 
     */
    public void testBFS() {
        List<Node> list = GraphMethods.breadthFirstTraversal(theGraph, 1);
        assertEquals(list.get(1).getID(), 3);

    }


    /**
     * test DFS
     * 
     */
    public void testDFS() {
        List<Node> list = GraphMethods.depthFirstTraversal(theGraph, 1);
        assertEquals(list.get(1).getID(), 5);
    }


    /**
     * test shortest distance
     */
    public void testShortestDistance() {
        int distance = GraphMethods.shortestDistance(theGraph, 1, 4);
        assertEquals(distance, 3);
    }


    /**
     * test longest shortest distance
     */
    public void testLongestShortestDistance() {

        int distance = GraphMethods.longestShortestPath(theGraph, 1);
        assertEquals(distance, 3);

    }


    /**
     * test shortest path
     */
    public void testShortestPath() {
        List<Node> list = GraphMethods.shortestPath(theGraph, 1, 4);
        assertEquals(list.get(1).getID(), 3);
        assertEquals(list.get(2).getID(), 2);
        assertEquals(list.get(3).getID(), 4);

    }


    /**
     * test path exists
     */
    public void testPathExists() {
        List<Node> list = new LinkedList<>();
        list = GraphMethods.shortestPath(theGraph, 1, 4);
        assertEquals(list.get(1).getID(), 3);
        assertEquals(list.get(2).getID(), 2);
        assertEquals(list.get(3).getID(), 4);

    }


    /**
     * test input file
     * 
     * @throws IOException
     */
    public void testInput() throws IOException {
        RoadMap.main(args);
        assertFuzzyEquals(readFile(curPath + "/src/output.txt"), systemOut()
            .getHistory());
    }

}
