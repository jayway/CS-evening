public class Head<K extends Comparable<K>, V> {
    public int level;
    private QuadNode<K, V> next;
    private Head<K, V>
        up;
    private Head<K, V> down;

    public Head() {
        level = 0;
    }

    public Head(int level) {
        this.level = level;
    }

    public void insert(K key, V value, int level) {
        if (next == null) {
            if (this.level <= level) {
                next = new QuadNode<K, V>(key, value);

                if (this.level < level) {
                    next.setUp(up.next);
                }
            }

            if (down != null) {
                down.insert(key, value, level);
            }
        } else {
            if (next.getKey().compareTo(key) < 0) {
                QuadNode<K, V> qn = new QuadNode<K, V>(key, value);
                qn.setNext(next);
                next.setPrevious(qn);
            } else if (next.getKey().compareTo(key) > 0) {

            } else {
                throw new IllegalArgumentException("Duplicate key insertion!");
            }
        }
    }

    private Head<K, V> getAbsUp() {
        Head<K, V> head = this;

        while (head.up != null) {
            head = head.up;
        }

        return head;
    }

    private Head<K, V> getAbsDown() {
        Head<K, V> head = this;

        while (head.down != null) {
            head = head.down;
        }

        return head;
    }

    public Head<K, V> maxHeight(int i) {
        if (i < 0) throw new IllegalArgumentException("Can't increase with negative value");

        Head<K, V> head = getAbsUp();

        for (int j = head.level; j != i; j++) {
            head.up = new Head<K, V>(head.level + 1);
            head.up.down = head;
            head = head.up;
        }

        return head;
    }

    public Head<K, V> trim() {
        Head<K, V> head = getAbsUp();

        while (head.next != null && head.down != null) {
            head = head.down;
            head.up = null;
        }

        return head;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Head head = getAbsDown(); head != null; head = head.up) {
            sb.append("H");
        }

        sb.append("\n");

        for (Head bottom = getAbsDown(); bottom != null; bottom = bottom.up) {
            for (QuadNode qn = bottom.next; qn != null; qn = qn.getUp()) {
                sb.append(qn.getKey());
            }
        }

        sb.append("\n");

        return sb.toString();
    }
}
