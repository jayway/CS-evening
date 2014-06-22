import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TSPSolver {

    private final static int WINDOW_SIZE = 500;
    private static long rndSeed;

    public static void main(String[] args) {

        final int[] data = TSPTools.readGraphFromCVSFile("../nodegen/10_locations.csv");
        final int coordinateSize = TSPTools.getMaxCoordSize(data);


        int size = data.length / 2;

        double[][] arcs = Arcs.getArray(data, size, size);

        final int[] path = new int[size];

        rndSeed = System.currentTimeMillis();
        TSPTools.getRandomizedStartPath(path, rndSeed);

        double last = Double.MAX_VALUE - 1;
        double best = Double.MAX_VALUE;
        while (last < best) {
            best = last;
            // TODO improve the path !!!
        }

        TSPTools.checkPath(arcs, path);

        TSPTools.savePathToFile(path, rndSeed,
                "result_" + size + "_" + TSPTools.getPathLength(arcs, path) + ".csv");


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
