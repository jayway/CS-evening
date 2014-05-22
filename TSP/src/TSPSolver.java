public class TSPSolver {

    public static void main(String[] args) {

        int w = 9;
        int h = 9;
        int size = 4;

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
        Node next = new Node();
        Node current = null;
        Node prev = null;
        for (int i = 0; i < size; i++) {
            prev = current;
            current = next;
            next = new Node();

            current.next = next;
            current.value = i;
            current.prev = prev;
            System.out.println("current " + current.value + ", " + current.prev + ", " + current
                    + ", " + current.next);
        }
        current.next = null;

        long length = TSPTools.getPathLength(distanceArray, current);

        boolean valid = TSPTools.isPathValid(current);

        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid"));

        System.out.println("current" + current + " " + current.prev);

        TSPTools.printPath(current);
    }

}
