
public class TSPSolver {

    public static void main(String[] args){
        int[][] arcs = Data.CITY_DISTANCES;
        int[] path = new int[arcs[0].length];

        for(int i=0;i<path.length;i++){
            path[i]=i;
        }

        long length = getPathLength(arcs, path);

        System.out.println("This TSP path is " + length + " km long.");

    }

    private static long getPathLength(int[][] arcs, int[] path) {
        long l=0;

        for(int i=1;i<path.length;i++){
            l += arcs[path[i-1]][path[i]];
        }

        return l;
    }
}
