package keyActions;

import scene.SceneTransformations;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyDispatcher implements KeyEventDispatcher {
    private final SceneActionThread actionThread = new SceneActionThread();

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            handleKeyPressed(e);
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            handleKeyReleased();
        }
        return false;
    }

    private void handleKeyPressed(KeyEvent e) {
        if (SceneTransformations.keyActionMap().containsKey(e.getKeyCode())) {
            actionThread.start(SceneTransformations.keyActionMap().get(e.getKeyCode()));
        }
    }

    private void handleKeyReleased() {
        actionThread.stop();
    }
}
