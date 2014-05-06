public class TSPSolver {

    public static void main(String[] args) {
        int[][] arcs = Data.CITY_DISTANCES;
        int[] path = new int[arcs[0].length];

        for (int i = 0; i < path.length; i++) {
            path[i] = i;
        }

        long length = TSPValidator.getPathLength(arcs, path);

        boolean valid = TSPValidator.isPathValid(arcs, path);

        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid"));

    }

}
