import student.TestCase;

/**
 * Test for the DNA tree
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.3.2
 *
 */
public class TreeTest extends TestCase {

    private Tree tree;
    private TreeTraverse traverser;

    /**
     * set up for the tree
     */
    public void setUp() {
        tree = new Tree();
        tree.insert("AAAA");
        tree.insert("ACGT");
        Node root = tree.getRoot();
        traverser = new TreeTraverse(root);
    }


    /**
     * test insert method for multiple cases
     */
    public void testInsert() {
        System.out.println("******* test insert() Start *****");
        Exception iae = null;
        try {
            tree.insert("");
        }
        catch (Exception e) {
            iae = e;
        }
        assertTrue(iae instanceof IllegalArgumentException);
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAA"));
        assertFalse(tree.insert("AAAA"));
        assertFalse(tree.insert("AA"));
        assertFalse(tree.insert("ACGT"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.insert("ACTGGGAA"));
        assertTrue(tree.remove("AAAA"));
        assertTrue(tree.insert("A"));
        traverser.printTree();
        assertTrue(contains(systemOut().getHistory(), "AAACCCCGGTGAAAACGTA"));
        assertTrue(contains(systemOut().getHistory(), "ACTGGGAA"));
        assertTrue(contains(systemOut().getHistory(), "AA"));
        System.out.println("******* test insert() End *****");
    }


    /**
     * test remove method for multiple cases
     */
    public void testRemove() {
        System.out.println("******* test Remove() Start *****");
        Exception iae = null;
        try {
            tree.remove("");
        }
        catch (Exception e) {
            iae = e;
        }
        assertFalse(tree.remove("ACGG"));
        assertTrue(iae instanceof IllegalArgumentException);
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.remove("ACGT"));
        assertTrue(tree.insert("ACCTT"));
        assertTrue(tree.remove("AAAA"));
        traverser.printTree();
        assertTrue(tree.remove("ACCTT"));
        assertTrue(tree.remove("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.remove("AA"));
        assertFalse(tree.remove("AA"));
        // re-gain the root since it may change
        Node currentRoot = tree.getRoot();
        TreeTraverse traverser2 = new TreeTraverse(currentRoot);
        traverser2.printTree();
        assertTrue(contains(systemOut().getHistory(), "E"));
        System.out.println("******* test Remove() End *****");
    }


    /**
     * test if can remove to origin node
     */
    public void testRemoveToOrigin() {
        System.out.println("******* test RemoveToOrigin() Start *****");
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.insert("ACTGGGAA"));
        assertTrue(tree.remove("ACTGGGAA"));
        assertTrue(tree.remove("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.remove("AAAA"));
        assertTrue(tree.remove("ACGT"));
        Node currentRoot = tree.getRoot();
        TreeTraverse traverser2 = new TreeTraverse(currentRoot);
        traverser2.printTree();
        assertTrue(contains(systemOut().getHistory(), "AA"));
        System.out.println("******* test RemoveToOrigin() End *****");
    }


    /**
     * test printLength()
     */
    public void testprintLength() {
        System.out.println("******* test printLength() Start *****");
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.insert("ACTGGGAA"));
        assertTrue(tree.remove("ACGT"));
        assertTrue(tree.insert("ACCTT"));
        assertTrue(tree.insert("ACTTA"));
        assertTrue(tree.insert("TATA"));
        assertTrue(tree.insert("TCG"));
        Node currentRoot = tree.getRoot();
        TreeTraverse traverser2 = new TreeTraverse(currentRoot);
        traverser2.printLength();
        assertTrue(contains(systemOut().getHistory(),
            "AAACCCCGGTGAAAACGTA 19"));
        System.out.println("******* test printLength() End *****");
    }


    /**
     * test printStats()
     */
    public void testprintStats() {
        System.out.println("******* test printStats() Start *****");
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.insert("ACTGGGAA"));
        assertTrue(tree.remove("ACGT"));
        assertTrue(tree.insert("ACCTT"));
        assertTrue(tree.insert("ACTTA"));
        assertTrue(tree.insert("TATA"));
        assertTrue(tree.insert("TCG"));
        Node currentRoot = tree.getRoot();
        TreeTraverse traverser3 = new TreeTraverse(currentRoot);
        traverser3.printStats();
        assertTrue(contains(systemOut().getHistory(),
            "ACTGGGAA A:37.50 C:12.50 G:37.50 T:12.50"));
        System.out.println("******* test printStats() End *****");
    }


    /**
     * test search()
     */
    public void testSearch() {
        System.out.println("******* test Search() Start *****");
        traverser.search("AAAA", 0);
        assertTrue(contains(systemOut().getHistory(), "# of nodes visited: 3"));
        traverser.search("ACGT", 0);
        assertTrue(contains(systemOut().getHistory(), "# of nodes visited: 3"));
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAACCCCGGTGAAAACGTA"));
        assertTrue(tree.insert("ACTGGGAA"));
        assertTrue(tree.remove("ACGT"));
        assertTrue(tree.insert("ACCTT"));
        assertTrue(tree.insert("ACTTA"));
        assertTrue(tree.insert("TATA"));
        assertTrue(tree.insert("TCG"));
        assertTrue(tree.insert("T"));
        Node currentRoot = tree.getRoot();
        TreeTraverse traverserCurrent = new TreeTraverse(currentRoot);
        traverserCurrent.printLength();
        traverserCurrent.search("ACT", 1);
        traverserCurrent.search("ACTTC", 0);
        traverserCurrent.search("TCG", 0);
        traverserCurrent.search("T", 0);
        traverserCurrent.search("ACTGG", 0);
        traverserCurrent.search("ACTGG", 1);
        System.out.println("******* test Search() End *****");
    }


    /**
     * test insert() in complex cases
     */
    public void testInsertComplex() {
        System.out.println("******* test insert Complex() Start *****");
        assertTrue(tree.insert("A"));
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("AAA"));
        assertTrue(tree.insert("AAAAA"));
        assertTrue(tree.insert("AACCT"));
        assertFalse(tree.remove("ACGG"));
        assertTrue(tree.insert("AAAAAA"));
        assertTrue(tree.insert("C"));
        assertTrue(tree.insert("CG"));
        assertTrue(tree.insert("GG"));
        assertTrue(tree.remove("CG"));
        assertTrue(tree.remove("AAA"));
        assertTrue(tree.remove("AAAAA"));
        assertTrue(tree.remove("AAAAAA"));
        assertTrue(tree.remove("AA"));
        assertTrue(tree.remove("AAAA"));
        assertTrue(tree.remove("A"));
        assertTrue(tree.remove("C"));
        assertTrue(tree.remove("GG"));
        assertTrue(tree.remove("ACGT"));
        Node currentRoot = tree.getRoot();
        TreeTraverse traverser3 = new TreeTraverse(currentRoot);
        traverser3.printStats();
        Tree treeTemp = new Tree();
        assertTrue(treeTemp.insert("A"));
        assertTrue(treeTemp.insert("T"));
        assertTrue(treeTemp.remove("T"));
        assertFalse(treeTemp.remove("G"));
        assertTrue(treeTemp.remove("A"));
        assertFalse(treeTemp.remove("G"));
        assertTrue(treeTemp.insert("AAAA"));
        assertTrue(treeTemp.insert("AAA"));
        assertFalse(treeTemp.remove("AA"));
        assertTrue(treeTemp.insert("CT"));
        assertFalse(treeTemp.remove("CTG"));
        assertTrue(treeTemp.insert("TTGG"));
        assertTrue(treeTemp.insert("GG"));
        assertFalse(treeTemp.insert("GG"));
        assertTrue(treeTemp.insert("GGG"));
        assertTrue(treeTemp.insert("GGA"));
        assertFalse(treeTemp.remove("GGT"));
        assertFalse(treeTemp.remove("TTGGG"));
        assertFalse(treeTemp.remove("TTGA"));
        assertTrue(treeTemp.insert("TTGGGA"));
        Node tempRoot = treeTemp.getRoot();
        TreeTraverse traverserTemp = new TreeTraverse(tempRoot);
        traverserTemp.printLength();
        traverserTemp.search("TT", 0);
        System.out.println("******* test insert Complex() End *****");
    }


