package view;

import model.Game;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Mouse;
import model.ObjectWithActions;
import model.Time;

public class Window extends JFrame {
	private MainMenu mainMenu = new MainMenu(this);
    private JPanel gameView = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel(new BorderLayout());
    private JPanel bottomRightPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    private JPanel viewSelector = new JPanel(new GridLayout(2,1));
    private MapView mapView = new MapView();
    private StatusView statusView = new StatusView();
    private Clock clock = new Clock();
    private ShopView shopView = new ShopView();
    private FamilyView familyView = new FamilyView();
    //private ActionView actionView = new ActionView();
    private InventoryDisplay inventoryDisplay = new InventoryDisplay();
    JLabel goldDisplay = new JLabel();
    ActionView currentActionView;
    JButton selectAction;

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

        JButton selectShop = new JButton("Shop");
        selectShop.setOpaque(false);
        selectShop.setContentAreaFilled(false);
        selectShop.setFocusable(false);
        selectShop.addActionListener(e -> {
            bottomPanel.remove(familyView);
            bottomPanel.add(shopView);
            repaint();
        });
        

        gameView.add(mapView, BorderLayout.LINE_START);
        gameView.add(rightPanel, BorderLayout.LINE_END);
        gameView.add(bottomPanel, BorderLayout.PAGE_END);

        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(statusView);
        rightPanel.add(inventoryDisplay);
        rightPanel.add(Box.createVerticalGlue());
        //rightPanel.add(actionView);
        rightPanel.setBackground(new Color(  253, 235, 208 ));

        bottomPanel.add(clock, BorderLayout.LINE_END);
        bottomPanel.add(viewSelector, BorderLayout.LINE_START);
        bottomPanel.add(bottomRightPanel, BorderLayout.LINE_END);
        bottomPanel.add(familyView);
        bottomPanel.add(bottomRightPanel, BorderLayout.LINE_END);
        bottomPanel.setBackground(new Color(182, 160, 132));

        bottomRightPanel.add(goldDisplay, BorderLayout.LINE_START);
        bottomRightPanel.add(clock, BorderLayout.LINE_END);
        bottomRightPanel.setOpaque(false);

        viewSelector.add(selectFamily);
        viewSelector.add(selectShop,BorderLayout.PAGE_END);
        viewSelector.setOpaque(false);

        setMainView(gameView);
        setMainView(mainMenu);
        updateGold();
        this.setVisible(true);
    }

    public void setMainView(JPanel panel){
        this.getContentPane().removeAll();
        Game.getInstance().getTime().pause();
        this.getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public MapView getMapView(){
        return mapView;
    }

    public void setKeyListener(KeyListener keyboard) {
        addKeyListener(keyboard);
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
    
    public void updateActionView() {
    	if (selectAction!=null) {
    		rightPanel.remove(selectAction);
            rightPanel.updateUI();
    	}
    	if (currentActionView != null) {
    		rightPanel.remove(currentActionView);
    	}
    	if (Game.getInstance().getSelectedObject() instanceof ObjectWithActions) {
    		selectAction = new JButton("Actions");
        	selectAction.setOpaque(false);
            selectAction.setContentAreaFilled(false);
            selectAction.setFocusable(false);

            selectAction.addActionListener(e->{
            	ActionView previousActionView = currentActionView;
            	if (!rightPanel.isAncestorOf(previousActionView)) {
            		currentActionView = new ActionView();
            		rightPanel.add(currentActionView);
            		rightPanel.remove(selectAction);
            		rightPanel.updateUI();
            	}
            });
            selectAction.setAlignmentX(Component.CENTER_ALIGNMENT);
        	rightPanel.add(selectAction);
    	}
    	
    }

    public void attachClock(){
        Game.getInstance().getTime().attach(clock);
    }

    public void updateGold() {
        goldDisplay.setText("Gold: " + Game.getInstance().getGold() + "g");
    }

    public void showGame() {
        setMainView(gameView);
        Game.getInstance().getTime().start();
    }

    public void showMainMenu() {
        setMainView(mainMenu);
    }

    public void updateFamily(){
        familyView.update();
    }
}