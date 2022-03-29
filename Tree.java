
/**
 * The DNA tree structure
 * 
 * @author Wenjun Han
 * @version 2022.2.28
 *
 */
public class Tree {

    private Node root;
    private FlyWeight flyweight;
    private Node temp; // for some urgent use

    /**
     * The constructor of the tree
     */
    public Tree() {
        flyweight = new FlyWeight(); // default at level 0
        temp = flyweight;
        root = flyweight;
        root.setLevel(0);
    }


    /**
     * clear the tree
     */
    public void clear() {
        flyweight = new FlyWeight(); // default at level 0
        temp = flyweight;
        root = flyweight;
        root.setLevel(0);
    }


    /**
     * Check if the tree is empty
     * 
     * @return True if the tree is empty, false if not
     */
    public boolean isEmpty() {
        return (root instanceof FlyWeight);
    }


    /**
     * Insert content to the DNA tree
     * 
     * @param data
     *            The data that we want to insert to the DNA tree
     * @return True if insert successfully, false if not
     * @throws IllegalArgumentException
     *             Throws when the data is null
     */
    public boolean insert(String data) throws IllegalArgumentException {
        if (data.equals("")) {
            throw new IllegalArgumentException();
        }
        boolean insertFlag = false;
        if (isEmpty()) {
            root = new Leaf(data, 0);
            insertFlag = true;
        }
        // leaf node with value
        else if (root.isLeaf()) {
            Leaf tempRoot = (Leaf)root;
            String oldData = tempRoot.getData();
            if (oldData.equals(data)) {
                insertFlag = false;
            }
            else {
                root = new InternalNode(0);
                initializeInternal((InternalNode)root);
                insert(root, oldData, 0, null, insertFlag);
                insertFlag = insert(root, data, 0, null, insertFlag);
            }
        }
        // internal node
        else {
            insertFlag = insert(root, data, 0, null, insertFlag);
        }
        return insertFlag;
    }


    /**
     * Initialize Internal node
     * 
     * @param node
     *            The internal node
     */
    private void initializeInternal(InternalNode node) {
        node.setA(flyweight);
        node.setC(flyweight);
        node.setG(flyweight);
        node.setT(flyweight);
        node.setD(flyweight);
    }


    /**
     * A helper method of insert method
     * 
     * @param root
     *            The node that we traverse
     * @param data
     *            The data to put into the tree
     * @param idx
     *            The index of string
     * @param parent
     *            parent node of current node
     * @param insertFlag
     *            Pass the insert flag to the recursive calls
     * @return True if insert, false if already exists
     */
    private boolean insert(
        Node currentRoot,
        String data,
        int idx,
        Node parent,
        boolean insertFlag) {
        if (currentRoot instanceof Leaf) {
            // data already exists
            Leaf endNode = (Leaf)currentRoot;
            if (checkExist(endNode, data)) {
                return false;
            }
            // create internal node to replace leaf, prefix case
            else {
                Leaf tempRoot = (Leaf)currentRoot;
                String tempData = tempRoot.getData();
                currentRoot = new InternalNode(currentRoot.getLevel());
                initializeInternal((InternalNode)currentRoot);
                // setBranchInternal(label, (InternalNode)parent,
                setBranchInternal(data.charAt(idx - 1), (InternalNode)parent,
                    (InternalNode)currentRoot);
                insert(tempData);
                insert(data);
                insertFlag = true;
                return insertFlag;
            }

        }
        else if (currentRoot instanceof FlyWeight) {
            setBranchLeaf(data.charAt(idx - 1), (InternalNode)parent, data,
                idx);
            insertFlag = true;
            return insertFlag;
        }
        else {
            // check what is the next node
            if (idx == data.length()) {
                InternalNode prefixParent = (InternalNode)currentRoot;
                if (prefixParent.getD() instanceof FlyWeight) {
                    prefixParent.setD(new Leaf(data, idx + 1));
                    insertFlag = true;
                    return insertFlag;
                }
                else {
                    return false;
                }
            }
            char label = data.charAt(idx);
            Node target = findBranch(label, (InternalNode)currentRoot);
            insertFlag = insert(target, data, idx + 1, currentRoot, insertFlag);
        }
        return insertFlag;
    }


