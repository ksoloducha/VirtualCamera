package utils;

public class PlaneParallelToAxis implements Area3D {
    private final Coordinates3D constCoordinate;
    private final double constValue;

    private PlaneParallelToAxis(double constValue, Coordinates3D constCoordinate) {
        this.constValue = constValue;
        this.constCoordinate = constCoordinate;
    }

    public static PlaneParallelToAxis parallelToXY(double constValue) {
        return new PlaneParallelToAxis(constValue, Coordinates3D.Z);
    }

    public static PlaneParallelToAxis parallelToXZ(double constValue) {
        return new PlaneParallelToAxis(constValue, Coordinates3D.Y);
    }

    public static PlaneParallelToAxis parallelToYZ(double constValue) {
        return new PlaneParallelToAxis(constValue, Coordinates3D.X);
    }

    @Override
    public boolean areOnTheSameSide(Point3D p1, Point3D p2) {
        var val1 = p1.get(constCoordinate);
        var val2 = p2.get(constCoordinate);
        if (isOnPlane(p1) || isOnPlane(p2)) {
            return false;
        }
        return val1 < constValue && val2 < constValue
                || val1 > constValue && val2 > constValue;
    }

    @Override
    public boolean isOnPlane(Point3D node) {
        return node.get(constCoordinate) == constValue;
    }

    @Override
    public double getPlaneEquationValue(Point3D point3D) {
        return point3D.get(constCoordinate);
    }
}
