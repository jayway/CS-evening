package domain;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mattias on 2014-06-18.
 */
public class TspPath {

    private int size;

    public final List<City> path;

    public TspPath(List<City> path) {
        this.path = path;
        size = path.size();
    }

    public long getPathLength() {
        long distance = 0;
        for (int a = 0; a < path.size(); a++) {
            distance += path.get(a).point.distance(path.get((a + 1) % path.size()).point);
        }

        return distance;
    }

    public boolean isValidPath() {
        HashSet<Integer> allNodes = new HashSet<Integer>();

        for (int a = 0; a < size; a++) {
            allNodes.add(a);
        }

        for (City city : path) {
            if (!allNodes.remove(city.id)) {
                return false;
            }
        }

        if (allNodes.size() != 0) {
            return false;
        }

        return true;
    }

    private List<Line2D> getLineList() {
        List<Line2D> lineList = new ArrayList<Line2D>();
        for (int a = 0; a < size - 1; a++) {
            lineList.add(new Line2D.Double(path.get(a).point.getX(), path.get(a).point.getY(), path.get(a + 1).point.getX(), path.get(a + 1).point.getY()));
        }

        lineList.add(new Line2D.Double(path.get(size - 1).point.getX(), path.get(size - 1).point.getY(), path.get(0).point.getX(), path.get(0).point.getY()));
        return lineList;
    }

    public void printIntersections() {
        List<Line2D> lineList = getLineList();
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 2; j < lineList.size(); j++) {
                if (lineList.get(i).intersectsLine(lineList.get(j)) && (i != 0 && j != lineList.size())) {
                    System.out.println("Intersection found <" + i + " (" + lineList.get(i).getP1().getX() + "," + lineList.get(i).getP1().getY() + "),(" + lineList.get(i).getP2().getX() + "," + lineList.get(i).getP2().getY() + ")> <" + j + " (" + lineList.get(j).getP1().getX() + "," + lineList.get(j).getP1().getY() + "),(" + lineList.get(j).getP2().getX() + "," + lineList.get(j).getP2().getY() + ")>");
                }
            }
        }
    }

    public List<Intersection> getIntersectionList() {
        List<Line2D> lineList = getLineList();
        List<Intersection> intersectionList = new ArrayList<Intersection>();
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 2; j < lineList.size(); j++) {
                if (lineList.get(i).intersectsLine(lineList.get(j))) {
                    intersectionList.add(new Intersection(lineList.get(i), lineList.get(j)));
                }
            }
        }
        return intersectionList;
    }

    public Intersection nextIntersection() {
        List<Line2D> lineList = getLineList();
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 2; j < lineList.size(); j++) {
                if (lineList.get(i).intersectsLine(lineList.get(j)) && (i != 0 && j != lineList.size())) {
                    System.out.println("(next)Intersection found <" + i + " (" + lineList.get(i).getP1().getX() + "," + lineList.get(i).getP1().getY() + "),(" + lineList.get(i).getP2().getX() + "," + lineList.get(i).getP2().getY() + ")> <" + j + " (" + lineList.get(j).getP1().getX() + "," + lineList.get(j).getP1().getY() + "),(" + lineList.get(j).getP2().getX() + "," + lineList.get(j).getP2().getY() + ")>");
                    return new Intersection(lineList.get(i), lineList.get(j));
                }
            }
        }

        return null;
    }


    public void printPath() {
        StringBuilder buf = new StringBuilder();
        buf.append("This TSP path is " + getPathLength() + " km long and is "
                + (isValidPath() ? "valid" : "invalid") + ".\n");
        buf.append("Path: ");
        for (int i = 0; i < path.size(); i++) {
            buf.append(path.get(i).id + ", ");
        }
        System.out.println(buf.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TspPath tspPath = (TspPath) o;

        if (size != tspPath.size) return false;

        for (int a = 0; a < size; a++) {
            if (!path.get(a).equals(((TspPath) o).path.get(a))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + path.hashCode();
        return result;
    }
}
