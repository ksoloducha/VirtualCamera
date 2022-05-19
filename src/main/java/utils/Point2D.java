package utils;

public class Point2D {
    private static final int X_SHIFT = 950;
    private static final int Y_SHIFT = 500;
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = X_SHIFT + x;
        this.y = Y_SHIFT - y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
