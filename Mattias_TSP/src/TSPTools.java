import domain.City;
import domain.Intersection;
import domain.TspPath;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TSPTools {

    /**
     * @param arcs
     * @param path
     */
    public static void checkPath(int[][] arcs, int[] path) {
        long length = TSPTools.getPathLength(arcs, path);
        boolean valid = TSPTools.isPathValid(path);
        System.out.println("This TSP path is " + length + " km long and is "
                + (valid ? "valid" : "invalid") + ".");
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
     * @param array
     * @param reverseStart
     * @param reverseEnd
     */
    public void reverseSubSectionOfArray(int[] array, int reverseStart, int reverseEnd) {
        while (reverseStart < reverseEnd) {
            int temp = array[reverseStart];
            array[reverseStart] = array[reverseEnd];
            array[reverseEnd] = temp;
            reverseStart++;
            reverseEnd--;
        }
    }

    public void removeTangleFromPath(int[] initialPath) {
        for (int i = 0; i < initialPath.length; i++) {
            for (int j = i + 1; j < initialPath.length; j++) {


            }
        }
    }

//    public static int[] getShortestPathFromOneNode(int[][] arcs, int size, int startNode){
//        ArrayList<Integer> tempPath = new ArrayList<Integer>();
//        HashSet<Integer> possibleNodes = new HashSet<Integer>();
//
//        for(int a=0; a<size;a++){
//            possibleNodes.add(a);
//        }
//
//        tempPath.add(startNode);
//        possibleNodes.remove(startNode);
//
//        while(possibleNodes.size() > 0){
//            int nextNode = getClosestNeighborForNode(arcs, tempPath.get(tempPath.size()-1), possibleNodes);
//            tempPath.add(nextNode);
//            possibleNodes.remove(nextNode);
//        }
//
//        int[] path = new int[size];
//        for(int a = 0; a < tempPath.size(); a++){
//            path[a] = tempPath.get(a);
//        }
//        return path;
//
//
//    }

    public static TspPath getShortestPathFromOneNode(List<City> cityList, int startNode) {
        ArrayList<Integer> tempPath = new ArrayList<Integer>();
        HashSet<Integer> possibleNodes = new HashSet<Integer>();

        for (int a = 0; a < cityList.size(); a++) {
            possibleNodes.add(a);
        }

        tempPath.add(startNode);
        possibleNodes.remove(startNode);

        while (possibleNodes.size() > 0) {
            int nextNode = getClosestNeighborForNode(cityList, tempPath.get(tempPath.size() - 1), possibleNodes);
            tempPath.add(nextNode);
            possibleNodes.remove(nextNode);
        }

        List<City> pathCityList = new ArrayList<City>();
        for (int a = 0; a < tempPath.size(); a++) {
            pathCityList.add(cityList.get(tempPath.get(a)));
        }
        return new TspPath(pathCityList);


    }

    public static int getClosestNeighborForNode(List<City> cityList, int nodeNbr, Set<Integer> validNodes) {
        int neighbourNode = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int a = 0; a < cityList.size(); a++) {
            if (validNodes.contains(a) && a != nodeNbr && cityList.get(nodeNbr).point.distance(cityList.get(a).point) < minDistance) {
                neighbourNode = a;
                minDistance = (int) cityList.get(nodeNbr).point.distance(cityList.get(a).point);
            }
        }
        return neighbourNode;
    }

//    public static int getClosestNeighborForNode(int[][] arcs, int nodeNbr, Set<Integer> validNodes){
//        int neighbourNode = -1;
//        int minDistance = Integer.MAX_VALUE;
//        for(int a = 0; a<arcs[nodeNbr].length;a++){
//           if(validNodes.contains(a) && arcs[nodeNbr][a] != 0 && arcs[nodeNbr][a] < minDistance){
//               neighbourNode = a;
//               minDistance = arcs[nodeNbr][a];
//           }
//        }
//        return neighbourNode;
//    }

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

    public static List<City> getCityList(int[] data) {
        List<City> list = new ArrayList<City>();
        for (int a = 0; a < data.length / 2; a++) {
            list.add(new City(a, new Point2D.Float(data[2 * a], data[2 * a + 1])));
        }
        return list;
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

    public static void printArcs2(List<City> cityList) {
        System.out.println(" - Distance array - ");
        for (int i = 0; i < cityList.size(); i++) {
            for (int j = 0; j < cityList.size(); j++) {
                System.out.print(" " + (long) cityList.get(i).point.distance(cityList.get(j).point));
            }
            System.out.println();
        }
    }

    public static TspPath resolveIntersection(TspPath tspPath) {
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        int index4 = -1;

        Intersection intersection = tspPath.nextIntersection();

        if (intersection != null) {
            for (City city : tspPath.path) {
                if (city.point.getX() == intersection.line1.getP1().getX() && city.point.getY() == intersection.line1.getP1().getY()) {
                    System.out.println("City 1 has id " + city.id + " with position " + tspPath.path.indexOf(city));
                    index1 = tspPath.path.indexOf(city);
                } else if (city.point.getX() == intersection.line1.getP2().getX() && city.point.getY() == intersection.line1.getP2().getY()) {
                    System.out.println("City 2 has id " + city.id + " with position " + tspPath.path.indexOf(city));
                    index2 = tspPath.path.indexOf(city);
                } else if (city.point.getX() == intersection.line2.getP1().getX() && city.point.getY() == intersection.line2.getP1().getY()) {
                    System.out.println("City 3 has id " + city.id + " with position " + tspPath.path.indexOf(city));
                    index3 = tspPath.path.indexOf(city);
                } else if (city.point.getX() == intersection.line2.getP2().getX() && city.point.getY() == intersection.line2.getP2().getY()) {
                    System.out.println("City 4 has id " + city.id + " with position " + tspPath.path.indexOf(city));
                    index4 = tspPath.path.indexOf(city);
                }
            }

            List<City> cityList = new ArrayList<City>();
            cityList.addAll(tspPath.path.subList(0, index1 + 1));
            for (int a = index3; a >= index2; a--) {
                cityList.add(tspPath.path.get(a));
            }

            if (index4 != 0) {
                cityList.addAll(tspPath.path.subList(index4, tspPath.path.size()));
            }

            TspPath resolvedPath = new TspPath(cityList);

            if (resolvedPath.getPathLength() < tspPath.getPathLength() && resolvedPath.isValidPath()) {
                System.out.println("Resolved intersection and saved " + (tspPath.getPathLength() - resolvedPath.getPathLength()));
                return resolvedPath;
            }
        }

        return tspPath;
    }
}
