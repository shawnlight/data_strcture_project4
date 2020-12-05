import java.util.Scanner;
import java.io.File;
import java.util.Iterator;

/**
 * Main program for analyzing CA road network data from SNAP dataset.
 */
public class RoadMap {

    /**
     * Driver code that reads a file name from args[1], opens the
     * file, and reads all edges in the file into a Graph structure.
     *
     * @param args[]
     *            is a list of command line arguments.
     */
    public static void main(String args[]) {
        // The roads data structure.
        Graph roads = new Graph(1);
        int graphMethodToRun = -1;
        // Check whether any command-line arguments were given.
        if (args.length < 2) {
            // If no arguments were given, display a message.
            System.out.println("Error: Incorrect number of parameters: \n"
                + "   Usage: RoadMap <map-data-file> <node-count> <optional-cmd>");
            System.exit(1);
        }
        else {
            // Create a pointer to the file at args[0].
            File fp = new File(args[0]);

            // Get the number of nodes in the file.
            int numRoads = Integer.parseInt(args[1]);

            // By default, run all of the methods. For testing, we want to run
            // individual ones.

            if (args.length == 3) {
                graphMethodToRun = Integer.parseInt(args[2]);
            }

            // Create graph
            roads = new Graph(numRoads);

            // Create a string for reading data.
            String edge;
            // Integers for storing edges and graph size.
            int n1, n2;
            // Try to execute the following block of code.
            try {
                // Start a scanner at the beginning of the File.
                Scanner input = new Scanner(fp);
                // For each line in the file...
                while (input.hasNextLine()) {
                    // Read the next edge into a String.
                    edge = input.nextLine();
                    // Filter out comment marks.
                    if (!(Character.compare(edge.charAt(0), '#') == 0)
                        && !(Character.compare(edge.charAt(0), '%') == 0)) {

                        // Read in each edge.
                        n1 = Integer.parseInt(edge.split("\\s+")[0]);
                        n2 = Integer.parseInt(edge.split("\\s+")[1]);
                        roads.addEdge(n1, n2);

                    }
                }
                // Close the Scanner object.
                input.close();
            }
            // If an error occurs, raise the following exception.
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        switch (graphMethodToRun) {
            case 1:
                printBreadthFirstTraversal(roads);
                break;
            case 2:
                printDepthFirstTraversal(roads);
                break;
            case 3:
                printShortestDistance(roads);
                break;
            case 4:
                printlongestShortestPath(roads);
                break;
            case 5:
                printShortestPath(roads);
                break;
            case 6:
                printPathExists(roads);
                break;
            default:
                printBreadthFirstTraversal(roads);
                printDepthFirstTraversal(roads);
                printShortestDistance(roads);
                printlongestShortestPath(roads);
                printShortestPath(roads);
                printPathExists(roads);
        }

    }
    // Execution completed.


    /**
     * Output from GraphMethods.breadthFirstTraversal()
     * 
     * @param roads
     */
    private static void printBreadthFirstTraversal(Graph roads) {
        // Output for GraphMethods.breadthFirstTraversal()
        System.out.println("<Breadth First Traversal>");
        Iterator<Node> output;
        for (int i = 0; i < roads.getNumNodes(); i++) {
            output = GraphMethods.breadthFirstTraversal(roads, i).iterator();
            System.out.println("Node " + Integer.toString(i) + ": ");
            System.out.print("\t");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
        }
        System.out.println("</Breadth First Traversal>");
    }


    /**
     * Output from GraphMethods.depthFirstTraversal()
     * 
     * @param roads
     */
    private static void printDepthFirstTraversal(Graph roads) {
        Iterator<Node> output;
        System.out.println("<Depth First Traversal>");
        for (int i = 0; i < roads.getNumNodes(); i++) {
            output = GraphMethods.depthFirstTraversal(roads, i).iterator();
            System.out.println("Node " + Integer.toString(i) + ": ");
            System.out.print("\t");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
        }
        System.out.println("</Depth First Traversal>");
    }


    /**
     * Output for GraphMethods.shortestDistance()
     * 
     * @param roads
     */
    private static void printShortestDistance(Graph roads) {
        System.out.println("<Shortest Distances>");
        for (int i = 0; i < roads.getNumNodes(); i++) {
            System.out.println("Distances from " + Integer.toString(i) + ": ");
            System.out.print("\t");
            for (int j = 0; j < roads.getNumNodes(); j++) {
                System.out.print(GraphMethods.shortestDistance(roads, i, j));
                if (j < roads.getNumNodes() - 1)
                    System.out.print(" ");
                else
                    System.out.println("");
            }
        }
        System.out.println("</Shortest Distances>");
    }


    /**
     * Output for GraphMethods.longestShortestPath()
     * 
     * @param roads
     */
    private static void printlongestShortestPath(Graph roads) {
        System.out.println("<MaxDist>");
        System.out.print("\t");
        for (int i = 0; i < roads.getNumNodes(); i++) {
            System.out.print(GraphMethods.longestShortestPath(roads, i));
            if (i == (roads.getNumNodes() - 1)) {
                System.out.println("");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println("</MaxDist>");
    }


    /**
     * Output from GraphMethods.longestShortestPath()
     * 
     * @param roads
     *            the graph
     */
    private static void printShortestPath(Graph roads) {
        Iterator<Node> output;
        System.out.println("<Shortest Paths>");
        for (int i = 0; i < roads.getNumNodes(); i++) {
            System.out.println("From Node " + Integer.toString(i) + ":");
            for (int j = 0; j < roads.getNumNodes(); j++) {
                output = GraphMethods.shortestPath(roads, i, j).iterator();
                System.out.print("\t To Node " + Integer.toString(j) + ": ");
                while (output.hasNext())
                    System.out.print(output.next() + " ");
                System.out.println("");
            }
        }
        System.out.println("</Shortest Paths>");
    }


    /**
     * Output from GraphMethods.printPathExists()
     * 
     * @param roads
     *            the graph
     */
    private static void printPathExists(Graph roads) {
        Iterator<Node> output;
        System.out.println("<Does Path Exist?>");
        int[] nodeList1 = new int[roads.getNumNodes()];
        int[] nodeList2 = new int[(roads.getNumNodes() + 1) / 2];
        int[] nodeList10 = new int[(roads.getNumNodes() + 1) / 10];
        int[] lastNode = new int[1];
        for (int i = 0; i < roads.getNumNodes(); i++) { // Generate all nodes.
            nodeList1[i] = i;
            if (i % 2 == 0)
                nodeList2[i / 2] = i;
            if (i % 10 == 0)
                nodeList2[i / 10] = i;
        }
        lastNode[0] = roads.getNumNodes() - 1;
        // Now check on the 4 node sets above
        for (int i = 0; i < roads.getNumNodes(); i++) {
            output = GraphMethods.pathExists(roads, nodeList1, i).iterator();
            System.out.println("Shortest path to " + Integer.toString(i)
                + " from:");
            System.out.print("\t All nodes: ");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
            output = GraphMethods.pathExists(roads, nodeList2, i).iterator();
            System.out.print("\t Even nodes: ");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
            output = GraphMethods.pathExists(roads, nodeList10, i).iterator();
            System.out.print("\t Every 10th node: ");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
            output = GraphMethods.pathExists(roads, lastNode, i).iterator();
            System.out.print("\t Last node: ");
            while (output.hasNext())
                System.out.print(output.next() + " ");
            System.out.println("");
        }
        System.out.println("</Does Path Exist?>");
    }
}
