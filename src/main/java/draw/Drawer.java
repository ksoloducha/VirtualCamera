package draw;

import geometry.threedimensional.Projectable3D;

import java.awt.*;
import java.util.List;

public abstract class Drawer {
    protected final double observerDistance;
    protected final Graphics2D graphics;

    protected Drawer(double observerDistance, Graphics2D graphics) {
        this.observerDistance = observerDistance;
        this.graphics = graphics;
    }

    public void draw(List<? extends Projectable3D> elements) {
        elements.forEach(element -> {
            if (element.isVisible()) {
                drawElements(element);
            }
        });
    }

    protected abstract void drawElements(Projectable3D element);
}
