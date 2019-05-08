package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.*;
import model.characters.Character;
import model.items.HoldableItem;


public class InventoryDisplay extends JPanel{
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
        if (selected instanceof Character && !(Game.getInstance().getFamily().contains(selected))) {
        	return;
        }

        if(selected != null && selected instanceof ObjectHolder){
            ArrayList<HoldableItem> inventory = ((ObjectHolder)selected).getInventory();
            for(int i = 0;i<9;i++){
                for( ActionListener al : buttons[i].getActionListeners() ) {//remove all actions on the button
                    buttons[i].removeActionListener( al );
                }
                if(i<inventory.size()){
                    HoldableItem item = inventory.get(i);
                    Image image = textures.get(item.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    buttons[i].setIcon(new ImageIcon(image));
                    buttons[i].setText(item.ID);
                    buttons[i].addActionListener(e -> {//allows to move an item from inventory onto the map
                        if(Game.getInstance().getDraggedObject()==null){
                            Game.getInstance().setDraggedObject(item);
                            ((ObjectHolder)selected).getInventory().remove(item);
                            update();
                        }
                        else System.out.println("Place current object first");
                    });
                }
                else{
                    buttons[i].setText("");
                    buttons[i].setIcon(null);
                    buttons[i].addActionListener(e -> {
                    	if(Game.getInstance().getDraggedObject()==null) {
                    		Game.getInstance().itemToAdd(); //tell the Game that the next item clicked should be added to iventory
                    	}
                    });

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
