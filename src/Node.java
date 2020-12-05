import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * The Node class defines a node in the graph, with integer ID and a list
 * of neighboring Node objects;
 *
 * The neighbor list can be accessed using the addNeighbor() and getNeighbor()
 * methods;
 *
 * The node ID can be accessed using the getID() or toString() methods.
 */
public class Node {
    // private class data
    private int ID;
    private List<Node> neighbors;

    /**
     * This is the constructor for the Node class.
     *
     * @param key
     *            is the int ID for this node.
     */
    Node(int key) {
        ID = key;
        neighbors = new LinkedList<Node>();
        return;
    }


    /**
     * This method adds an item to the neighbor list.
     *
     * @param neighbor
     *            is a Node object to add to the neighbor list.
     */
    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
        return;
    }


    /**
     * This method returns a list of neighbors to this node.
     *
     * @return an Iterator object over the list of neighboring nodes.
     */
    public Iterator getNeighbors() {
        return this.neighbors.iterator();
    }


    /**
     * This method returns the ID for this node.
     *
     * @return an int specifying the ID of this node.
     */
    public int getID() {
        return this.ID;
    }


    /**
     * This method returns a String representation of the node ID.
     *
     * @return an int specifying the ID of this node.
     */
    public String toString() {
        return Integer.toString(this.ID);
    }
}
