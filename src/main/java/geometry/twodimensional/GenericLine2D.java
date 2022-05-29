package geometry.twodimensional;

public class GenericLine2D implements Line2D {
    private final double a;
    private final double b;

    public GenericLine2D(Point2D point1, Point2D point2) {
        this.a = calculateA(point1.unshift(), point2.unshift());
        this.b = calculateB(a, point1.unshift());
    }

    private double calculateB(double a, Point2D point) {
        return point.y() - a * point.x();
    }

    private double calculateA(Point2D point1, Point2D point2) {
        return (point1.y() - point2.y()) / (point1.x() - point2.x());
    }

    @Override
    public Point2D getPointForY(double y) {
        var unshiftY = Point2D.unshiftY(y);
        var x = (unshiftY - b) / a;
        return Point2D.withShift(x, unshiftY);
    }
}
