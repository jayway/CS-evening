import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPValidator {

    public static long getPathLength(int[][] arcs, int[] path) {
        long l = 0;

        for (int i = 1; i < path.length; i++) {
            l += arcs[path[i - 1]][path[i]];
        }

        return l;
    }

    public static boolean isPathValid(int[][] arcs, int[] path) {
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