    /**
     * Set and insert leaf node to the internal node
     * 
     * @param label
     *            The character of the string
     * @param node
     *            The internal node
     * @param data
     *            The data to store
     * @param idx
     *            The index of the data
     */
    private void setBranchInternal(
        char label,
        InternalNode node,
        Node nextNode) {
        if (label == 'A') {
            node.setA(nextNode);
        }
        else if (label == 'C') {
            node.setC(nextNode);
        }
        else if (label == 'G') {
            node.setG(nextNode);
        }
        else {
            node.setT(nextNode);
        }
    }


    /**
     * Set and insert leaf node to the internal node
     * 
     * @param label
     *            The character of the string
     * @param node
     *            The internal node
     * @param data
     *            The data to store
     * @param idx
     *            The index of the data
     */
    private void setBranchLeaf(
        char label,
        InternalNode node,
        String data,
        int idx) {
        if (label == 'A') {
            node.setA(new Leaf(data, idx));
        }
        else if (label == 'C') {
            node.setC(new Leaf(data, idx));
        }
        else if (label == 'G') {
            node.setG(new Leaf(data, idx));
        }
        else {
            node.setT(new Leaf(data, idx));
        }
    }


    /**
     * Find the branch based on the character in the string
     * 
     * @param label
     *            The character
     * @param node
     *            The internal node that have 5 branches
     * @return The corresponding branch of the character
     */
    private Node findBranch(char label, InternalNode node) {
        if (label == 'A') {
            return node.getA();
        }
        else if (label == 'C') {
            return node.getC();
        }
        else if (label == 'G') {
            return node.getG();
        }
        else {
            return node.getT();
        }
    }


    /**
     * Check if current node contains this data
     * 
     * @param node
     *            The current node
     * @param data
     *            The data we check existence
     * @return True if exists, false if not
     */
    private boolean checkExist(Leaf node, String data) {

        return node.getData().equals(data);
    }


    /**
     * Get the root of the tree
     * 
     * @return Root of the node
     */
    public Node getRoot() {
        return root;
    }


    /**
     * Remove the string in the tree
     * 
     * @param data
     *            The string that we need to remove
     * @return True if the string is removed, false if the string is not found
     * @throws IllegalArgumentException
     *             Throws if the argument is invalid
     */
    public boolean remove(String data) {
        if (data.equals("")) {
            throw new IllegalArgumentException();
        }
        boolean removeFlag = false;
        if (isEmpty()) {
            return false;
        }
        else if (root.isLeaf()) {
            Leaf currentRoot = (Leaf)root;
            if (currentRoot.getData().equals(data)) {
                root = flyweight;
                return true;
            }
            else {
                return false;
            }
        }
        // internal node
        else {
            removeFlag = remove(root, data, 0, null, removeFlag);
            if (!(temp instanceof FlyWeight)) {
                root = temp;
                temp = flyweight;
            }
        }
        return removeFlag;
    }


    /**
     * Set the current to a fly weight node
     * 
     * @param root
     *            The current node
     * @param parent
     *            The parent node of current node
     */
    private void setFlyWeight(Node currentRoot, InternalNode parent) {
        if (currentRoot == parent.getA()) {
            parent.setA(flyweight);
        }
        if (currentRoot == parent.getC()) {
            parent.setC(flyweight);
        }
        if (currentRoot == parent.getG()) {
            parent.setG(flyweight);
        }
        if (currentRoot == parent.getT()) {
            parent.setT(flyweight);
        }
    }


