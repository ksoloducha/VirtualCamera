package geometry.twodimensional;

public class Line2DXParallel implements Line2D {
    private final double x;

    public Line2DXParallel(Point2D point) {
        this.x = point.x();
    }

    @Override
    public Point2D getPointForY(double y) {
        return Point2D.noShift(x, y);
    }
}
