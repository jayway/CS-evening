package se.kjellstrand.bst;

import java.util.ArrayList;

public class BST {

    private Node root = new Node(0);

    private Node getRoot() {
        // Construct needed to handle rotations that depends on having a parent
        // for all nodes, even root.
        return root.getLeft();
    }

    public void insert(Node node) {
        if (getRoot() == null) {
            root.setLeft(node);
        } else {
            insert(getRoot(), node);
        }
    }

    public void insert(Node current, Node node) {
        if (node.getKey() < current.getKey()) {
            if (current.getLeft() == null) {
                current.setLeft(node);
                node.setParent(current);
            } else {
                insert(current.getLeft(), node);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(node);
                node.setParent(current);
            } else {
                insert(current.getRight(), node);
            }
        }
    }

    public Node find(int key) {
        return find(getRoot(), key);
    }

    private Node find(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (node.getKey() == key) {
            return node;
        } else if (node.getKey() > key) {
            return find(node.getLeft(), key);
        } else if (node.getKey() < key) {
            return find(node.getRight(), key);
        }
        return null;
    }

    public void delete(int key) {
        Node node = find(key);
        if (node == null) {
            return;
        }
        // Deleting a leaf (node with no children): Deleting a leaf is easy, as
        // we can simply remove it from the tree.
        if (node.getLeft() == null && node.getRight() == null) {
            Node parent = node.getParent();
            if (parent.getLeft() != null && parent.getLeft().equals(node)) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else
        // Deleting a node with one child: Remove the node and replace it
        // with its child.
        if (node.getLeft() != null ^ node.getRight() != null) {
            Node parent = node.getParent();
            Node child = node.getLeft();
            if (child == null) {
                child = node.getRight();
            }
            if (parent.getLeft() != null && parent.getLeft().equals(node)) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else
        // Deleting a node with two children: Call the node to be deleted N. Do
        // not delete N. Instead, choose either its in-order successor node or
        // its in-order predecessor node, R. Replace the value of N with the
        // value of R, then delete R.
        {
            Node replace = findInOrderSuccessor(node);
            int replaceKey = replace.getKey();
            delete(replace.getKey());
            node.setKey(replaceKey);
        }
    }

    private Node findInOrderSuccessor(Node node) {
        if (node.getRight() != null) {
            Node replace = node.getRight();
            while (replace.getLeft() != null) {
                replace = replace.getLeft();
            }
            return replace;
        } else {
            return null;
        }
    }

    public int size() {
        return getRoot().size();
    }

    public void rotateLeft(Node a) {
        // -----a-----
        // ---o---c---
        // --o-o-b-o--

        Node p = a.getParent();
        Node c = a.getRight();

        Node b = null;
        if (c != null) {
            b = c.getLeft();
        }

        // a have no children, no rotation possible
        if (c == null && b == null) {
            return;
        }

        if (a == p.getLeft()) {
            p.setLeft(c);
        } else {
            p.setRight(c);
        }

        a.setRight(b);
        if (c != null) {
            c.setLeft(a);
        }
    }

    public ArrayList<Integer> getInOrderListOfKeys() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        getInOrderListOfKeys(list, getRoot());
        return list;
    }

    private void getInOrderListOfKeys(ArrayList<Integer> list, Node node) {
        if (node.getLeft() != null) {
            getInOrderListOfKeys(list, node.getLeft());
        }
        list.add(node.getKey());
        if (node.getRight() != null) {
            getInOrderListOfKeys(list, node.getRight());
        }
    }

    public ArrayList<Integer> getPreOrderListOfKeys() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        getPreOrderListOfKeys(list, getRoot());
        return list;
    }

    private void getPreOrderListOfKeys(ArrayList<Integer> list, Node node) {
        list.add(node.getKey());

        if (node.getLeft() != null) {
            getPreOrderListOfKeys(list, node.getLeft());
        }
        if (node.getRight() != null) {
            getPreOrderListOfKeys(list, node.getRight());
        }
    }

    public ArrayList<Integer> getPostOrderListOfKeys() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        getPostOrderListOfKeys(list, getRoot());
        return list;
    }

    private void getPostOrderListOfKeys(ArrayList<Integer> list, Node node) {
        if (node.getLeft() != null) {
            getPostOrderListOfKeys(list, node.getLeft());
        }
        if (node.getRight() != null) {
            getPostOrderListOfKeys(list, node.getRight());
        }

        list.add(node.getKey());
    }

}
