package geometry.twodimensional;

public class Line2DYParallel implements Line2D {
    private final double y;

    public Line2DYParallel(Point2D point) {
        y = point.y();
    }

    @Override
    public Point2D getPointForY(double y) {
        throw new IllegalStateException("Not enough data");
    }
}
