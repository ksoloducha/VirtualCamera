package scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private static final double width = 100;
    private static final double height = 100;
    private static final double depth = 100;
    private static final double minX = 110;
    private static final double minY = 110;
    private static final double plusX = 10;
    private static final double plusY = 10;
    private static final double minZ = 1000;
    private static final double plusZ = 1120;
    private final List<SceneObject> sceneObjects = new ArrayList<>();

    public Scene() {
        sceneObjects.add(new Cuboid(-minX, -minY, minZ, height, width, depth));
        sceneObjects.add(new Cuboid(plusX, plusY, minZ, height, width, depth));
        sceneObjects.add(new Cuboid(plusX, -minY, minZ, height, width, depth));
        sceneObjects.add(new Cuboid(-minX, plusY, minZ, height, width, depth));
        sceneObjects.add(new Cuboid(-minX, -minY, plusZ, height, width, depth));
        sceneObjects.add(new Cuboid(plusX, plusY, plusZ, height, width, depth));
        sceneObjects.add(new Cuboid(plusX, -minY, plusZ, height, width, depth));
        sceneObjects.add(new Cuboid(-minX, plusY, plusZ, height, width, depth));
    }

    public List<SceneObject> getSceneObjects() {
        sceneObjects.sort(SceneObject::compareTo);
        return sceneObjects;
    }

    public void moveObserverUp() {
        sceneObjects.forEach(SceneObject::moveDown);
    }

    public void moveObserverDown() {
        sceneObjects.forEach(SceneObject::moveUp);
    }

    public void moveObserverLeft() {
        sceneObjects.forEach(SceneObject::moveRight);
    }

    public void moveObserverRight() {
        sceneObjects.forEach(SceneObject::moveLeft);
    }

    public void moveObserverForward() {
        sceneObjects.forEach(SceneObject::moveBack);
    }

    public void moveObserverBack() {
        sceneObjects.forEach(SceneObject::moveForward);
    }

    public void zoomIn() {
        sceneObjects.forEach(SceneObject::zoomIn);
    }

    public void zoomOut() {
        sceneObjects.forEach(SceneObject::zoomOut);
    }

    public void rotateOXRight() {
        sceneObjects.forEach(SceneObject::rotateOXRight);
    }

    public void rotateOXLeft() {
        sceneObjects.forEach(SceneObject::rotateOXLeft);
    }

    public void rotateOYRight() {
        sceneObjects.forEach(SceneObject::rotateOYRight);
    }

    public void rotateOYLeft() {
        sceneObjects.forEach(SceneObject::rotateOYLeft);
    }

    public void rotateOZRight() {
        sceneObjects.forEach(SceneObject::rotateOZRight);
    }

    public void rotateOZLeft() {
        sceneObjects.forEach(SceneObject::rotateOZLeft);
    }

    public boolean canMoveForward() {
        return sceneObjects.stream()
                .noneMatch(SceneObject::isCollapsingWithUser);
    }
}
