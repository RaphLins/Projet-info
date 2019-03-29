package src.view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

public class Window {
    private Map map;

    public Window() {
        map = new Map("map.txt");

        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, map.WINDOW_PIXEL_WIDTH, Map.WINDOW_PIXEL_HEIGHT+35);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void update() {
        this.map.redraw();
    }
}
