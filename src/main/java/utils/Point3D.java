package utils;

import org.ejml.simple.SimpleMatrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Point3D implements Comparable<Point3D> {
    private final TransformationMatrices matrices;
    private final Map<Coordinates3D, Double> coordinates;

    public Point3D(double x, double y, double z) {
        coordinates = new HashMap<>();
        coordinates.put(Coordinates3D.X, x);
        coordinates.put(Coordinates3D.Y, y);
        coordinates.put(Coordinates3D.Z, z);
        matrices = new TransformationMatrices();
    }

    public static Point3D average(List<Point3D> nodes) {
        double x = getAverageCoord(Point3D::x, nodes);
        double y = getAverageCoord(Point3D::y, nodes);
        double z = getAverageCoord(Point3D::z, nodes);
        return new Point3D(x, y, z);
    }

    private static double getAverageCoord(Function<Point3D, Double> valueSupplier, List<Point3D> nodes) {
        return nodes.stream()
                .map(valueSupplier)
                .reduce(0.0, Double::sum);
    }

    public double x() {
        return coordinates.get(Coordinates3D.X);
    }

    public void setX(double x) {
        coordinates.put(Coordinates3D.X, x);
    }

    public double y() {
        return coordinates.get(Coordinates3D.Y);
    }

    public void setY(double y) {
        coordinates.put(Coordinates3D.Y, y);
    }

    public double z() {
        return coordinates.get(Coordinates3D.Z);
    }

    public void setZ(double z) {
        coordinates.put(Coordinates3D.Z, z);
    }

    public double get(Coordinates3D coord) {
        return coordinates.get(coord);
    }

    public void set(Coordinates3D coord, double value) {
        coordinates.put(coord, value);
    }

    public Point2D project(double observerDistance) {
        var projectionMatrix = matrices.getProjectionMatrix(observerDistance);
        var coordinatesVector = matrices.getNormalizedCoordinates(this);
        var projectionResult = projectionMatrix.mult(coordinatesVector);
        normalize(projectionResult, observerDistance);
        return new Point2D(projectionResult.get(0), projectionResult.get(1));
    }

    private void normalize(SimpleMatrix vector, double d) {
        var x = vector.get(0);
        var y = vector.get(1);
        var z = vector.get(2);
        vector.set(0, x * d / z);
        vector.set(1, y * d / z);
    }

    public void zoom(double zoomStep) {
        setX(x() * zoomStep);
        setY(y() * zoomStep);
        setZ(z() * zoomStep);
    }

    public void rotateOX(double step) {
        var OXRotationMatrix = matrices.getOXRotationMatrix(step);
        rotate(OXRotationMatrix);
    }

    public void rotateOY(double step) {
        var OYRotationMatrix = matrices.getOYRotationMatrix(step);
        rotate(OYRotationMatrix);
    }

    public void rotateOZ(double step) {
        var OZRotationMatrix = matrices.getOZRotationMatrix(step);
        rotate(OZRotationMatrix);
    }

    private void rotate(SimpleMatrix rotationMatrix) {
        var coordinatesVector = matrices.getNormalizedCoordinates(this);
        var rotationResult = rotationMatrix.mult(coordinatesVector);
        setX(rotationResult.get(0));
        setY(rotationResult.get(1));
        setZ(rotationResult.get(2));
    }

    public Point3D getFurther(Point3D other) {
        return this.z() > other.z() ? this : other;
    }

    @Override
    public int compareTo(Point3D other) {
        return Double.compare(this.distanceToPoint0(), other.distanceToPoint0());
    }

    public double distanceToPoint0() {
        return Math.sqrt(x() * x() + y() * y() + z() * z());
    }

    public boolean isVisible() {
        return z() >= 0;
    }
}
