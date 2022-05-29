package geometry.threedimensional;

public class Plane implements Area3D {
    private static final double epsilon = 0.000001d;
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    private Plane(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static Plane fromPoints(Point3D p1, Point3D p2, Point3D p3) {
        var v1 = new Vector3D(p1, p2);
        var v2 = new Vector3D(p1, p3);
        var n = v1.vectorProduct(v2);
        return Plane.fromNormalVectorAndPoint(n, p1);
    }

    public static Plane fromNormalVectorAndPoint(Vector3D n, Point3D p) {
        var d = -1 * (n.x() * p.x() + n.y() * p.y() + n.z() * p.z());
        return new Plane(n.x(), n.y(), n.z(), d);
    }

    @Override
    public boolean areOnTheSameSide(Point3D p1, Point3D p2) {
        var val1 = getPlaneEquationValue(p1);
        var val2 = getPlaneEquationValue(p2);
        if (isOnPlane(p1) || isOnPlane(p2)) {
            return false;
        }
        return val1 > 0 && val2 > 0
                || val1 < 0 && val2 < 0;
    }

    @Override
    public double getPlaneEquationValue(Point3D p) {
        return a * p.x() + b * p.y() + c * p.z() + d;
    }

    @Override
    public boolean isOnPlane(Point3D point3D) {
        return Math.abs(getPlaneEquationValue(point3D)) < epsilon;
    }
}
