package geometry.threedimensional;

import geometry.twodimensional.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Line3D implements Projectable3D {
    private final Point3D a;
    private final Point3D b;
    private final Color color = Color.PINK;

    public Line3D(Point3D a, Point3D b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public List<Point2D> getProjection(double observerDistance) {
        var visible = getVisiblePart();
        var projection = new ArrayList<Point2D>();
        projection.add(visible.a.project(observerDistance));
        projection.add(visible.b.project(observerDistance));
        return projection;
    }

    private Line3D getVisiblePart() {
        if (a.z() >= 0 && b.z() >= 0) {
            return new Line3D(a, b);
        }
        var crossingPoint = findCrossingPointWithXY();
        return new Line3D(crossingPoint, a.getFurther(b));
    }

    private Point3D findCrossingPointWithXY() {
        var x = a.x() + (b.x() - a.x()) * a.z() / (a.z() - b.z());
        var y = a.y() + (b.y() - a.y()) * a.z() / (a.z() - b.z());
        return new Point3D(x, y, 0.01);
    }

    @Override
    public boolean isVisible() {
        return a.z() >= 0 || b.z() >= 0;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
