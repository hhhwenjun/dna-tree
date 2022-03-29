/**
 * The fly weight node class extends node
 * 
 * @author Wenjun Han
 * @version 2.28
 *
 */
public class FlyWeight extends Node {
    /**
     * Default constructor of Fly weight node
     */
    public FlyWeight() {
        super();
    }


    /**
     * Check if the node is a leaf
     * 
     * @return True if a leaf node, false if not, default is true.
     */
    public boolean isLeaf() {
        return true;
    }


    /**
     * Check if the node is an internal node
     * 
     * @return true if it is an internal node
     */
    public boolean isInternal() {
        return false;
    }
}
