package geometry.twodimensional;

public class Point2D {
    private static final int X_SHIFT = 950;
    private static final int Y_SHIFT = 500;
    private final double x;
    private final double y;

    private Point2D(double x, double y) {
//        this.x = X_SHIFT + x;
//        this.y = Y_SHIFT - y;
        this.x = x;
        this.y = y;
    }

    public static int compareByY(Point2D point1, Point2D point2) {
        return Double.compare(point1.y, point2.y);
    }

    public static int compareByX(Point2D point1, Point2D point2) {
        return Double.compare(point1.x, point2.x);
    }

    public static Point2D withShift(double x, double y) {
        return new Point2D(x + X_SHIFT, Y_SHIFT - y);
    }

    public static Point2D noShift(double x, double y) {
        return new Point2D(x, y);
    }

    public static int compareClockwise(Point2D point1, Point2D point2) {
        return 0;
    }

    public static double unshiftY(double y) {
        return Y_SHIFT - y;
    }

    public static int compareByXY(Point2D point1, Point2D point2) {
        var xCompare = compareByX(point1, point2);
        return xCompare != 0 ? xCompare : compareByY(point1, point2);
    }

    public Point2D unshift() {
        return new Point2D(x - X_SHIFT, Y_SHIFT - y);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
