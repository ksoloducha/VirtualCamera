package geometry.twodimensional;

public interface Line2D {
    double EPSILON = 0.000001d;

    Point2D getPointForY(double y);
}
