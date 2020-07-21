public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            RBTreeNode tree = new RBTreeNode(true, r.getItemAt(0));
            if (r.getChildrenCount() != 0) {
                tree.left = buildRedBlackTree(r.getChildAt(0));
                tree.right = buildRedBlackTree(r.getChildAt(1));
            }
            return tree;
        } else if (r.getItemCount() == 2) {
            RBTreeNode tree = new RBTreeNode(true, r.getItemAt(0));
            if (r.getChildrenCount() != 0) {
                tree.left = buildRedBlackTree(r.getChildAt(0));
                tree.right = new RBTreeNode(false, r.getItemAt(1),
                        buildRedBlackTree(r.getChildAt(1)),
                        buildRedBlackTree(r.getChildAt(2)));
            }
            return tree;
        } else {
            RBTreeNode tree = new RBTreeNode(true, r.getItemAt(1));
            if (r.getChildrenCount() != 0) {
                tree.left = new RBTreeNode(false, r.getItemAt(0),
                    buildRedBlackTree(r.getChildAt(0)),
                    buildRedBlackTree(r.getChildAt(1)));
                tree.right = new RBTreeNode(false, r.getItemAt(2),
                    buildRedBlackTree(r.getChildAt(2)),
                    buildRedBlackTree(r.getChildAt(3)));
            }
            return tree;
        }
    }

    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        return null;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        return null;
    }

    public void insert(T item) {   
        root = insert(root, item);  
        root.isBlack = true;    
    }

    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: YOUR CODE HERE
        return null;
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
