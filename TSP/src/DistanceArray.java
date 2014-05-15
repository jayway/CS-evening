public class DistanceArray {

    public static int[][] getArray(int[] data, int w, int h) {
        int[][] array = new int[data.length / 2][data.length / 2];

        for (int i = 0; i < data.length / 2; i++) {
            for (int j = 0; j < data.length / 2; j++) {
                array[i][j] = getDistance(data, i, j);
            }
        }

        return array;
    }

    public static int getDistance(int[] data, int p1, int p2) {
        int x1 = data[p1 * 2];
        int y1 = data[p1 * 2 + 1];
        int x2 = data[p2 * 2];
        int y2 = data[p2 * 2 + 1];
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
