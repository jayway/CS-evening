package domain;

import java.awt.geom.Line2D;

/**
 * Created by mattias on 2014-06-19.
 */
public class Intersection {

    public final Line2D line1;

    public final Line2D line2;

    public Intersection(Line2D line1, Line2D line2) {
        this.line1 = line1;
        this.line2 = line2;
    }
}
