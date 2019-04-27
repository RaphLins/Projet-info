package view;

import model.Game;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import controller.Mouse;
import model.GameObject;

public class Window extends JFrame {
	//private Time time = new Time(1550);
    private JPanel groupPanel = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel(new BorderLayout());
    private JPanel bottomRightPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    private JPanel viewSelector = new JPanel(new GridLayout(2,1));
    private MapView mapView = new MapView(Game.getInstance().getMap());
    private StatusView statusView = new StatusView();
    private Clock clock = new Clock();
    private ShopView shopView = new ShopView();
    private FamilyView familyView = new FamilyView();
    private ActionView actionView = new ActionView();
    private InventoryDisplay inventoryDisplay = new InventoryDisplay();
    JLabel goldDisplay = new JLabel();

    public Window(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1920, 1020);
        this.getContentPane().setBackground(Color.gray);

        JButton selectFamily = new JButton("Family");
        selectFamily.setOpaque(false);
        selectFamily.setContentAreaFilled(false);
        selectFamily.setFocusable(false);
        selectFamily.addActionListener(e -> {
            bottomPanel.remove(shopView);
            bottomPanel.add(familyView);
            repaint();
        });
        viewSelector.add(selectFamily);

        JButton selectShop = new JButton("Shop");
        selectShop.setOpaque(false);
        selectShop.setContentAreaFilled(false);
        selectShop.setFocusable(false);
        selectShop.addActionListener(e -> {
            bottomPanel.remove(familyView);
            bottomPanel.add(shopView);
            repaint();
        });


        groupPanel.add(mapView, BorderLayout.LINE_START);
        groupPanel.add(rightPanel, BorderLayout.LINE_END);
        groupPanel.add(bottomPanel, BorderLayout.PAGE_END);

        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(statusView);
        rightPanel.add(inventoryDisplay);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(actionView);
        rightPanel.setBackground(new Color(  253, 235, 208 ));

        bottomPanel.add(clock, BorderLayout.LINE_END);
        bottomPanel.add(viewSelector, BorderLayout.LINE_START);
        bottomPanel.add(bottomRightPanel, BorderLayout.LINE_END);
        bottomPanel.add(shopView);
        bottomPanel.add(bottomRightPanel, BorderLayout.LINE_END);
        bottomPanel.setBackground(new Color(182, 160, 132));

        bottomRightPanel.add(goldDisplay, BorderLayout.LINE_START);
        bottomRightPanel.add(clock, BorderLayout.LINE_END);
        bottomRightPanel.setOpaque(false);

        viewSelector.add(selectShop,BorderLayout.PAGE_END);
        viewSelector.setOpaque(false);

        this.getContentPane().add(this.groupPanel);
        updateGold();
        this.setVisible(true);
    }

    public MapView getMapView(){
        return mapView;
    }

    public void setKeyListener(KeyListener keyboard) {
        this.mapView.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.mapView.addMouse(m);
    }

    public void updateTile(int x, int y) {
        mapView.updateTile(x,y);
    }

    public void updateInventory(){
        inventoryDisplay.update();
    }

    public void updateStatus(){
        statusView.repaint();
    }



    public void updateGold() {
        goldDisplay.setText("Gold: " + Game.getInstance().getGold() + "g");
    }
}