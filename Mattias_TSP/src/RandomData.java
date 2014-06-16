import java.util.Random;

public class RandomData {

    public static int[] getRandomData(int size, int width, int height, int seed) {
        int[] data = new int[size * 2];

        java.util.Random r = new Random(seed);

        for (int i = 0; i < size; i++) {
            data[i * 2] = r.nextInt(height);
            data[i * 2 + 1] = r.nextInt(width);
        }

        return data;
    }
}
