package src.view;

import java.awt.Color;

import javax.swing.JFrame;

public class Window {
    private Map map = new Map();

    public Window() {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, map.WIDTH, map.HEIGHT+35);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void update() {
        this.map.redraw();
    }
}
