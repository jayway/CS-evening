public class SkipList {
    QNode top;

    int height = 1;

    public SkipList() {
        top = new QNode(Integer.MIN_VALUE);
        top.right = new QNode(Integer.MAX_VALUE);
    }

    public void insert(int value) {
        int coinFlips = flipCoins();
        System.out.println("insert: " + value + ", coinFlips: " + coinFlips);
        insert(top, value, coinFlips, height);
    }

    private void insert(QNode node, int value, int coinFlips, int currentDepth) {
        System.out.println("insert value: " + value + ", node " + node);
        System.out.println("node.value: " + node.value);
        if (currentDepth > 0) {
            if (value > node.value && value < node.right.value) {
                if (currentDepth <= coinFlips) {
                    insertToTheRight(node, value);
                    System.out.println("insert value here");
                } else {
                    System.out.println("insert down");
                    insert(node.down, value, coinFlips, --currentDepth);
                }
            } else {
                System.out.println("insert right");
                insert(node.right, value, coinFlips, currentDepth);
            }
        }
    }

    private void insertToTheRight(QNode left, int value) {
        QNode tmp = new QNode(value);
        QNode right = left.right;
        tmp.right = right;
        tmp.left = left;
        right.left = tmp;
        left.right = tmp;
        tmp.value = value;
        // we have a node above us
        if(left.up!=null){
            tmp.up = left.up.right;
            left.up.right.down = tmp;
        }
    }

    public int flipCoins() {
        int coinFlips = 1;
        while (coinFlips <= 1) {// Math.random() > 0.5f) {
            coinFlips++;

            QNode node = new QNode(Integer.MIN_VALUE);
            node.right = new QNode(Integer.MAX_VALUE);
            node.right.left = node;
            node.down = top;
            top.up = node;
            top = node;

            top.right.down = top.down.right;
            top.right.down.up = top.right;

        }
        height = Math.max(height, coinFlips);

        return coinFlips;
    }

    public void print() {
        System.out.println("-- SkipList -- height: " + height);
        QNode tmp = top;
        while (tmp.down != null) {
            tmp = tmp.down;
        }
        QNode tmpr = tmp;
        QNode tmpu = tmp;
        while (tmpr.right != null) {
            while (tmpu != null) {
                System.out
                        .print((tmpr.value == Integer.MAX_VALUE ? Integer.MIN_VALUE : tmpr.value) == Integer.MIN_VALUE ? " - "
                                : tmpr.value + "  ");
                tmpu = tmpu.up;
            }
            tmpr = tmpr.right;
            tmpu = tmpr;
            System.out.println();
        }

    }

}
