import keyactions.KeyDispatcher;
import scene.Scene;
import scene.SceneTransformations;
import ui.ActionsWindow;
import ui.DisplayGraphics;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var scene = new Scene();
            var displayGraphics = new DisplayGraphics(scene);
            new MainWindow(displayGraphics);
            new ActionsWindow();
            SceneTransformations.setScene(scene);
            SceneTransformations.setDisplayGraphics(displayGraphics);
            var manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.addKeyEventDispatcher(new KeyDispatcher());
        });
    }
}
