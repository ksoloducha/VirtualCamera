package draw;

import geometry.twodimensional.*;
import geometry.threedimensional.Projectable3D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;
import java.util.stream.Collectors;

public class FilledDrawer extends Drawer {
    public FilledDrawer(double observerDistance, Graphics2D graphics) {
        super(observerDistance, graphics);
    }

    @Override
    protected void drawElements(Projectable3D element) {
        var projectedNodes = getSortedClockwise(element.getProjection(observerDistance));
        var path = new Path2D.Double();
        path.moveTo(projectedNodes.get(0).x(), projectedNodes.get(0).y());
        projectedNodes.forEach(node -> path.lineTo(node.x(), node.y()));
        path.closePath();
        graphics.setColor(element.getColor());
        graphics.fill(path);
    }

    private List<Point2D> getSortedClockwise(List<Point2D> points) {
        return points.stream()
                .sorted(Point2D::compareClockwise)
                .collect(Collectors.toList());
    }
}
