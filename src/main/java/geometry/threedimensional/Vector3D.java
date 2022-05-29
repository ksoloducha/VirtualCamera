package geometry.threedimensional;

public class Vector3D {
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(Point3D start, Point3D end) {
        x = end.x() - start.x();
        y = end.y() - start.y();
        z = end.z() - start.z();
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public Vector3D vectorProduct(Vector3D other) {
        var resX = y * other.z - z * other.y;
        var rexY = z * other.x - x * other.z;
        var resZ = x * other.y - y * other.x;
        return new Vector3D(resX, rexY, resZ);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        var other = (Vector3D) obj;
        return x == other.x && y == other.y && z == other.z;
    }
}
