public class TSPSolver {

    public static void main(String[] args) {

        int w = 9;
        int h = 9;
        int size = 10;

        int[] data = RandomData.getRandomData(size, w, h, 0);

        int[][] distanceArray = DistanceArray.getArray(data, w, h);

        System.out.println(" - Distance array - ");
        for (int i = 0; i < data.length / 2; i++) {
            System.out.println();
            for (int j = 0; j < data.length / 2; j++) {
                System.out.print(" " + distanceArray[i][j]);
            }
        }
        System.out.println();

        // Create a valid but very naive solution by
        // setting 0..n to be our path.
        int[] path = new int[size];
        for (int i = 0; i < path.length; i++) {
            path[i] = i;
        }

        long length = TSPValidator.getPathLength(distanceArray, path);

        boolean valid = TSPValidator.isPathValid(path);

        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid"));

    }

}
