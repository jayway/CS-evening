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

        for (int n = 0; n < l; n++) {
            for (int i = 0; i < l; i++) {
                int pna = path[(n - 1 + l) % l];
                int pnb = path[n];
                int pnc = path[(n + 1) % l];
                int pib = path[i];
                int pic = path[(i + 1) % l];
                int distOrg = arcs[pna][pnb] + arcs[pnb][pnc] + arcs[pib][pic];
                int distNew = arcs[pnb][pib] + arcs[pnb][pic] + arcs[pna][pnc];
                if (distOrg > distNew) {
                    if (n < i) {
                        int v = path[n];
                        for (int j = n; j < i; j++) {
                            path[j] = path[j + 1];
                        }
                        path[i] = v;
                    } else if (n > i) {
                        int v = path[n];
                        for (int j = n; j > i + 1; j--) {
                            path[j] = path[j - 1];
                        }
                        path[i + 1] = v;
                    }
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
