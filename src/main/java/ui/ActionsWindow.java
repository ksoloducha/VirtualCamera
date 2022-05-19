package ui;

import scene.SceneTransformations;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Objects;

public class ActionsWindow extends JFrame {
    public ActionsWindow() {
        setPreferredSize(new Dimension(350, 500));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        initializeButtons();
    }

    private void initializeButtons() {
        var generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.PAGE_AXIS));
        var cameraPanel = new JPanel();
        var directionsPanel = new JPanel();
        var zoomPanel = new JPanel();
        zoomPanel.setLayout(new FlowLayout());
        directionsPanel.setLayout(new FlowLayout());
        var rotationsPanel = new JPanel();
        var rotationsLayout = new GridLayout(3, 2);
        rotationsPanel.setLayout(rotationsLayout);
        rotationsLayout.setVgap(10);
        rotationsLayout.setHgap(10);
        try {
            var cameraImage = getImage("/icons/photo.png");
            var displayOptionImage = getImage("/icons/displayOption.png");
            var zoomInImage = getImage("/icons/zoomin.png");
            var zoomOutImage = getImage("/icons/zoomout.png");
            var upImage = getImage("/icons/up.png");
            var downImage = getImage("/icons/down.png");
            var leftImage = getImage("/icons/left.png");
            var rightImage = getImage("/icons/right.png");
            var forwardImage = getImage("/icons/forward.png");
            var backImage = getImage("/icons/back.png");
            var rotateOXRightImage = getImage("/icons/rotateOXleft.png");
            var rotateOXLeftImage = getImage("/icons/rotateOXright.png");
            var rotateOYRightImage = getImage("/icons/rotateOYright.png");
            var rotateOYLeftImage = getImage("/icons/rotateOYleft.png");
            var rotateOZRightImage = getImage("/icons/rotateOZright.png");
            var rotateOZLeftImage = getImage("/icons/rotateOZleft.png");
            setupButton(cameraImage, cameraPanel, SceneTransformations::onTakePhoto);
            setupButton(displayOptionImage, cameraPanel, SceneTransformations::onSwitchDisplayOption);
            setupButton(upImage, directionsPanel, SceneTransformations::onMoveUp);
            setupButton(downImage, directionsPanel, SceneTransformations::onMoveDown);
            setupButton(leftImage, directionsPanel, SceneTransformations::onMoveLeft);
            setupButton(rightImage, directionsPanel, SceneTransformations::onMoveRight);
            setupButton(forwardImage, directionsPanel, SceneTransformations::onMoveForward);
            setupButton(backImage, directionsPanel, SceneTransformations::onMoveBack);
            setupButton(rotateOXRightImage, rotationsPanel, SceneTransformations::onRotateOXRight);
            setupButton(rotateOXLeftImage, rotationsPanel, SceneTransformations::onRotateOXLeft);
            setupButton(rotateOYRightImage, rotationsPanel, SceneTransformations::onRotateOYRight);
            setupButton(rotateOYLeftImage, rotationsPanel, SceneTransformations::onRotateOYLeft);
            setupButton(rotateOZRightImage, rotationsPanel, SceneTransformations::onRotateOZRight);
            setupButton(rotateOZLeftImage, rotationsPanel, SceneTransformations::onRotateOZLeft);
            setupButton(zoomInImage, zoomPanel, SceneTransformations::onZoomIn);
            setupButton(zoomOutImage, zoomPanel, SceneTransformations::onZoomOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(cameraPanel);
        this.add(zoomPanel);
        this.add(directionsPanel);
        this.add(rotationsPanel);
        generalPanel.add(cameraPanel);
        generalPanel.add(zoomPanel);
        generalPanel.add(directionsPanel);
        generalPanel.add(rotationsPanel);
        add(generalPanel);
    }

    private void setupButton(ImageIcon icon, JPanel panel, Runnable action) {
        var button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(icon);
        panel.add(button);
        button.addActionListener(e -> action.run());
    }

    private ImageIcon getImage(String path) throws IOException {
        var bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        return new ImageIcon(bufferedImage.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
    }
}
