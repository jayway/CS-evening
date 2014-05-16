public class TSPSolver {

    public static void main(String[] args) {

        int w = 9;
        int h = 9;
        int size = 3;

        int[] data = RandomData.getRandomData(size, w, h, 0);

        int[][] distanceArray = DistanceArray.getArray(data, w, h);

        System.out.println(" - Distance array - ");
        for (int i = 0; i < data.length / 2; i++) {
            for (int j = 0; j < data.length / 2; j++) {
                System.out.print(" " + distanceArray[i][j]);
            }
            System.out.println();
        }

        // Create a valid but very naive solution by
        // setting 0..n to be our path.
        Node first = new Node();
        Node tmp = first;
        Node prev = null;
        for (int i = 0; i < size; i++) {
            tmp.value = i;
            if (prev != null) {
                tmp.prev = prev;
                prev.next = tmp;
            }
            prev = tmp;
        }
        first.prev = prev;

        long length = TSPValidator.getPathLength(distanceArray, first);

        boolean valid = TSPValidator.isPathValid(first);

        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid"));

        printPath(first);
    }

    public static void printPath(Node first) {
        Node tmp = first;
        System.out.print("PATH: ");
        while (tmp != first.prev) {
            System.out.print(tmp.value + ", ");
        }
        System.out.println();
    }

}
