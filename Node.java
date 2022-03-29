/**
 * Base class of DNA TreeNode
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.2.28
 *
 */
public class Node {

    /* The level of the node */
    private int level;

    /**
     * Default constructor of tree node, set it to root
     */
    public Node() {
        this.level = 0;
    }


    /**
     * Default constructor of tree node
     * 
     * @param level
     *            The level of the node
     */
    public Node(int level) {
        this.level = level;
    }


    /**
     * Get the level of the node
     * 
     * @return Level of the node
     */
    public int getLevel() {
        return level;
    }


    /**
     * Set the level of the node
     * 
     * @param level
     *            Set node to the level
     * 
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Check if the node is a leaf, default false
     * <p>
     * For inherited leaf and internal node, this method will be override.
     * </p>
     * 
     * @return True if a leaf node, false if not, default is true.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Check if the node is an internal node, default false
     * <p>
     * For inherited leaf and internal node, this method will be override.
     * </p>
     * 
     * @return true if it is an internal node, otherwise return false.
     */
    public boolean isInternal() {
        return false;
    }
}
