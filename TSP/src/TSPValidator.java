import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPValidator {

    /**
     * Calculate the length of a path.
     *
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     * @return the length
     */
    public static long getPathLength(int[][] arcs, Node path) {
        Integer[] intArray = convertNodesToIntArray(path);
        return getPathLength(arcs, intArray);
    }

    /**
     * Calculate the length of a path.
     *
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     * @return the length
     */
    private static Integer[] convertNodesToIntArray(Node path) {
        ArrayList<Integer> arrayPath = new ArrayList<Integer>();
        Node tmp = path.next;
        arrayPath.add(path.value);
        while (tmp != path) {
            arrayPath.add(tmp.value);
            tmp = tmp.next;
        }
        Integer[] intArray = arrayPath.toArray(new Integer[0]);
        return intArray;
    }

    /**
     * Calculate the length of a path.
     *
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     * @return the length
     */
    public static long getPathLength(int[][] arcs, Integer[] path) {
        long l = 0;
        for (int i = 1; i < path.length; i++) {
            l += arcs[path[i - 1]][path[i]];
        }
        return l;
    }

    /**
     * Check that the path contains every index exactly once.
     *
     * @param path the path to check
     * @return the outcome of the check
     */
    public static boolean isPathValid(Node path) {
        Integer[] intArray = convertNodesToIntArray(path);
        return isPathValid(intArray);
    }

    /**
     * Check that the path contains every index exactly once.
     *
     * @param path the path to check
     * @return the outcome of the check
     */
    public static boolean isPathValid(Integer[] path) {
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
}
