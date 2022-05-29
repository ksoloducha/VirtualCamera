package geometry.threedimensional;

import geometry.twodimensional.Point2D;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Polygon3D implements Projectable3D, Comparable<Polygon3D> {
    private final List<Point3D> nodes;
    private final Color color;

    public Polygon3D(Color color, Point3D... nodes) {
        if (nodes.length < 3) {
            throw new IllegalArgumentException("Wall needs to have at least 3 nodes");
        }
        this.nodes = Arrays.stream(nodes).collect(Collectors.toList());
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public List<Point2D> getProjection(double observerDistance) {
        return nodes.stream()
                .map(node -> node.project(observerDistance))
                .collect(Collectors.toList());
    }

    public Area3D getPlane() {
        if (areCoordinatesEqual(Point3D::x, nodes)) {
            return PlaneParallelToAxis.parallelToYZ(nodes.get(0).x());
        } else if (areCoordinatesEqual(Point3D::y, nodes)) {
            return PlaneParallelToAxis.parallelToXZ(nodes.get(0).y());
        } else if (areCoordinatesEqual(Point3D::z, nodes)) {
            return PlaneParallelToAxis.parallelToXY(nodes.get(0).z());
        }
        return Plane.fromPoints(nodes.get(0), nodes.get(1), nodes.get(2));
    }

    private boolean areCoordinatesEqual(Function<Point3D, Double> valueProvider, List<Point3D> nodes) {
        return nodes.stream()
                .map(valueProvider)
                .distinct()
                .count() <= 1;
    }

    @Override
    public int compareTo(Polygon3D other) {
        if (areAllNodesBeforePlane(other.getPlane())) {
            return 1;
        }
        return -1;
    }

    private boolean areAllNodesBeforePlane(Area3D plane) {
        var nodesOnPlane = getNodesOnPlane(plane);
        var nodesBeforePlane = getNodesBeforePlane(plane);
        if (nodesBeforePlane.isEmpty()) {
            return false;
        } else if (nodesBeforePlane.size() + nodesOnPlane.size() == nodes.size()) {
            return true;
        } else {
            throw new IllegalArgumentException("Plane and polygon intersect");
        }
    }

    private List<Point3D> getNodesOnPlane(Area3D plane) {
        return nodes.stream()
                .filter(plane::isOnPlane)
                .collect(Collectors.toList());
    }

    private List<Point3D> getNodesBeforePlane(Area3D plane) {
        var zeroPoint = new Point3D(0, 0, 0);
        return nodes.stream()
                .filter(node -> plane.areOnTheSameSide(zeroPoint, node))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isVisible() {
        return nodes.stream()
                .anyMatch(Point3D::isVisible);
    }
}
