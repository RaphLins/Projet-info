package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.*;


public class InventoryDisplay extends JPanel{
	Game game = Game.getInstance();
    TextureHashMap textures = new TextureHashMap();
    JButton buttons[] = new JButton[9];
    public InventoryDisplay() {
        setPreferredSize(new Dimension(240,240));
        setMaximumSize(new Dimension(240,240));
        setOpaque(false);
        setLayout(new GridLayout(3,3,10,10));
        for(int i = 0;i<9;i++){
            buttons[i] = new JButton();
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            //button.setBorderPainted(false);
            buttons[i].setFocusable(false);
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setPreferredSize(new Dimension(60, 60));
            add(buttons[i]);
        }
        update();

    }

    public void update(){
        GameObject selected = Game.getInstance().getSelectedObject();

        if(selected != null && selected instanceof ObjectHolder){
            ArrayList<GameObject> inventory = ((ObjectHolder)selected).getInventory();
            for(int i = 0;i<9;i++){
                if(i<inventory.size()){
                    GameObject object = inventory.get(i);
                    Image image = textures.get(object.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    buttons[i].setIcon(new ImageIcon(image));
                    buttons[i].setText(object.ID);
                    buttons[i].addActionListener(e -> {
                        if(game.getDraggedObject()==null){
                        	game.setDraggedObject(object);
                        	((ObjectHolder)selected).removeItem(object);
                        	update();
                        	System.out.println("updated");
                        }
                        else System.out.println("Place current object first");
                    });
                }
                else{
                    buttons[i].setText("");
                    buttons[i].setIcon(null);
                    buttons[i].addActionListener(e -> {
                    	if(game.getDraggedObject()==null) {
                    		Game.getInstance().itemToAdd();
                    	}
                    });
                    //for( ActionListener al : buttons[i].getActionListeners() ) {
                    //    buttons[i].removeActionListener( al );
                    //}
                }
            }
            EventQueue.invokeLater(() -> {
                updateUI();
                setVisible(true);
            });
        }
        else{
            setVisible(false);
        }
    }
}
