public class DistanceArray {

    public static int[][] getArray(int[] data, int w, int h) {
        int[][] array = new int[data.length / 2][data.length / 2];

        for (int i = 0; i < data.length / 2; i++) {
            int x1 = data[i * 2];
            int y1 = data[i * 2 + 1];
            for (int j = 0; j < data.length / 2; j++) {
                int x2 = data[j * 2];
                int y2 = data[j * 2 + 1];
                array[i][j] = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            }
        }

        return array;
    }

}
