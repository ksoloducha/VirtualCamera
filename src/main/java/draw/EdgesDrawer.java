package draw;

import geometry.threedimensional.Projectable3D;

import java.awt.*;
import java.awt.geom.Line2D;

public class EdgesDrawer extends Drawer {
    public EdgesDrawer(double observerDistance, Graphics2D graphics) {
        super(observerDistance, graphics);
    }

    @Override
    protected void drawElements(Projectable3D element) {
        var projection = element.getProjection(observerDistance);
        var a = projection.get(0);
        var b = projection.get(1);
        graphics.setColor(element.getColor());
        graphics.draw(new Line2D.Double(a.x(), a.y(), b.x(), b.y()));
    }
}
