package scene;

import ui.DisplayGraphics;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class SceneTransformations {
    private static final Map<Integer, Runnable> keyActionMap = new HashMap<>();
    private static Scene scene = new Scene();
    private static DisplayGraphics displayGraphics = new DisplayGraphics(scene);

    static {
        keyActionMap.put(KeyEvent.VK_UP, SceneTransformations::onMoveForward);
        keyActionMap.put(KeyEvent.VK_DOWN, SceneTransformations::onMoveBack);
        keyActionMap.put(KeyEvent.VK_RIGHT, SceneTransformations::onMoveRight);
        keyActionMap.put(KeyEvent.VK_LEFT, SceneTransformations::onMoveLeft);
        keyActionMap.put(KeyEvent.VK_W, SceneTransformations::onMoveUp);
        keyActionMap.put(KeyEvent.VK_S, SceneTransformations::onMoveDown);
        keyActionMap.put(KeyEvent.VK_Z, SceneTransformations::onRotateOXLeft);
        keyActionMap.put(KeyEvent.VK_X, SceneTransformations::onRotateOXRight);
        keyActionMap.put(KeyEvent.VK_C, SceneTransformations::onRotateOYLeft);
        keyActionMap.put(KeyEvent.VK_V, SceneTransformations::onRotateOYRight);
        keyActionMap.put(KeyEvent.VK_B, SceneTransformations::onRotateOZLeft);
        keyActionMap.put(KeyEvent.VK_N, SceneTransformations::onRotateOZRight);
        keyActionMap.put(KeyEvent.VK_Q, SceneTransformations::onZoomIn);
        keyActionMap.put(KeyEvent.VK_A, SceneTransformations::onZoomOut);
    }

    public static void setScene(Scene scene) {
        SceneTransformations.scene = scene;
    }

    public static void setDisplayGraphics(DisplayGraphics displayGraphics) {
        SceneTransformations.displayGraphics = displayGraphics;
    }

    public static Map<Integer, Runnable> keyActionMap() {
        return keyActionMap;
    }

    public static void onTakePhoto() {
        displayGraphics.saveToFile();
    }

    public static void onMoveUp() {
        scene.moveObserverUp();
        displayGraphics.repaint();
    }

    public static void onMoveDown() {
        scene.moveObserverDown();
        displayGraphics.repaint();
    }

    public static void onMoveLeft() {
        scene.moveObserverLeft();
        displayGraphics.repaint();
    }

    public static void onMoveRight() {
        scene.moveObserverRight();
        displayGraphics.repaint();
    }

    public static void onMoveForward() {
        scene.moveObserverForward();
        displayGraphics.repaint();
    }

    public static void onMoveBack() {
        scene.moveObserverBack();
        displayGraphics.repaint();
    }

    public static void onRotateOXRight() {
        scene.rotateOXRight();
        displayGraphics.repaint();
    }

    public static void onRotateOXLeft() {
        scene.rotateOXLeft();
        displayGraphics.repaint();
    }

    public static void onRotateOYRight() {
        scene.rotateOYRight();
        displayGraphics.repaint();
    }

    public static void onRotateOYLeft() {
        scene.rotateOYLeft();
        displayGraphics.repaint();
    }

    public static void onRotateOZRight() {
        scene.rotateOZRight();
        displayGraphics.repaint();
    }

    public static void onRotateOZLeft() {
        scene.rotateOZLeft();
        displayGraphics.repaint();
    }

    public static void onZoomIn() {
        scene.zoomIn();
        displayGraphics.repaint();
    }

    public static void onZoomOut() {
        scene.zoomOut();
        displayGraphics.repaint();
    }
}
