package geometry.utils;

import geometry.threedimensional.Point3D;
import org.ejml.simple.SimpleMatrix;

public class TransformationMatrices {
    public SimpleMatrix getProjectionMatrix(double observerDistance) {
        var data = initializeProjectionData(observerDistance);
        return new SimpleMatrix(data);
    }

    private double[][] initializeProjectionData(double d) {
        var data = new double[4][4];
        data[0][0] = 1.0;
        data[1][1] = 1.0;
        data[2][2] = 1.0;
        data[3][2] = 1 / d;
        return data;
    }

    public SimpleMatrix getNormalizedCoordinates(Point3D point) {
        var data = new double[4][1];
        data[0][0] = point.x();
        data[1][0] = point.y();
        data[2][0] = point.z();
        data[3][0] = 1.0;
        return new SimpleMatrix(data);
    }

    public SimpleMatrix getOXRotationMatrix(double step) {
        var data = new double[4][4];
        data[0][0] = 1.0;
        data[3][3] = 1.0;
        data[1][1] = Math.cos(step);
        data[1][2] = -1.0 * Math.sin(step);
        data[2][1] = Math.sin(step);
        data[2][2] = Math.cos(step);
        return new SimpleMatrix(data);
    }

    public SimpleMatrix getOYRotationMatrix(double step) {
        var data = new double[4][4];
        data[1][1] = 1.0;
        data[3][3] = 1.0;
        data[0][0] = Math.cos(step);
        data[0][2] = Math.sin(step);
        data[2][0] = -1.0 * Math.sin(step);
        data[2][2] = Math.cos(step);
        return new SimpleMatrix(data);
    }

    public SimpleMatrix getOZRotationMatrix(double step) {
        var data = new double[4][4];
        data[2][2] = 1.0;
        data[3][3] = 1.0;
        data[0][0] = Math.cos(step);
        data[0][1] = -1.0 * Math.sin(step);
        data[1][0] = Math.sin(step);
        data[1][1] = Math.cos(step);
        return new SimpleMatrix(data);
    }
}
