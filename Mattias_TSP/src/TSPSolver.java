public class TSPSolver {

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        int[] data = TSPTools.readGraphFromCVSFile("../nodegen/10_locations.csv");

        int size = data.length / 2;

        System.out.println("size= " + size);

        int[][] arcs = Arcs.getArray(data, size, size);

        TSPTools.printArcs(size, arcs);

        int[] path = new int[size];

        for (int i = 0; i < size; i++) {
            path[i] = i;
        }

        TSPTools.checkPath(arcs, path);

//        for(int a = 0; a < 10; a++){
//            System.out.println("Closest neighbor for " + a + " is " + TSPTools.getClosestNeighborForNode(arcs, a));
//        }


        long minPathLength = Long.MAX_VALUE;
        int startNode = -1;
        for(int a = 0; a < 10;a++){
            long tempPathLength = TSPTools.getPathLength(arcs, TSPTools.getShortestPathFromOneNode(arcs, 10, a));
            if(tempPathLength < minPathLength){
                startNode = a;
                minPathLength = tempPathLength;
            }
        }

        int[] pathCandidate = TSPTools.getShortestPathFromOneNode(arcs, 10, startNode);
        TSPTools.checkPath(arcs, pathCandidate);

        System.out.println("Done!");

    }

}