    /**
     * test insert in a short tree
     */
    public void testInsertShort() {
        System.out.println("******* test insert short() Start *****");
        assertTrue(tree.insert("AGC"));
        assertTrue(tree.insert("G"));
        assertTrue(tree.insert("GT"));
        assertTrue(tree.insert("TTT"));
        assertFalse(tree.insert("GT"));
        assertFalse(tree.insert("G"));
        assertTrue(tree.insert("TT"));
        assertTrue(tree.remove("TT"));
        Node tempRoot = tree.getRoot();
        TreeTraverse traverserTemp = new TreeTraverse(tempRoot);
        traverserTemp.printLength();
        System.out.println("******* test insert short() End *****");
    }


    /**
     * test insert in a long tree
     */
    public void testInsertLong() {
        System.out.println("******* test insert long() Start *****");
        assertTrue(tree.insert("A"));
        assertTrue(tree.insert("G"));
        assertTrue(tree.insert("T"));
        assertTrue(tree.insert("C"));
        assertFalse(tree.insert("A"));
        assertFalse(tree.insert("G"));
        assertFalse(tree.insert("T"));
        assertFalse(tree.insert("C"));
        assertTrue(tree.insert("TT"));
        assertTrue(tree.insert("AA"));
        assertTrue(tree.insert("CC"));
        assertTrue(tree.insert("CA"));
        assertTrue(tree.insert("CG"));
        assertTrue(tree.insert("CT"));
        assertTrue(tree.insert("CAA"));
        assertTrue(tree.insert("AAAAA"));
        assertTrue(tree.insert("AAAAAAAAAAA"));
        assertTrue(tree.insert("AAC"));
        assertTrue(tree.insert("AACA"));
        assertTrue(tree.insert("GG"));
        assertTrue(tree.insert("TTTTT"));
        assertTrue(tree.insert("TTTTG"));
        assertTrue(tree.remove("TTTTG"));
        assertTrue(tree.remove("G"));
        assertTrue(tree.remove("TTTTT"));
        assertTrue(tree.remove("T"));
        assertTrue(tree.remove("AAAAA"));
        assertTrue(tree.remove("AAAA"));
        assertTrue(tree.remove("AACA"));
        Node tempRoot = tree.getRoot();
        TreeTraverse traverserTemp = new TreeTraverse(tempRoot);
        traverserTemp.printLength();
        System.out.println("******* test insert long() End *****");
    }


    /**
     * test insert in complex case
     */
    public void testInsertSyntax() {
        System.out.println("******* test insert syntax() Start *****");
        Tree treeSync = new Tree();
        assertTrue(treeSync.insert("A"));
        assertFalse(treeSync.insert("A"));
        TreeTraverse traverserSync = new TreeTraverse(treeSync.getRoot());
        traverserSync.printTree();
        System.out.println("******* test insert syntax() End *****");
    }
}
