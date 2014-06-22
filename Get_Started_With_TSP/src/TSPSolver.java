import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TSPSolver {

    // Size of the window where we draw the resulting path
    private final static int WINDOW_SIZE = 500;
    // used for generating a randomized path through the graph
    private static long rndSeed;

    public static void main(String[] args) {

        // Read the x and y values for al nodes from a csv file.
        final int[] data = TSPTools.readGraphFromCVSFile("../nodegen/10_locations.csv");

        // Get the largest x or y value, used for scaling the resulting path
        // before drawing it to screen.
        final int coordinateSize = TSPTools.getMaxCoordSize(data);

        // How long should the path be / how many nodes are we dealing with.
        int size = data.length / 2;

        // Get the distances between all nodes
        double[][] arcs = Arcs.getArray(data, size, size);

        // Create a array for holding the path
        final int[] path = new int[size];

        // Seed for creating a random path
        rndSeed = System.currentTimeMillis();

        // create a random path
        TSPTools.getRandomizedStartPath(path, rndSeed);

        // TODO improve the path !!!
        // This is where YOU improve the path, please use the TSPTools where
        // needed, to check that your path is valid and to check the length of
        // it for example.
        // END TODO

        // Validate that the path is valid (contains all nodes exactly once),
        // and print the length of it.
        TSPTools.checkPath(arcs, path);

        // Save the path to file, named after the input and length
        TSPTools.savePathToFile(path, rndSeed,
                "result_" + size + "_" + TSPTools.getPathLength(arcs, path) + ".csv");

        // Show the resulting path in a window, useful for debugging and
        // improving the optimising algorithms.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(TSPTools.getPolygonForPlotting(data, path, WINDOW_SIZE, coordinateSize));
            }
        });
    }

    private static void createAndShowGUI(Polygon p) {
        JFrame f = new JFrame("TSP path viewer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WINDOW_SIZE, WINDOW_SIZE);
        f.add(new MyPanel(p));
        f.pack();
        f.setVisible(true);
    }

    static class MyPanel extends JPanel {
        Polygon p = null;

        public MyPanel(Polygon p) {
            setBorder(BorderFactory.createLineBorder(Color.black));
            this.p = p;
        }

        public Dimension getPreferredSize() {
            return new Dimension(WINDOW_SIZE, WINDOW_SIZE);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawPolygon(p);
        }
    }

}
