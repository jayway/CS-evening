package domain;

import java.awt.geom.Point2D;

/**
 * Created by mattias on 2014-06-18.
 */
public class City {
    public final int id;

    public final Point2D point;

    public City(int id, Point2D point) {
        this.id = id;
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (!point.equals(city.point)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + point.hashCode();
        return result;
    }
}
