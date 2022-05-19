package utils;

public class Line {
    private final Point3D a;
    private final Point3D b;

    public Line(Point3D a, Point3D b) {
        this.a = a;
        this.b = b;
    }

    public Point3D getA() {
        return a;
    }

    public Point3D getB() {
        return b;
    }

    public Line getProjection() {
        if (a.z() >= 0 && b.z() >= 0) {
            return new Line(a, b);
        }
        var crossingPoint = findCrossingPointWithXY();
        return new Line(crossingPoint, a.getFurther(b));
    }

    private Point3D findCrossingPointWithXY() {
        var x = a.x() + (b.x() - a.x()) * a.z() / (a.z() - b.z());
        var y = a.y() + (b.y() - a.y()) * a.z() / (a.z() - b.z());
        return new Point3D(x, y, 0.01);
    }

    public boolean isVisible() {
        return a.z() >= 0 || b.z() >= 0;
    }
}
