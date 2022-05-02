package utils;

import org.ejml.simple.SimpleMatrix;

public class Point3D {
    private final TransformationMatrices matrices;
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        matrices = new TransformationMatrices();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
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
        x *= zoomStep;
        y *= zoomStep;
        z *= zoomStep;
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
        this.x = rotationResult.get(0);
        this.y = rotationResult.get(1);
        this.z = rotationResult.get(2);
    }

    public Point3D getFurther(Point3D other) {
        return this.getZ() > other.getZ() ? this : other;
    }
}
