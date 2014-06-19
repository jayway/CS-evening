import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TSPSolver {

    private final static int WINDOW_SIZE = 250;

    public static void main(String[] args) {

        // int[] data = RandomData.getRandomData(size, w, h, 0);
        final int coordinateSize = 1000;
        final int[] data = TSPTools.readGraphFromCVSFile("../nodegen/" + coordinateSize + "_locations.csv");

        int size = data.length / 2;

        System.out.println("size: " + size);

        int[][] arcs = Arcs.getArray(data, size, size);

        // TSPTools.printArcs(size, arcs);

        int[] path = new int[size];
        final int[] bestPath = new int[size];

        long globalBest = Integer.MAX_VALUE;
        for (int n = 0; n < 10; n++) {
            TSPTools.getRandomizedStartPath(path, System.currentTimeMillis());

            long last = Integer.MAX_VALUE - 1;
            long best = Integer.MAX_VALUE;
            while (last < best) {
                best = last;
                last = tryRemoveAndInsert(arcs, path);
                long x = removeXarcs(path, arcs);
                last = last > x ? x : last;
            }
            if (globalBest > best) {
                globalBest = best;
                TSPTools.checkPath(arcs, path);
                for (int i = 0; i < path.length; i++) {
                    bestPath[i] = path[i];
                }
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(TSPTools.getPolygonForPlotting(data, bestPath, WINDOW_SIZE, coordinateSize));
            }
        });

    }

    private static void createAndShowGUI(Polygon p) {
        System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WINDOW_SIZE, WINDOW_SIZE);
        f.add(new MyPanel(p));
        f.pack();
        f.setVisible(true);
    }

    private static long tryRemoveAndInsert(int[][] arcs, int[] path) {
        int l = path.length;

        for (int n = 0; n < l; n++) {
            for (int i = 0; i < l; i++) {
                int pna = path[(n - 1 + l) % l];
                int pnb = path[n];
                int pnc = path[(n + 1) % l];
                int pib = path[i];
                int pic = path[(i + 1) % l];
                int distOrg = arcs[pna][pnb] + arcs[pnb][pnc] + arcs[pib][pic];
                int distNew = arcs[pnb][pib] + arcs[pnb][pic] + arcs[pna][pnc];
                if (distOrg > distNew) {
                    if (n < i) {
                        int v = path[n];
                        for (int j = n; j < i; j++) {
                            path[j] = path[j + 1];
                        }
                        path[i] = v;
                    } else if (n > i) {
                        int v = path[n];
                        for (int j = n; j > i + 1; j--) {
                            path[j] = path[j - 1];
                        }
                        path[i + 1] = v;
                    }
                }
            }
        }
        return TSPTools.getPathLength(arcs, path);
    }

    private static long removeXarcs(int[] path, int[][] arcs) {
        int l = path.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 2; j < l; j++) {
                int a = i;
                int d = (i + 1) % l;
                int b = j;
                int c = (j + 1) % l;
                int a1 = arcs[path[a]][path[d]] + arcs[path[b]][path[c]];
                int a2 = arcs[path[a]][path[b]] + arcs[path[d]][path[c]];
                if (a1 > a2) {
                    TSPTools.reversSubSectionOfArray(path, d, b);
                }
            }
        }
        return TSPTools.getPathLength(arcs, path);
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
            // g.draw
        }
    }

}
