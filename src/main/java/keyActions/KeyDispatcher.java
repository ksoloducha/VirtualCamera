package keyactions;

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
        if (!actionThread.isRunning() && SceneTransformations.keyActionMap().containsKey(e.getKeyCode())) {
            startKeyAction(e);
        }
    }

    private void startKeyAction(KeyEvent e) {
        try {
            actionThread.start(SceneTransformations.keyActionMap().get(e.getKeyCode()));
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void handleKeyReleased() {
        actionThread.stop();
    }
}
