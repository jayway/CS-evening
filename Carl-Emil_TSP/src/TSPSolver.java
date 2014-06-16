public class TSPSolver {

	public static void main(String[] args) {

		// int[] data = RandomData.getRandomData(size, w, h, 0);
		int[] data = TSPTools.readGraphFromCVSFile("../nodegen/10_locations.csv");

		int size = data.length / 2;

		System.out.println("size= " + size);

		int[][] arcs = Arcs.getArray(data, size, size);

		TSPTools.printArcs(size, arcs);

		int[] path = new int[size];

		for (int i = 0; i < size; i++) {
			path[i] = i;
		}
		boolean improved = true;
		long best = Integer.MAX_VALUE;
		while (improved) {
			long last = tryRemoveAndInsert(arcs, path);
			improved = last < best;
		}
		// removeXarcs(path, arcs);

		TSPTools.checkPath(arcs, path);
	}

	private static long tryRemoveAndInsert(int[][] arcs, int[] path) {

		// for (int n = 1; n < path.length - 1; n++) {
		// for (int i = 1; i < path.length - 1; i++) {
		// System.out.println("n: " + n + ", i: " + i);
		int n = 1, i = 5;
		int d1 = arcs[n][n - 1] + arcs[n][n + 1] + arcs[i][i + 1];
		int d2 = arcs[n][i] + arcs[n][i + 1] + arcs[n - 1][n + 1];
		if (d1 > d2) {
			if (n < i) {
				int v = path[n];
				System.out.println("n " + n + ", i:" + i + ", d1: " + d1 + ", d2: " + d2);
				for (int j = n; j < i; j++) {
					path[j] = path[j + 1];
					TSPTools.printPath(path);
				}
				path[i] = v;
				TSPTools.printPath(path);
				TSPTools.checkPath(arcs, path);
				System.out.println("-----------");
			} else {

			}
			// break;
		}
		// }
		// }
		return TSPTools.getPathLength(arcs, path);
	}

	private static void removeXarcs(int[] path, int[][] arcs) {
		for (int i = 0; i < path.length; i++) {
			int a = path[i];
			int b = path[i + 1];
			for (int j = i + 2; j < path.length; j++) {
				int c = path[j];
				int d = path[j + 1];
				int a1 = arcs[a][b] + arcs[c][d];
				int a2 = arcs[a][d] + arcs[b][c];
				if (a1 > a2) {
					TSPTools.reversSubSectionOfArray(path, i, j + 1);
				}
			}
		}
	}

}
