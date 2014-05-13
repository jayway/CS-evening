public class SkipList {
	QNode top;
	int height = 1;

	public SkipList() {
		top = new QNode(Integer.MIN_VALUE);
		top.right = new QNode(Integer.MAX_VALUE);
	}

	public void insert(int value) {
		int coinFlips = flipCoins();
		System.out.println("find: " + value + ", coinFlips: " + coinFlips);
		find(top, value, coinFlips, height);
	}

	public int flipCoins() {
		int coinFlips = 1;
		while (Math.random() > 0.5f) {
			coinFlips++;
		}
		while (coinFlips >= height) {
			QNode node = new QNode(Integer.MIN_VALUE);
			node.right = new QNode(Integer.MAX_VALUE);
			node.right.left = node;
			node.down = top;
			top.up = node;
			top = node;
			height++;
		}
		return coinFlips;
	}

	private void find(QNode node, int value, int coinFlips, int currentDepth) {
		if (value < node.value) {
			if (currentDepth <= coinFlips && currentDepth > 0) {
				insertToTheLeft(node, value);
				System.out.println("insert and find down");
				find(node.down, value, coinFlips, --currentDepth);
			} else {
				System.out.println("find down");
				find(node.down, value, coinFlips, --currentDepth);
			}
		} else {
			System.out.println("find right");
			find(node.right, value, coinFlips, currentDepth);
		}
	}

	private void insertToTheLeft(QNode node, int value) {
		QNode tmp = new QNode(value);
		tmp.left = node.left;
		tmp.right = node;
		tmp.left.right = node;
		tmp.right.left = node;
		tmp.value = value;
	}

	public void print() {
		System.out.println("-- SkipList --");
		QNode tmp = top;
		while (tmp.down != null) {
			tmp = tmp.down;
			System.out.println("mode down");
		}
		while (tmp.up != null) {
			QNode tmpr = tmp;
			while (tmpr != null) {
				System.out.print(tmpr.value + "   ");
				tmpr = tmpr.right;
			}
			System.out.println();
			tmp = tmp.up;
		}

	}

}
