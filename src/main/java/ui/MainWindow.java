package ui;

import javax.swing.JFrame;
import java.awt.Dimension;

public class MainWindow extends JFrame {
    public MainWindow(DisplayGraphics displayGraphics) {
        add(displayGraphics);
        setPreferredSize(new Dimension(1900, 1000));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
