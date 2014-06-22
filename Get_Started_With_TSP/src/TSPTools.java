import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TSPTools {

    /**
     * Checks that the path is valid (contains every node exactly once), and
     * also prints the length of the path.
     * 
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     */
    public static void checkPath(double[][] arcs, int[] path) {
        double length = TSPTools.getPathLength(arcs, path);
        boolean valid = TSPTools.isPathValid(path);
        System.out.println("This TSP path is " + length + " km long and is " + (valid ? "valid" : "invalid") + ".");
        TSPTools.printPath(path);
    }

    /**
     * Calculate the length of a path.
     * 
     * @param arcs matrix containing the arcs of the graph
     * @param path the path to get a length for
     * @return the length of the path
     */
    public static double getPathLength(double[][] arcs, int[] path) {
        double l = 0;
        for (int i = 1; i < path.length; i++) {
            l += arcs[path[i - 1]][path[i]];
        }
        l += arcs[path[path.length - 1]][path[0]];
        return l;
    }

    /**
     * Check that the path contains every node exactly once.
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
     * Reverses a subsection of a path.
     * 
     * @param path the path to reverse a subsection of
     * @param reverseStart the first node of the subsection
     * @param reverseEnd the last node of the subsection
     */
    public static void reversSubSectionOfArray(int[] path, int reverseStart, int reverseEnd) {
        if (reverseEnd < reverseStart) {
            int t = reverseEnd;
            reverseEnd = reverseStart;
            reverseStart = t;
        }
        int d2 = (reverseEnd - reverseStart) / 2;
        for (int i = 0; i <= d2; i++) {
            int t = path[reverseStart + i];
            path[reverseStart + i] = path[reverseEnd - i];
            path[reverseEnd - i] = t;
        }
    }

    /**
     * Fill in the path with a randomized valid set of nodes.
     * 
     * @param path this is where the result is returned.
     * @param seed the seed used to create the path, reusing the same seed
     *        results in the same path.
     */
    public static void getRandomizedStartPath(int[] path, long seed) {
        Random rnd = new Random(seed);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < path.length; i++) {
            list.add(new Integer(i));
        }
        int j = 0;
        while (!list.isEmpty()) {
            int ri = rnd.nextInt(list.size());
            path[j++] = (Integer) list.remove(ri);
        }
    }

    /**
     * Read a graph from a comma separated file, file should be on x,y,x,y...
     * format
     * 
     * @param file the file containing the graph
     * @return the nodes x and y values of the graph
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

    /**
     * Looks for the maximum coordinate on both x and y axis, returns the max
     * value found.
     * 
     * @param data the x,y values for al the nodes in the graph
     * @return integer for the largest x or y value found
     */
    public static int getMaxCoordSize(int[] data) {
        int max = 0;
        for (int s : data) {
            max = Math.max(max, s);
        }
        return max;
    }

    /**
     * Prints the path
     * 
     * @param path the path to print
     */
    public static void printPath(int[] path) {
        System.out.print("Path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + ", ");
        }
        System.out.println();
    }

    /**
     * Prints the arcs of the graph
     * 
     * @param distanceArray
     */
    public static void printArcs(int[][] distanceArray) {
        System.out.println(" - Distance array - ");
        for (int i = 0; i < distanceArray.length; i++) {
            for (int j = 0; j < distanceArray[0].length; j++) {
                System.out.print(" " + distanceArray[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Build a polygon for plotting the path on the screen
     * 
     * @param nodeData x,y,x,y... data of the graph
     * @param path the path to plot
     * @param windowSize size of the window to plot in
     * @param maxCoordinate max x or y coordinate
     * @return the polygon to plot.
     */
    public static Polygon getPolygonForPlotting(int[] nodeData, int[] path, int windowSize, int maxCoordinate) {
        Polygon p = new Polygon();
        float scale = (float) windowSize / maxCoordinate;
        for (int a = 0; a < path.length; a++) {
            p.addPoint((int) (nodeData[2 * path[a]] * scale), (int) (nodeData[2 * path[a] + 1] * scale));
        }
        return p;
    }

    /**
     * 
     * Write the path to file
     * 
     * @param path the path to write to file
     * @param rndSeed the seed used to create the path
     * @param string file name
     */
    public static void savePathToFile(int[] path, long rndSeed, String string) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        StringBuffer data = new StringBuffer();
        data.append("Time: ");
        data.append(sdf.format(date));
        data.append("\n");
        data.append("Seed: ");
        data.append(rndSeed);
        data.append("\n");
        for (int i = 0; i < path.length; i++) {
            data.append(path[i]).append(", ");
        }
        data.append("\n");
        FileWriter writer;
        try {
            writer = new FileWriter(string);
            writer.write(data.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
