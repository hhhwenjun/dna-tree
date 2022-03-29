
/**
 * Construct leaf node for the DNA tree
 * 
 * @author Wenjun Han (hwenjun)
 * @version 2.28
 *
 */
public class Leaf extends Node {

    /** We read in the DNA sequence in string **/
    private String data;

    /**
     * Constructor of tree leaf
     * 
     * @param data
     *            The content of the node
     * @param level
     *            The level of the node
     * @throws IllegalArgumentException
     *             Throw if data is null
     * 
     */
    public Leaf(String data, int level) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.data = data;
        super.setLevel(level);
    }


    /**
     * Get the data of the node
     * 
     * @return Content of node
     */
    public String getData() {
        return data;
    }


    /**
     * Set the data of the node
     * 
     * @param newData
     *            Set new data to the node
     * @throws IllegalArgumentException
     *             Throws exception if data is null
     */
    public void setData(String newData) throws IllegalArgumentException {
        if (newData == null) {
            throw new IllegalArgumentException();
        }
        this.data = newData;
    }


    /**
     * Check if the node is a leaf
     * 
     * @return True if a leaf node, false if not, default is true.
     */
    @Override
    public boolean isLeaf() {
        return true;
    }


    /**
     * Check if the node is an internal node
     * 
     * @return true if it is an internal node, otherwise return false.
     */
    @Override
    public boolean isInternal() {
        return false;
    }
}
