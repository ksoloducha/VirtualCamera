package keyActions;

import java.util.concurrent.atomic.AtomicBoolean;

public class SceneActionThread implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Runnable action;

    public void start(Runnable action) throws InterruptedException {
        this.action = action;
        Thread worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            action.run();
            try {
                Thread.currentThread().join(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running.get();
    }
}
