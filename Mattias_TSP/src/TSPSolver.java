import java.io.File;

public class TSPSolver {

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        int[] data = TSPTools.readGraphFromCVSFile("../nodegen/1000_locations.csv");

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
        int nbrOfNodes = 1000;
        for(int a = 0; a < nbrOfNodes;a++){
            long tempPathLength = TSPTools.getPathLength(arcs, TSPTools.getShortestPathFromOneNode(arcs, nbrOfNodes, a));
            if(tempPathLength < minPathLength){
                startNode = a;
                minPathLength = tempPathLength;
            }
        }

        int[] pathCandidate = TSPTools.getShortestPathFromOneNode(arcs, nbrOfNodes, startNode);
        TSPTools.checkPath(arcs, pathCandidate);

        try {
            PlotterHelper.saveStringToFile(PlotterHelper.getCSVString(data, pathCandidate), new File("1000_nodes_result.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");

    }

}
