import java.util.Random;

public class SkipList<K extends Comparable<K>,V> {
    private Head<K, V> head = new Head<K, V>();
    final static Random random = new Random();

    public void insert(K key, V value) {
        int level = 0;

        while (random.nextBoolean()) {
            level++;
        }


        head = head.maxHeight(level);
        head.insert(key, value, level);
    }

    @Override
    public String toString() {
        return head.toString();
    }

    public static void main(String[] args) {
        SkipList<Integer, String> sl = new SkipList<Integer, String>();
//        for (int i = 0; i < 10; )
        sl.insert(3, "Tjugofem");
        System.out.println(sl);
    }
}
