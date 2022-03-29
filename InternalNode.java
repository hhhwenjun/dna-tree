
/**
 * Create node class of internal node
 * 
 * @author Wenjun Han
 * @version 2022.2.28
 *
 */
public class InternalNode extends Node {

    private Node branchA;
    private Node branchC;
    private Node branchG;
    private Node branchT;
    private Node branchD; // Dollar sign : $

    /**
     * Constructor of internal Node
     * 
     * @param level
     *            The level of the internal node
     * 
     */
    public InternalNode(int level) {
        super(level);
    }


    /**
     * Setter of branch A
     * 
     * @param nodeA
     *            The branch A node
     */
    public void setA(Node nodeA) {
        this.branchA = nodeA;
    }


    /**
     * Getter of branch A
     * 
     * @return The node of A
     */
    public Node getA() {
        return branchA;
    }


    /**
     * Setter of branch C
     * 
     * @param nodeC
     *            The branch C node
     */
    public void setC(Node nodeC) {
        this.branchC = nodeC;
    }


    /**
     * Getter of branch C
     * 
     * @return The node of C
     */
    public Node getC() {
        return branchC;
    }


    /**
     * Setter of branch G
     * 
     * @param nodeG
     *            The branch G node
     */
    public void setG(Node nodeG) {
        this.branchG = nodeG;
    }


    /**
     * Getter of branch G
     * 
     * @return The node of G
     */
    public Node getG() {
        return branchG;
    }


    /**
     * Setter of branch T
     * 
     * @param nodeT
     *            The branch T node
     */
    public void setT(Node nodeT) {
        this.branchT = nodeT;
    }


    /**
     * Getter of branch T
     * 
     * @return The node of T
     */
    public Node getT() {
        return branchT;
    }


    /**
     * Setter of branch dollar sign prefix node
     * 
     * @param nodeD
     *            The branch of prefix
     */
    public void setD(Node nodeD) {
        this.branchD = nodeD;
    }


    /**
     * Getter of branch prefix node
     * 
     * @return The node of the prefix
     */
    public Node getD() {
        return branchD;
    }


    /**
     * Check if the node is a internal node
     * 
     * @return false since it is not a leaf
     */
    @Override
    public boolean isLeaf() {
        return false;
    }


    /**
     * Check if the node is an internal node
     * 
     * @return true if it is an internal node
     */
    @Override
    public boolean isInternal() {
        return true;
    }

}
