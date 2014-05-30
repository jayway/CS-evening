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

    public int[] readGraphFromCVSFile(String path) {
        return null;

    }

    public static void printPath(int[] path) {
        System.out.println(" --- PATH --- \n");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + ", ");
        }
        System.out.println();
    }

}
