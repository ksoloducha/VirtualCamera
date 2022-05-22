package utils;

public interface Area3D {
    boolean areOnTheSameSide(Point3D p1, Point3D p2);

    boolean isOnPlane(Point3D point3D);

    double getPlaneEquationValue(Point3D point3D);
}
