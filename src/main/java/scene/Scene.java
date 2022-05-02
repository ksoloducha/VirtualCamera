package scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private static final List<SceneObject> SCENE_OBJECTS = new ArrayList<>();
    private static final double width = 100;
    private static final double height = 100;
    private static final double depth = 100;
    private static final double minX = 110;
    private static final double minY = 110;
    private static final double plusX = 10;
    private static final double plusY = 10;
    private static final double minZ = 1000;
    private static final double plusZ = 1120;

    static {
        SCENE_OBJECTS.add(new Cuboid(-minX, -minY, minZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(plusX, plusY, minZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(plusX, -minY, minZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(-minX, plusY, minZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(-minX, -minY, plusZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(plusX, plusY, plusZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(plusX, -minY, plusZ, height, width, depth));
        SCENE_OBJECTS.add(new Cuboid(-minX, plusY, plusZ, height, width, depth));
    }

    public List<SceneObject> getSceneObjects() {
        return SCENE_OBJECTS;
    }

    public void moveObserverUp() {
        SCENE_OBJECTS.forEach(SceneObject::moveDown);
    }

    public void moveObserverDown() {
        SCENE_OBJECTS.forEach(SceneObject::moveUp);
    }

    public void moveObserverLeft() {
        SCENE_OBJECTS.forEach(SceneObject::moveRight);
    }

    public void moveObserverRight() {
        SCENE_OBJECTS.forEach(SceneObject::moveLeft);
    }

    public void moveObserverForward() {
        SCENE_OBJECTS.forEach(SceneObject::moveBack);
    }

    public void moveObserverBack() {
        SCENE_OBJECTS.forEach(SceneObject::moveForward);
    }

    public void zoomIn() {
        SCENE_OBJECTS.forEach(SceneObject::zoomIn);
    }

    public void zoomOut() {
        SCENE_OBJECTS.forEach(SceneObject::zoomOut);
    }

    public void rotateOXRight() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOXRight);
    }

    public void rotateOXLeft() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOXLeft);
    }

    public void rotateOYRight() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOYRight);
    }

    public void rotateOYLeft() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOYLeft);
    }

    public void rotateOZRight() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOZRight);
    }

    public void rotateOZLeft() {
        SCENE_OBJECTS.forEach(SceneObject::rotateOZLeft);
    }
}
