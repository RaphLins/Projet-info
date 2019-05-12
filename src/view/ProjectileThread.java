package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileThread extends JPanel implements Runnable {
    private int delay = 15;
    private BufferedImage image;
    private Graphics offScreen;
    private int y = 0;
    Thread thread;

    public ProjectileThread() {
        image = new TextureHashMap().get("adult_wizard0");
        thread = new Thread ( this );
        thread.start();
        offScreen = image.getGraphics();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        offScreen.fillRect ( 0, 0, 0, 0);
        g.drawImage(image, 0, y, null);
    }

    public void update(Graphics g) {
        paintComponent(g);
    }

    public void run() {
        while (true) {
            repaint();
            y+=2;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }
}
