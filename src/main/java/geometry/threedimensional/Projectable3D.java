package geometry.threedimensional;

import geometry.twodimensional.Point2D;

import java.awt.*;
import java.util.List;

public interface Projectable3D {
    List<Point2D> getProjection(double observerDistance);

    boolean isVisible();

    Color getColor();
}
