public class TSPSolver {

    public static void main(String[] args) {
        int[][] arcs = Data.CITY_DISTANCES;
        int[] path = new int[arcs[0].length];

        // Create a valid but very naive solution by
        // setting 0..n to be our path.
        for (int i = 0; i < path.length; i++) {
            path[i] = i;
        }

        long length = TSPValidator.getPathLength(arcs, path);

        boolean valid = TSPValidator.isPathValid(path);

        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid"));

    }

}
