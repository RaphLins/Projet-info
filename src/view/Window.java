package view;

import model.GameObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;
import model.Map;
import model.characters.Character;

public class Window extends JFrame {
    private JPanel groupPanel = new JPanel(new BorderLayout());
    private MapView mapView = new MapView(new Map());
    private StatusView statusView = new StatusView();

    public Window(String title) {
        super(title);
        // JFrame window = new JFrame("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1000, 1020);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(mapView, BorderLayout.LINE_START);
        groupPanel.add(statusView, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
    }

    public void update() {
        this.mapView.redraw();
        this.statusView.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.mapView.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.mapView.addMouse(m);
    }

    public void setPlayer(Character p) {
        statusView.setPlayer(p);
    }
}