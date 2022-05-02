package scene;

import utils.Line;
import utils.Point3D;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Cuboid implements SceneObject {
    private final List<Point3D> nodes = new ArrayList<>(8);
    private final List<Line> edges = new ArrayList<>(12);
    private final double width;
    private final double height;
    private final double depth;
    private double observerDistance = 500;

    public Cuboid(double x, double y, double z, double height, double width, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        initializeNodes(x, y, z);
        initializeEdges();
    }

    private void initializeNodes(double x, double y, double z) {
        nodes.add(new Point3D(x, y, z));
        nodes.add(new Point3D(x, y + height, z));
        nodes.add(new Point3D(x, y, z + depth));
        nodes.add(new Point3D(x, y + height, z + depth));
        nodes.add(new Point3D(x + width, y, z));
        nodes.add(new Point3D(x + width, y + height, z));
        nodes.add(new Point3D(x + width, y, z + depth));
        nodes.add(new Point3D(x + width, y + height, z + depth));
    }

    private void initializeEdges() {
        edges.add(new Line(nodes.get(0), nodes.get(1)));
        edges.add(new Line(nodes.get(1), nodes.get(3)));
        edges.add(new Line(nodes.get(3), nodes.get(2)));
        edges.add(new Line(nodes.get(2), nodes.get(0)));
        edges.add(new Line(nodes.get(4), nodes.get(5)));
        edges.add(new Line(nodes.get(5), nodes.get(7)));
        edges.add(new Line(nodes.get(7), nodes.get(6)));
        edges.add(new Line(nodes.get(6), nodes.get(4)));
        edges.add(new Line(nodes.get(0), nodes.get(4)));
        edges.add(new Line(nodes.get(1), nodes.get(5)));
        edges.add(new Line(nodes.get(2), nodes.get(6)));
        edges.add(new Line(nodes.get(3), nodes.get(7)));
    }

    @Override
    public void draw(Graphics2D graphics) {
        edges.forEach(edge -> {
            if (edge.isVisible()) {
                var projectedEdge = edge.getProjection();
                var a = projectedEdge.getA().project(observerDistance);
                var b = projectedEdge.getB().project(observerDistance);
                graphics.draw(new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY()));
            }
        });
    }

    @Override
    public void moveDown() {
        nodes.forEach(node -> node.setY(node.getY() - MOVE_STEP));
    }

    @Override
    public void moveUp() {
        nodes.forEach(node -> node.setY(node.getY() + MOVE_STEP));
    }

    @Override
    public void moveRight() {
        nodes.forEach(node -> node.setX(node.getX() + MOVE_STEP));
    }

    @Override
    public void moveLeft() {
        nodes.forEach(node -> node.setX(node.getX() - MOVE_STEP));
    }

    @Override
    public void moveBack() {
        nodes.forEach(node -> node.setZ(node.getZ() - MOVE_STEP));
    }

    @Override
    public void moveForward() {
        nodes.forEach(node -> node.setZ(node.getZ() + MOVE_STEP));
    }

    @Override
    public void zoomIn() {
        nodes.forEach(node -> node.zoom(ZOOM_STEP));
        observerDistance *= ZOOM_STEP;
    }

    @Override
    public void zoomOut() {
        nodes.forEach(node -> node.zoom(1 / ZOOM_STEP));
        observerDistance /= ZOOM_STEP;
    }

    @Override
    public void rotateOXRight() {
        nodes.forEach(node -> node.rotateOX(ROTATION_STEP));
    }

    @Override
    public void rotateOXLeft() {
        nodes.forEach(node -> node.rotateOX(-ROTATION_STEP));
    }

    @Override
    public void rotateOYRight() {
        nodes.forEach(node -> node.rotateOY(ROTATION_STEP));
    }

    @Override
    public void rotateOYLeft() {
        nodes.forEach(node -> node.rotateOY(-ROTATION_STEP));
    }

    @Override
    public void rotateOZRight() {
        nodes.forEach(node -> node.rotateOZ(ROTATION_STEP));
    }

    @Override
    public void rotateOZLeft() {
        nodes.forEach(node -> node.rotateOZ(-ROTATION_STEP));
    }
}
