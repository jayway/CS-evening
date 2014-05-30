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
    private static Integer[] convertNodesToIntArray(Node node) {
        Node first = getFirst(node);
        ArrayList<Integer> arrayPath = new ArrayList<Integer>();
        Node tmp = first.next;
        arrayPath.add(first.value);
        while (tmp != null) {
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
        l += arcs[path.length - 1][0];
        return l;
    }

    /**
     * Check that the path contains every index exactly once.
     *
     * @param path the path to check
     * @return the outcome of the check
     */
    public static boolean isPathValid(Node node) {
        Node first = getFirst(node);
        Integer[] intArray = convertNodesToIntArray(first);
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

    public static Node getFirst(Node node) {
        while (node.prev != null) {
            node = node.prev;
        }
        return node;
    }

    /**
     * Prints the path.
     */
    public static void printPath(Node node) {
        Node first = getFirst(node);

        System.out.print("PATH: ");
        while (first != null) {
            System.out.print(first.value + ", ");
            first = first.next;
        }
        System.out.println();
    }

}
