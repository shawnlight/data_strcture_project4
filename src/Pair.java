/**
 * @author light
 * @version Dec 5, 2020
 */
public class Pair {
    private int distance;
    private int preID;

    /**
     * Pair constructor
     * 
     * @param x
     *            distance
     * @param y
     *            ID
     */
    public Pair(int x, int y) {
        distance = x;
        preID = y;
    }


    /**
     * get the distance
     * 
     * @return distance
     */
    public int getDistance() {
        return distance;
    }


    /**
     * set the distance
     * 
     * @param distance
     *            distance value
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }


    /**
     * get the ID
     * 
     * @return ID
     */
    public int getPreID() {
        return preID;
    }


    /**
     * set the ID
     * 
     * @param preID
     *            ID value
     */
    public void setPreID(int preID) {
        this.preID = preID;
    }

}
