
/**
 * A traverse of the DNA tree
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.3.2
 *
 */
public class TreeTraverse {

    private Node root;

    /**
     * Constructor of the tree traverse
     * 
     * @param root
     *            The root of the tree to be traversed
     */
    public TreeTraverse(Node root) {
        this.root = root;
    }


    /**
     * Print out the tree
     */
    public void printTree() {
        System.out.println("tree dump:");
        traverseTree(root, 0, 0);
    }


    /**
     * Print out the tree
     */
    public void printLength() {
        System.out.println("tree dump:");
        traverseTree(root, 0, 1);
    }


    /**
     * Print out the tree
     */
    public void printStats() {
        System.out.println("tree dump:");
        traverseTree(root, 0, 2);
    }


    /**
     * Print out the number of space before the node value
     * 
     * @param spaceNum
     *            The number of space, same as level number
     * @return space The string of spaces
     */
    private String printSpace(int spaceNum) {
        String space = "";
        for (int i = 0; i < spaceNum; i++) {
            space += " ";
            space += " ";
        }
        return space;
    }


    /**
     * Traverser of the tree
     * 
     * @param root
     *            The root of the tree
     * @param currentLevel
     *            The current level of the tree
     * @param mode
     *            Print tree: 0; Print lengths: 1; Print stats: 2;
     */
    private void traverseTree(Node currentRoot, int currentLevel, int mode) {
        if (currentRoot instanceof FlyWeight) {
            System.out.println(printSpace(currentLevel) + 'E');
        }
        else if (currentRoot instanceof Leaf) {
            Leaf leaf = (Leaf)currentRoot;
            if (mode == 0) {
                System.out.println(printSpace(currentLevel) + leaf.getData());
            }
            else if (mode == 1) {
                System.out.println(printSpace(currentLevel) + leaf.getData()
                    + " " + leaf.getData().length());
            }
            // print the statistics of the string
            else {
                System.out.println(printSpace(currentLevel) + leaf.getData()
                    + findStat(leaf.getData()));
            }
        }
        else {
            InternalNode current = (InternalNode)currentRoot;
            System.out.println(printSpace(currentLevel) + 'I');
            traverseTree(current.getA(), currentLevel + 1, mode);
            traverseTree(current.getC(), currentLevel + 1, mode);
            traverseTree(current.getG(), currentLevel + 1, mode);
            traverseTree(current.getT(), currentLevel + 1, mode);
            traverseTree(current.getD(), currentLevel + 1, mode);
        }
    }


    /**
     * Help find the statistics within the string
     * 
     * @param data
     *            The data to find the statistics
     * @return A string that presents the stat
     */
    private String findStat(String data) {
        int aNum = 0;
        int cNum = 0;
        int gNum = 0;
        int tNum = 0;
        int sum = 0;
        for (char character : data.toCharArray()) {
            if (character == 'A') {
                aNum++;
            }
            else if (character == 'C') {
                cNum++;
            }
            else if (character == 'G') {
                gNum++;
            }
            else {
                tNum++;
            }
            sum++;
        }
        String res = "";
        res = res + " A:" + String.format("%.2f", (double)aNum * 100 / sum)
            + " ";
        res = res + "C:" + String.format("%.2f", (double)cNum * 100 / sum)
            + " ";
        res = res + "G:" + String.format("%.2f", (double)gNum * 100 / sum)
            + " ";
        res = res + "T:" + String.format("%.2f", (double)tNum * 100 / sum);
        return res;
    }


    /**
     * Find the target node with given data
     * 
     * @param data
     *            The given data of the node
     * @return The target node
     */
    public Node findNode(String data) {
        return findNode(root, data, new FlyWeight());
    }


    /**
     * Find a node in the current tree
     * 
     * @param root
     *            The root of the tree
     * @param data
     *            The data of the target node
     * @param result
     *            The result node
     * @return Node The target node
     */
    private Node findNode(Node currentRoot, String data, Node result) {

        if (currentRoot instanceof Leaf) {
            Leaf leaf = (Leaf)currentRoot;
            if (leaf.getData().equals(data)) {
                result = leaf;
                return leaf;
            }
        }
        else if (currentRoot instanceof FlyWeight) {
            return result;
        }
        else {
            InternalNode current = (InternalNode)currentRoot;
            result = findNode(current.getA(), data, result);
            result = findNode(current.getC(), data, result);
            result = findNode(current.getG(), data, result);
            result = findNode(current.getT(), data, result);
            result = findNode(current.getD(), data, result);
        }
        return result;
    }


