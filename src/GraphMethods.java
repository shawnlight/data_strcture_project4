import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * On my honor:
 *
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 *
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this exam with
 * anyone other than my instructor, ACM/UPE tutors, or the TAs
 * assigned to this course. I have violated neither the spirit
 * nor letter of this restriction.
 *
 * @author student
 * @version mm/dd/yyyy
 *
 */
public class GraphMethods {

    /**
     *
     * Implement a breadth-first traversal.
     *
     * @param theGraph
     *            is a Graph object to traverse
     *
     * @param start
     *            is the index of the starting Node for the traversal
     *
     * @return a List<Node> of all Nodes in the order they were visited.
     *         The node with index start is visited first.
     */
    @SuppressWarnings("rawtypes")
    public static List<Node> breadthFirstTraversal(Graph theGraph, int start) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        // Your implementation goes here
        Queue<Node> queue = new LinkedList<>();
        Node startNode = theGraph.getNode(start);
        queue.offer(startNode);
        int nodeNum = theGraph.getNumNodes();
        boolean[] visited = new boolean[nodeNum];
        visited[start] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            output.add(node);
            Iterator it = node.getNeighbors();
            while (it.hasNext()) {
                Node next = (Node)it.next();
                int id = next.getID();
                if (visited[id]) {
                    continue;
                }

                queue.offer(next);
                visited[id] = true;
            }
        }

        return output;
    }


    /**
     * Implement a depth first traversal
     *
     * @param theGraph
     *            is a Graph object to traverse
     *
     * @param start
     *            is the index of the starting Node for the traversal
     *
     * @return a List<Node> of all Nodes in the order they were visited.
     *         The node with index start is visited first.
     */
    public static List<Node> depthFirstTraversal(Graph theGraph, int start) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        // Your implementation goes here
        int nodeNum = theGraph.getNumNodes();
        boolean[] visited = new boolean[nodeNum];
        Node startNode = theGraph.getNode(start);
        dfsHelper(output, startNode, visited);

        return output;

    }


    /**
     * DFS help function
     * 
     * @param out
     *            output list
     * @param node
     *            node to visit
     * @param visited
     *            if the node is visited
     */
    @SuppressWarnings("rawtypes")
    public static void dfsHelper(List<Node> out, Node node, boolean[] visited) {
        int id = node.getID();
        if (visited[id]) {
            return;
        }
        out.add(node);
        visited[id] = true;
        Iterator it = node.getNeighbors();
        Stack<Node> stack = new Stack<>();
        while (it.hasNext()) {
            Node next = (Node)it.next();
            stack.push(next);
        }
        while (!stack.isEmpty()) {
            Node lastNode = stack.pop();
            dfsHelper(out, lastNode, visited);
        }
    }


    /**
     * Find the length of the shortest path between two nodes in the graph.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is the index of the starting Node
     *
     * @param end
     *            is the index of target Node
     *
     * @return an int specifying the length of the shortest path between
     *         the Nodes at start and end. If start = end, return 0.
     */
    public static int shortestDistance(Graph theGraph, int start, int end) {
        // Initialize the output integer
        int output = 0;

        // Your implementation goes here
        Queue<Node> queue = new LinkedList<>();
        Node startNode = theGraph.getNode(start);
        queue.offer(startNode);
        boolean[] visited = new boolean[theGraph.getNumNodes()];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (node.getID() == end) {
                    return output;
                }

                Iterator it = node.getNeighbors();
                while (it.hasNext()) {
                    Node next = (Node)it.next();
                    if (visited[next.getID()]) {
                        continue;
                    }

                    queue.offer(next);
                    visited[next.getID()] = true;
                }
            }
            output++;
        }

        return output;
    }


    /**
     * Find the longest shortest path from start to any other Node in the graph
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is an int array of starting indices
     *
     * @return an int value specifying the length of the longest shortest path
     */
    public static int longestShortestPath(Graph theGraph, int start) {
        // Initialize the output integer
        int output = 0;

        // Your implementation goes here
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            output = Math.max(output, shortestDistance(theGraph, start, i));
        }
        return output;

    }


    /**
     * Find the shortest path between two nodes in the graph.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is the index of the starting Node
     *
     * @param end
     *            is the index of target Node
     *
     * @return a List<Node> of all Nodes visited on the shortest path between
     *         start and end, in the order that they were visited.
     */
    @SuppressWarnings("unchecked")
    public static List<Node> shortestPath(Graph theGraph, int start, int end) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        // Your implementation goes here
        int nodeNum = theGraph.getNumNodes();
        // record distance from start
        Pair[] distance = new Pair[nodeNum];
        // visited node record
        boolean[] visited = new boolean[nodeNum];
        // record the previous parent node
        Node[] pre = new Node[nodeNum];

        for (int i = 0; i < nodeNum; i++) {
            if (i == start) {
                Pair pair = new Pair(0, Integer.MAX_VALUE);
                distance[i] = pair;
                continue;
            }

            Pair pair = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
            distance[i] = pair;
        }

        int startID = 0;

        for (int i = 0; i < nodeNum; i++) {

            int minV = minVertex(theGraph, visited, distance);
            Node nextMin = theGraph.getNode(minV);
            // unreachable
            if (distance[minV].getDistance() == Integer.MAX_VALUE) {
                break;
            }
            visited[minV] = true;
            Iterator it = nextMin.getNeighbors();

            while (it.hasNext()) {
                Node next = (Node)it.next();

                int index = next.getID();
                if (distance[index].getDistance() > distance[minV].getDistance()
                    + 1) {
                    distance[index].setDistance(distance[minV].getDistance()
                        + 1);
                    pre[index] = nextMin;
                    distance[index].setPreID(startID++);

                }
            }

        }

        // unreachable
        if (distance[end].getDistance() == Integer.MAX_VALUE) {
            return output;
        }

        // from end to start, add to the first position
        int num = end;
        while (num != start) {
            Node toAdd = pre[num];
            output.add(0, toAdd);
            num = toAdd.getID();

        }

        // add the end node
        output.add(theGraph.getNode(end));

        return output;
    }


    /**
     * get the minimum distance from distance array, follow the
     * neighbor sequence
     * 
     * @param theGraph
     *            graph
     * @param visited
     *            if the node is visited
     * @param d
     *            distance array
     * @return minimum distance node ID
     */
    public static int minVertex(Graph theGraph, boolean[] visited, Pair[] d) {
        int v = 0;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (!visited[i]) {
                v = i;
                break;
            }
        }

        // find the lowest one with lowest node ID
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (!visited[i] && (d[v].getDistance() > d[i].getDistance())) {
                v = i;
            }
        }

        // collect all tied nodes
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (!visited[i] && (d[v].getDistance() == d[i].getDistance())) {
                list.add(i);
            }
        }

        // choose the one with lowest index, means it will be traversed first
        for (int item : list) {
            if (d[item].getPreID() == Integer.MAX_VALUE) {
                continue;
            }

            if (d[item].getPreID() < d[v].getPreID()) {
                v = item;
            }
        }

        return v;

    }


    /**
     * Determine whether there exists a path meeting the goal.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is an int array of starting indices
     *
     * @param end
     *            is the index of target Node
     *
     * @return a List<Node> specifying the shortest path from ANY start
     *         Node to the target Node
     */
    public static List<Node> pathExists(Graph theGraph, int[] start, int end) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        // Your implementation goes here
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < start.length; i++) {
            List<Node> temp = shortestPath(theGraph, start[i], end);
            if (temp.size() == 0) {
                continue;
            }

            if (temp.size() < min) {
                output = temp;
                min = temp.size();
            }
        }

        return output;
    }
}
