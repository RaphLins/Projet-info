package view;

import model.Game;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Mouse;
import model.map.GameObject;
import model.map.ObjectWithActions;
import model.characters.Character;

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
    private JLabel messageDisp = new JLabel();
    //private ActionView actionView = new ActionView();
    private InventoryDisplay inventoryDisplay = new InventoryDisplay();
    private JLabel goldDisplay = new JLabel();
    private ActionView currentActionView;
    private SocializeView currentSocializeView;
    private JButton selectAction;

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

        messageDisp.setHorizontalAlignment(SwingConstants.CENTER);
        messageDisp.setVerticalAlignment(SwingConstants.BOTTOM);
        messageDisp.setOpaque(false);
        messageDisp.setPreferredSize(new Dimension(1920,30));
        messageDisp.setFont(new Font("default", Font.BOLD, 15));

        gameView.add(mapView, BorderLayout.LINE_START);
        gameView.add(messageDisp, BorderLayout.PAGE_START);
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

        if (currentSocializeView != null) {
            rightPanel.remove(currentSocializeView);
        }
        GameObject selected = Game.getInstance().getSelectedObject();
    	if (selected instanceof ObjectWithActions && !(selected instanceof Character && !(Game.getInstance().getFamily().contains(selected)))) {
    		selectAction = new JButton("Actions");
        	selectAction.setOpaque(false);
            selectAction.setContentAreaFilled(false);
            selectAction.setFocusable(false);

            selectAction.addActionListener(e->{
            	if (!rightPanel.isAncestorOf(currentActionView)) {
            		currentActionView = new ActionView();
            		inventoryDisplay.setVisible(false);
                    rightPanel.remove(selectAction);
            		rightPanel.add(currentActionView);
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

    public void message(String text){
        messageDisp.setText(text);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                messageDisp.setText("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void showSocializeView() {
        SocializeView previousSocializeView = currentSocializeView;
        if (!rightPanel.isAncestorOf(previousSocializeView)) {
            currentSocializeView = new SocializeView();
            rightPanel.remove(selectAction);
            rightPanel.add(currentSocializeView);
            rightPanel.updateUI();
        }
    }

    public void removeSocializeView() {
        rightPanel.remove(currentSocializeView);
    }
}