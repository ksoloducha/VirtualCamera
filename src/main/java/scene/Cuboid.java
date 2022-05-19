package scene;

import utils.Line;
import utils.Point3D;
import utils.Polygon3D;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.*;
import java.util.List;

public class Cuboid implements SceneObject, Comparable<SceneObject> {
    private static final List<Color> COLORS = new ArrayList<>(6);

    static {
        COLORS.add(Color.YELLOW);
        COLORS.add(Color.BLUE);
        COLORS.add(Color.RED);
        COLORS.add(Color.GREEN);
        COLORS.add(Color.PINK);
        COLORS.add(Color.ORANGE);
    }

    private final List<Point3D> nodes = new ArrayList<>(8);
    private final List<Line> edges = new ArrayList<>(12);
    private final List<Polygon3D> walls = new ArrayList<>(6);
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
        initializeWalls();
    }

    private void initializeWalls() {
        walls.add(new Polygon3D(COLORS.get(0), nodes.get(0), nodes.get(1), nodes.get(5), nodes.get(4)));
        walls.add(new Polygon3D(COLORS.get(1), nodes.get(2), nodes.get(3), nodes.get(7), nodes.get(6)));
        walls.add(new Polygon3D(COLORS.get(2), nodes.get(0), nodes.get(2), nodes.get(6), nodes.get(4)));
        walls.add(new Polygon3D(COLORS.get(3), nodes.get(1), nodes.get(3), nodes.get(7), nodes.get(5)));
        walls.add(new Polygon3D(COLORS.get(4), nodes.get(0), nodes.get(1), nodes.get(3), nodes.get(2)));
        walls.add(new Polygon3D(COLORS.get(5), nodes.get(4), nodes.get(5), nodes.get(7), nodes.get(6)));
        sortWalls();
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
    public void draw(Graphics2D graphics, DisplayOption displayOption) {
        if (displayOption.equals(DisplayOption.EDGES)) {
            drawVisibleEdges(graphics);
        } else if (displayOption.equals(DisplayOption.FILLED)) {
            drawVisibleWalls(graphics);
        }
    }

    private void drawVisibleWalls(Graphics2D graphics) {
        walls.forEach(wall -> {
            if (wall.isVisible()) {
                drawWall(wall, graphics);
            }
        });
    }

    private void drawWall(Polygon3D wall, Graphics2D graphics) {
        var projectedNodes = wall.getProjectedNodes(observerDistance);
        var path = new Path2D.Double();
        path.moveTo(projectedNodes.get(0).x(), projectedNodes.get(0).y());
        projectedNodes.forEach(node -> path.lineTo(node.x(), node.y()));
        path.closePath();
        graphics.setColor(wall.getColor());
        graphics.fill(path);
    }

    private void drawVisibleEdges(Graphics2D graphics) {
        edges.forEach(edge -> {
            if (edge.isVisible()) {
                drawEdge(edge, graphics);
            }
        });
    }

    private void drawEdge(Line edge, Graphics2D graphics) {
        var projectedEdge = edge.getProjection();
        var a = projectedEdge.getA().project(observerDistance);
        var b = projectedEdge.getB().project(observerDistance);
        graphics.draw(new Line2D.Double(a.x(), a.y(), b.x(), b.y()));
    }

    private void sortWalls() {
        walls.sort(Polygon3D::compareTo);
    }

    @Override
    public void moveDown() {
        nodes.forEach(node -> node.setY(node.y() - MOVE_STEP));
        sortWalls();
    }

    @Override
    public void moveUp() {
        nodes.forEach(node -> node.setY(node.y() + MOVE_STEP));
        sortWalls();
    }

    @Override
    public void moveRight() {
        nodes.forEach(node -> node.setX(node.x() + MOVE_STEP));
        sortWalls();
    }

    @Override
    public void moveLeft() {
        nodes.forEach(node -> node.setX(node.x() - MOVE_STEP));
        sortWalls();
    }

    @Override
    public void moveBack() {
        nodes.forEach(node -> node.setZ(node.z() - MOVE_STEP));
        sortWalls();
    }

    @Override
    public void moveForward() {
        nodes.forEach(node -> node.setZ(node.z() + MOVE_STEP));
        sortWalls();
    }

    @Override
    public void zoomIn() {
        nodes.forEach(node -> node.zoom(ZOOM_STEP));
        observerDistance *= ZOOM_STEP;
        sortWalls();
    }

    @Override
    public void zoomOut() {
        nodes.forEach(node -> node.zoom(1 / ZOOM_STEP));
        observerDistance /= ZOOM_STEP;
        sortWalls();
    }

    @Override
    public void rotateOXRight() {
        nodes.forEach(node -> node.rotateOX(ROTATION_STEP));
        sortWalls();
    }

    @Override
    public void rotateOXLeft() {
        nodes.forEach(node -> node.rotateOX(-ROTATION_STEP));
        sortWalls();
    }

    @Override
    public void rotateOYRight() {
        nodes.forEach(node -> node.rotateOY(ROTATION_STEP));
        sortWalls();
    }

    @Override
    public void rotateOYLeft() {
        nodes.forEach(node -> node.rotateOY(-ROTATION_STEP));
        sortWalls();
    }

    @Override
    public void rotateOZRight() {
        nodes.forEach(node -> node.rotateOZ(ROTATION_STEP));
        sortWalls();
    }

    @Override
    public void rotateOZLeft() {
        nodes.forEach(node -> node.rotateOZ(-ROTATION_STEP));
        sortWalls();
    }

    @Override
    public int compareTo(SceneObject other) {
        return -1 * centerPoint().compareTo(other.centerPoint());
    }

    @Override
    public Point3D centerPoint() {
        return Point3D.average(nodes);
    }

    @Override
    public boolean isCollapsingWithUser() {
        return nodes.stream()
                .anyMatch(node -> node.z() <= 5.0);
    }
}
