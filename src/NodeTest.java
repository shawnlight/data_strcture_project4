import java.util.Iterator;
import java.util.LinkedList;
import student.TestCase;

/**
 * @author CS Staff
 * @version 2020-11-17
 */
public class NodeTest extends TestCase {

    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;

    public void setUp() throws Exception {
        node1 = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
        node4 = new Node(4);

    }


    public void testAddNeighbor() {

        Iterator<Node> neighborhood = null;

        this.node1.addNeighbor(node2);

        neighborhood = this.node1.getNeighbors();

        assertTrue(neighborhood.hasNext());

    }


    public void testGetNeighbors() {
        Iterator<Node> neighborhood = null;

        this.node1.addNeighbor(node2);
        this.node1.addNeighbor(node3);
        this.node1.addNeighbor(node4);

        neighborhood = this.node1.getNeighbors();
        assertNotNull(neighborhood);
    }


    public void testGetID() {
        assertEquals(1, this.node1.getID());
    }


    public void testToString() {
        assertEquals("2", this.node2.toString());
    }

}
