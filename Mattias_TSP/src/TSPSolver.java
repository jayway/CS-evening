import domain.City;
import domain.TspPath;

import java.io.File;
import java.util.List;

public class TSPSolver {

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        int[] data = TSPTools.readGraphFromCVSFile("../nodegen/1000_locations.csv");

        List<City> cityList = TSPTools.getCityList(data);

        TspPath tspPath = new TspPath(cityList);

        tspPath.printPath();


        long minPathLength = Long.MAX_VALUE;
        int startNode = -1;
        int nbrOfNodes = 1000;
        for (int a = 0; a < nbrOfNodes; a++) {
            long tempPathLength = TSPTools.getShortestPathFromOneNode(cityList, a).getPathLength();
            if (tempPathLength < minPathLength) {
                startNode = a;
                minPathLength = tempPathLength;
            }
        }

        TspPath pathCandidate = TSPTools.getShortestPathFromOneNode(cityList, startNode);
        pathCandidate.printPath();


        pathCandidate.printIntersections();

        TspPath resolvePath = null;
        do {
            if(resolvePath != null){
                pathCandidate = resolvePath;
            }

            resolvePath = TSPTools.resolveIntersection(pathCandidate);
        } while (!resolvePath.equals(pathCandidate));

        pathCandidate.printPath();

        pathCandidate.printIntersections();

        try {
            PlotterHelper.saveStringToFile(PlotterHelper.getCSVString(pathCandidate), new File("1000_nodes_result.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");

    }

}
