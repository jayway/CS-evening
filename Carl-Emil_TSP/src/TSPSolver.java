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

        TSPTools.checkPath(arcs, path);
    }

}
