public class QuadNode<K extends Comparable<K>,V> {
    private QuadNode<K,V>
            up,
            down,
            next,
            previous;
    private K key;
    private V value;

    public QuadNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void insert(K key, V value, QuadNode<K, V> up) {

    }

    public void setPrevious(QuadNode qn) {
        previous = qn;
    }

    public void setNext(QuadNode qn) {
        next = qn;
    }

    public void setDown(QuadNode qn) {
        down = qn;
    }

    public void setUp(QuadNode qn) {
        up = qn;
    }

    public QuadNode getUp() {
        return up;
    }
}
