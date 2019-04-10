package view;

import model.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Mouse;

public class Window extends JFrame {
	//private Time time = new Time(1550);
    private JPanel groupPanel = new JPanel(new BorderLayout());
    private MapView mapView = new MapView(Game.getInstance().getMap());
    private StatusView statusView = new StatusView();
    //private Clock clock = new Clock(time);

    public Window(String title) {
        super(title);
        //JFrame window = new JFrame("Game");
        //ProjectileThread thread= new ProjectileThread();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1920, 1020);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(mapView, BorderLayout.LINE_START);
        groupPanel.add(statusView, BorderLayout.LINE_END);
        //groupPanel.add(clock, BorderLayout.PAGE_START);
        //groupPanel.add(thread);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
    }

    public void update() {
        this.mapView.redraw();
        this.statusView.redraw();
    }

    public MapView getMapView(){
        return mapView;
    }

    public StatusView getStatusView(){
        return statusView;
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
    
    public JPanel getGroupPanel (){
    	return groupPanel;
    }
}