    /**
     * Search the sequence descriptor
     * 
     * @param data
     *            The data or data prefix that to search
     * @param mode
     *            The search mode: exact search: 0; prefix search: 1;
     */
    public void search(String data, int mode) {
        // -1 is not found
        int count = 0;
        boolean found = false;
        LinkedList<String> sequences = new LinkedList<String>();
        count = searchCounter(root, data, 0, count, sequences, mode);
        found = searchFlag(root, data, found, mode);
        System.out.println("# of nodes visited: " + count);
        if (found) {
            sequences.moveToEnd();
            sequences.append(null);
            sequences.moveToStart();
            while (!sequences.isAtEnd()) {
                System.out.println("sequence: " + sequences.getValue());
                sequences.next();
            }
        }
        else {
            System.out.println("no sequence found");
        }
    }


    /**
     * Search a node for the data
     * 
     * @param root
     *            Root of the tree
     * @param data
     *            The data we want to traverse
     * @param visited
     *            The number of nodes visited so far
     * @param found
     *            True if it is found, false if not
     * @return The number of visited nodes
     */
    private int searchCounter(
        Node currentRoot,
        String data,
        int index,
        int visited,
        LinkedList<String> sequences,
        int mode) {

        if (currentRoot instanceof FlyWeight) {
            visited++;
            return visited;
        }
        else if (currentRoot instanceof Leaf) {
            visited++;
            Leaf leaf = (Leaf)currentRoot;
            if (mode == 0) {
                if (leaf.getData().equals(data)) {
                    sequences.append(data);
                }

            }
            // not exact search
            else {
                sequences.append(leaf.getData());
            }
            return visited;
        }
        else {
            InternalNode current = (InternalNode)currentRoot;
            char label = ' ';
            visited++;
            // exact search
            if (mode == 0) {
                if (index == data.length()) {
                    visited = searchCounter(current.getD(), data, index + 1,
                        visited, sequences, mode);
                }
                else {
                    label = data.charAt(index);
                    if (label == 'A') {
                        visited = searchCounter(current.getA(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'C') {
                        visited = searchCounter(current.getC(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'G') {
                        visited = searchCounter(current.getG(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'T') {
                        visited = searchCounter(current.getT(), data, index + 1,
                            visited, sequences, mode);
                    }
                }
            }
            else {
                if (index < data.length()) {
                    label = data.charAt(index);
                    if (label == 'A') {
                        visited = searchCounter(current.getA(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'C') {
                        visited = searchCounter(current.getC(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'G') {
                        visited = searchCounter(current.getG(), data, index + 1,
                            visited, sequences, mode);
                    }
                    if (label == 'T') {
                        visited = searchCounter(current.getT(), data, index + 1,
                            visited, sequences, mode);
                    }
                }
                else {
                    visited = searchCounter(current.getA(), data, index + 1,
                        visited, sequences, mode);
                    visited = searchCounter(current.getC(), data, index + 1,
                        visited, sequences, mode);
                    visited = searchCounter(current.getG(), data, index + 1,
                        visited, sequences, mode);
                    visited = searchCounter(current.getT(), data, index + 1,
                        visited, sequences, mode);
                    visited = searchCounter(current.getD(), data, index + 1,
                        visited, sequences, mode);
                }
            }

        }
        return visited;
    }


    /**
     * Traverse the whole tree
     * 
     * @param root
     *            The root of the tree
     */
    private boolean searchFlag(
        Node currentRoot,
        String data,
        boolean flag,
        int mode) {
        if (currentRoot instanceof FlyWeight) {
            return flag;
        }
        else if (currentRoot instanceof Leaf) {
            Leaf leaf = (Leaf)currentRoot;
            if (data.length() > leaf.getData().length()) {
                return flag;
            }
            if (leaf.getData().equals(data) || (leaf.getData().substring(0, data
                .length()).equals(data) && mode == 1)) {
                flag = true;
                return flag;
            }
        }
        else {
            InternalNode current = (InternalNode)currentRoot;
            flag = searchFlag(current.getA(), data, flag, mode);
            flag = searchFlag(current.getC(), data, flag, mode);
            flag = searchFlag(current.getG(), data, flag, mode);
            flag = searchFlag(current.getT(), data, flag, mode);
            flag = searchFlag(current.getD(), data, flag, mode);
        }
        return flag;
    }
}
