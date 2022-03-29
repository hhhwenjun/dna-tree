import student.TestCase;

/**
 * Test of all the nodes
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.3.2
 *
 */
public class NodeTest extends TestCase {

    private Node node;
    private InternalNode inter;
    private Leaf leaf;
    private FlyWeight fly;

    /**
     * set up cases for the tests
     */
    public void setUp() {
        node = new Node();
        inter = new InternalNode(2);
        leaf = new Leaf("ABC", 0);
        fly = new FlyWeight();
    }


    /**
     * test getLevel()
     */
    public void testGetLevel() {
        Node secondNode = new Node(3);
        assertEquals(secondNode.getLevel(), 3);
        assertEquals(node.getLevel(), 0);
        assertEquals(leaf.getLevel(), 0);
    }


    /**
     * test setLevel()
     * 
     */
    public void testSetLevel() {
        node.setLevel(5);
        assertEquals(node.getLevel(), 5);
    }


    /**
     * test isLeaf()
     */
    public void testIsLeaf() {
        assertFalse(node.isLeaf());
        assertTrue(fly.isLeaf());
        assertTrue(leaf.isLeaf());
        assertFalse(inter.isLeaf());
    }


    /**
     * test isInternal()
     */
    public void testIsInternal() {
        assertFalse(node.isInternal());
        assertFalse(fly.isInternal());
        assertFalse(leaf.isInternal());
        assertTrue(inter.isInternal());
    }


    /**
     * test leaf node setData() & getData()
     */
    public void testGetSetData() {
        assertEquals(leaf.getData(), "ABC");
        leaf.setData("BBC");
        assertEquals(leaf.getData(), "BBC");
        Exception newCatch = null;
        try {
            leaf = new Leaf(null, 0);
        }
        catch (Exception e) {
            newCatch = e;
        }
        assertTrue(newCatch instanceof IllegalArgumentException);
        newCatch = null;
        try {
            leaf.setData(null);
        }
        catch (Exception e) {
            newCatch = e;
        }
        assertTrue(newCatch instanceof IllegalArgumentException);
    }


    /**
     * test getter & setter of branches of internal node
     */
    public void testInternalSetterGetter() {
        inter.setA(fly);
        inter.setC(fly);
        inter.setG(fly);
        inter.setT(fly);
        inter.setD(leaf);
        assertTrue(inter.getA() instanceof FlyWeight);
        assertTrue(inter.getC() instanceof FlyWeight);
        assertTrue(inter.getG() instanceof FlyWeight);
        assertTrue(inter.getT() instanceof FlyWeight);
        assertFalse(inter.getD() instanceof FlyWeight);
        assertEquals(inter.getT().getLevel(), 0);
    }
}
