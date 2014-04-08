
package se.kjellstrand.bst;

public class Node {
    private int key;

    private Node left;

    private Node right;

    private Node parent;

    public Node(int key) {
        setKey(key);
    }

    public int size() {
        int ls = 0;
        int rs = 0;
        if (getLeft() != null) {
            ls = getLeft().size();
        }
        if (getRight() != null) {
            rs = getRight().size();
        }
        return 1 + rs + ls;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int k) {
        this.key = k;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node l) {
        this.left = l;
        if (this.left != null) {
            this.left.setParent(this);
        }
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node r) {
        this.right = r;
        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node p) {
        this.parent = p;
    }

}
