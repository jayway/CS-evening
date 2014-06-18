public class TSPSolver {

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        int[] data = TSPTools.readGraphFromCVSFile("../nodegen/100_locations.csv");

        int size = data.length / 2;

        System.out.println("size= " + size);

        int[][] arcs = Arcs.getArray(data, size, size);

        // TSPTools.printArcs(size, arcs);

        int[] path = new int[size];

        for (int i = 0; i < size; i++) {
            path[i] = i;
        }
        long last = 0;
        long best = Integer.MAX_VALUE;
        while (last < best) {
            last = tryRemoveAndInsert(arcs, path);
            best = last;
            System.out.println("length: " + last);
        }
        // removeXarcs(path, arcs);

        TSPTools.checkPath(arcs, path);
    }

    private static long tryRemoveAndInsert(int[][] arcs, int[] path) {
        int l = path.length;

        for (int n = 1; n < path.length - 1; n++) {
            for (int i = 1; i < path.length - 1; i++) {
                // System.out.println("n: " + n + ", i: " + i);
                // int n = 1, i = 5;
                int pna = path[n - 1];
                int pnb = path[n];
                int pnc = path[n + 1];
                int pia = path[i - 1];
                int pib = path[i];
                int pic = path[i + 1];
                int distOrg = arcs[pna][pnb] + arcs[pnb][pnc] + arcs[pib][pic];
                int distNew = arcs[pnb][pib] + arcs[pnb][pic] + arcs[pna][pnc];
                if (distOrg > distNew) {
                    if (n < i) {
                        int v = path[n];
                        System.out.println("n " + n + ", i:" + i + ", distOrg: " + distOrg + ", distNew: " + distNew);
                        System.out.println("length: " + TSPTools.getPathLength(arcs, path));
                        TSPTools.printPath(path);
                        for (int j = n; j < i; j++) {
                            path[j] = path[j + 1];
                            TSPTools.printPath(path);
                        }
                        path[i] = v;
                        TSPTools.printPath(path);
                        System.out.println("length: " + TSPTools.getPathLength(arcs, path));
                        System.out.println("-------------");
                    } else if (n > i) {
                        int v = path[n];
//                        System.out.println("n " + n + ", i:" + i + ", distOrg: " + distOrg + ", distNew: " + distNew);
//                        System.out.println("length: " + TSPTools.getPathLength(arcs, path));
//                        TSPTools.printPath(path);
//                        for (int j = n; j > i; j--) {
//                            path[j] = path[j - 1];
//                            TSPTools.printPath(path);
//                        }
//                        path[i] = v;
//                        TSPTools.printPath(path);
//                        System.out.println("length: " + TSPTools.getPathLength(arcs, path));
//                        System.out.println("-------------");
                    }
//                    if (n < i) {
//                        n 1, i:5, distOrg: 192, distNew: 95
//                        length: 493
//                        Path: 0, 2, 3, 4, 1, 5, 6, 7, 8, 9,
//                        Path: 0, 3, 3, 4, 1, 5, 6, 7, 8, 9,
//                        Path: 0, 3, 4, 4, 1, 5, 6, 7, 8, 9,
//                        Path: 0, 3, 4, 1, 1, 5, 6, 7, 8, 9,
//                        Path: 0, 3, 4, 1, 5, 5, 6, 7, 8, 9,
//                        Path: 0, 3, 4, 1, 5, 2, 6, 7, 8, 9,
//                        length: 396
//                        -------------
//                } else if (n > i) {
//                    n 7, i:2, distOrg: 160, distNew: 130
//                    length: 492
//                    Path: 0, 7, 5, 6, 4, 3, 2, 1, 8, 9,
//                    Path: 0, 7, 5, 6, 4, 3, 2, 2, 8, 9,
//                    Path: 0, 7, 5, 6, 4, 3, 3, 2, 8, 9,
//                    Path: 0, 7, 5, 6, 4, 4, 3, 2, 8, 9,
//                    Path: 0, 7, 5, 6, 6, 4, 3, 2, 8, 9,
//                    Path: 0, 7, 5, 5, 6, 4, 3, 2, 8, 9,
//                    Path: 0, 7, 1, 5, 6, 4, 3, 2, 8, 9,
//                    length: 465
                    // break;
                }
            }
        }
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
