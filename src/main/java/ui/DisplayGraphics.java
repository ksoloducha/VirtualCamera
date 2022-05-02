package ui;

import scene.Scene;

import javax.imageio.ImageIO;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayGraphics extends Canvas {
    private final Scene scene;

    public DisplayGraphics(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void paint(Graphics graphics) {
        setBackground(Color.BLACK);
        setForeground(Color.PINK);
        scene.getSceneObjects().forEach(sceneObject -> sceneObject.draw((Graphics2D) graphics));
    }

    public void saveToFile() {
        flash();
        saveSceneToFile();
    }

    private void flash() {
        setBackground(Color.WHITE);
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
    }

    private void saveSceneToFile() {
        var image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        var graphics = image.createGraphics();
        paint(graphics);
        graphics.dispose();
        var fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        var path = "photos/" + fileName + ".png";
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
