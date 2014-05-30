import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPTools {

    /**
     * Calculate the length of a path.
     * 
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     * @return the length
     */
    public static long getPathLength(int[][] arcs, int[] path) {
        long l = 0;
        for (int i = 1; i < path.length; i++) {
            l += arcs[path[i - 1]][path[i]];
        }
        l += arcs[path.length - 1][0];
        return l;
    }

    /**
     * Check that the path contains every index exactly once.
     * 
     * @param path the path to check
     * @return the outcome of the check
     */
    public static boolean isPathValid(int[] path) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < path.length; i++) {
            list.add(path[i]);
        }
        Collections.sort(list);
        for (int i = 0; i < path.length; i++) {
            if (list.get(i) != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param array
     * @param reverseStart
     * @param reverseEnd
     */
    public void reversSubSectionOfArray(int[] array, int reverseStart, int reverseEnd) {

    }

    /**
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static int[] readGraphFromCVSFile(String file) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = line.split(",");
        int[] results = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            try {
                results[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return results;
    }

    public static void printPath(int[] path) {
        System.out.println(" --- PATH ---");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + ", ");
        }
        System.out.println();
    }

}