    /**
     * Remove this branch and connect to the next level node
     * 
     * @param root
     *            The current node
     * @param parent
     *            The parent node of the current node
     * @param target
     *            The next level node to be connected to the parent node
     */
    private void removeBranch(
        Node currentRoot,
        InternalNode parent,
        Node target) {
        target.setLevel(target.getLevel() - 1);
        if (currentRoot == parent.getA()) {
            parent.setA(target);
        }
        if (currentRoot == parent.getC()) {
            parent.setC(target);
        }
        if (currentRoot == parent.getG()) {
            parent.setG(target);
        }
        if (currentRoot == parent.getT()) {
            parent.setT(target);
        }
    }


    /**
     * Helper method of remove
     * 
     * @param root
     *            The current node
     * @param data
     *            The data to be removed
     * @param idx
     *            The index of the string data
     * @param parent
     *            Parent node of the current node
     * @param removeFlag
     *            Pass the remove flag in recursion call
     * @return True if remove, false if not found
     */
    private boolean remove(
        Node currentRoot,
        String data,
        int idx,
        Node parent,
        boolean removeFlag) {

        if (currentRoot instanceof FlyWeight) {
            removeFlag = false;
            return removeFlag;
        }
        // simply remove the leaf
        else if (currentRoot instanceof Leaf) {
            if (checkExist((Leaf)currentRoot, data)) {
                setFlyWeight(currentRoot, (InternalNode)parent);
                removeFlag = true;
                return removeFlag;
            }
            else {
                return false;
            }
        }
        // Internal node
        else {
            InternalNode currentInternal = (InternalNode)currentRoot;
            if (idx == data.length()) {
                if (currentInternal.getD() instanceof FlyWeight) {
                    return false;
                }
                else {
                    currentInternal.setD(flyweight);
                    removeFlag = true;
                    // check if the node has any leaf or internal node
                    checkRemove(currentRoot, (InternalNode)parent, 0, flyweight,
                        0);
                    return removeFlag;
                }
            }
            char label = data.charAt(idx);
            Node nextNode = findBranch(label, currentInternal);
            removeFlag = remove(nextNode, data, idx + 1, currentRoot,
                removeFlag);
            // check if the node has any leaf or internal node
            checkRemove(currentRoot, (InternalNode)parent, 0, flyweight, 0);
        }
        return removeFlag;
    }


    /**
     * Helper method of remove method, check if we need to remove the upper
     * level node
     * 
     * @param root
     *            The root of the node, original should be internal
     * @param parent
     *            The parent of the root
     * @param num
     *            Number of fly weight within the root
     * @param remain
     *            The remain node inside the subtree
     */
    private int checkRemove(
        Node currentRoot,
        Node parent,
        int num,
        Node remain,
        int round) {

        if (currentRoot instanceof FlyWeight) {
            num++;
            return num;
        }
        else if (currentRoot instanceof InternalNode && round == 0) {
            InternalNode node = (InternalNode)currentRoot;
            num = checkRemove(node.getA(), parent, num, remain, round + 1);
            num = checkRemove(node.getC(), parent, num, remain, round + 1);
            num = checkRemove(node.getG(), parent, num, remain, round + 1);
            num = checkRemove(node.getT(), parent, num, remain, round + 1);
            num = checkRemove(node.getD(), parent, num, remain, round + 1);
            if (num == 4) {
                if (!(node.getA() instanceof FlyWeight)) {
                    remain = node.getA();
                }
                if (!(node.getC() instanceof FlyWeight)) {
                    remain = node.getC();
                }
                if (!(node.getG() instanceof FlyWeight)) {
                    remain = node.getG();
                }
                if (!(node.getT() instanceof FlyWeight)) {
                    remain = node.getT();
                }
                if (!(node.getD() instanceof FlyWeight)) {
                    remain = node.getD();
                }
                if (remain instanceof Leaf) {
                    if (parent != null) {
                        removeBranch(currentRoot, (InternalNode)parent, remain);
                    }
                    else {
                        temp = remain;
                    }
                }
            }
            else if (node.getD() instanceof Leaf) {
                Leaf dollarTemp = (Leaf)node.getD();
                String tempData = dollarTemp.getData();
                node.setD(flyweight);
                insert(tempData);
            }
        }
        return num;
    }
}
