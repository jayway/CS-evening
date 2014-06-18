import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPTools {

    /**
     * @param arcs
     * @param path
     */
    public static void checkPath(int[][] arcs, int[] path) {
        long length = TSPTools.getPathLength(arcs, path);
        boolean valid = TSPTools.isPathValid(path);
        System.out.println("This TSP path is " + length + " km long and is " + (valid ? "valid" : "invalid") + ".");
        TSPTools.printPath(path);
    }

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
        l += arcs[path[path.length - 1]][path[0]];
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
     * @param path
     * @param reverseStart
     * @param reverseEnd
     */
    public static void reversSubSectionOfArray(int[] path, int reverseStart, int reverseEnd) {
        if (reverseEnd < reverseStart) {
            int t = reverseEnd;
            reverseEnd = reverseStart;
            reverseStart = t;
        }
        int d2 = (reverseEnd - reverseStart) / 2;
        // System.out.println("reverseStart, " + reverseStart + " reverseEnd " +
        // reverseEnd);
        for (int i = 0; i <= d2; i++) {
            // System.out.println(" " + (reverseStart + i) + "  " + (reverseEnd
            // - i));
            int t = path[reverseStart + i];
            path[reverseStart + i] = path[reverseEnd - 1];
            path[reverseEnd - 1] = t;
        }
    }

    /**
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
        System.out.print("Path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + ", ");
        }
        System.out.println();
    }

    public static void printArcs(int size, int[][] distanceArray) {
        System.out.println(" - Distance array - ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + distanceArray[i][j]);
            }
            System.out.println();
        }
    }
}
