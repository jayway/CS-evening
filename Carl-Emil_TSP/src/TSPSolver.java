public class TSPSolver {

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        int[] data = TSPTools.readGraphFromCVSFile("../nodegen/10_locations.csv");

        int size = data.length / 2;

        System.out.println("size: " + size);

        int[][] arcs = Arcs.getArray(data, size, size);

        // TSPTools.printArcs(size, arcs);

        int[] path = new int[size];
        TSPTools.getRandimozedStartPath(path, System.currentTimeMillis());

        long last = Integer.MAX_VALUE - 1;
        long best = Integer.MAX_VALUE;
        while (last < best) {
            best = last;
            last = tryRemoveAndInsert(arcs, path);
            long x = removeXarcs(path, arcs);
            last = last > x ? x : last;
            System.out.println("length: " + last);
        }

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

    private static long removeXarcs(int[] path, int[][] arcs) {
        int l = path.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 2; j < l; j++) {
                int a = i;
                int d = (i + 1) % l;
                int b = j;
                int c = (j + 1) % l;
                int a1 = arcs[path[a]][path[d]] + arcs[path[b]][path[c]];
                int a2 = arcs[path[a]][path[b]] + arcs[path[d]][path[c]];
                if (a1 > a2) {
                    TSPTools.reversSubSectionOfArray(path, d, b);
                }
            }
        }
        return TSPTools.getPathLength(arcs, path);
    }

}
