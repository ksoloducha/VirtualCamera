package scene;

import geometry.threedimensional.Point3D;
import geometry.threedimensional.Projectable3D;

import java.awt.Graphics2D;
import java.util.List;

public interface SceneObject extends Comparable<SceneObject> {
    double MOVE_STEP = 5;
    double ZOOM_STEP = 1.25;
    double ROTATION_STEP = 0.01;

    void draw(Graphics2D graphics, DisplayOption displayOption);

    void moveDown();

    void moveUp();

    void moveRight();

    void moveLeft();

    void moveBack();

    void moveForward();

    void zoomIn();

    void zoomOut();

    void rotateOXRight();

    void rotateOXLeft();

    void rotateOYRight();

    void rotateOYLeft();

    void rotateOZRight();

    void rotateOZLeft();

    Point3D centerPoint();

    boolean isCollapsingWithUser();

    List<? extends Projectable3D> getEdges();

    List<? extends Projectable3D> getWalls();
}
