package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.View;

import model.*;
import model.characters.Character;
import model.items.Item;
import model.items.Sellable;
import model.items.Wand;
import model.map.Wall;


public class InventoryDisplay extends JPanel{
    TextureHashMap textures = new TextureHashMap();
    public InventoryDisplay() {
        setSize(260, 260);
        setOpaque(false);
        setLayout(new GridLayout(3,3,10,10));
    }

    public void update(){
        removeAll();
        GameObject selected = Game.getInstance().getSelectedObject();

        if(selected != null && selected instanceof ObjectHolder){
            setVisible(true);
            ArrayList<GameObject> inventory = ((ObjectHolder)selected).getInventory();
            for(int i = 0;i<9;i++){
                JButton button = new JButton();
                if(i<inventory.size()){
                    GameObject object = inventory.get(i);
                    Image image = textures.get(object.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(image));
                    button.setText(object.ID);
                    button.addActionListener(e -> {
                        Game game = Game.getInstance();
                        if(game.getDraggedObject()==null){
                            game.setDraggedObject(object);
                            ((ObjectHolder)selected).removeItem(object);
                            update();
                        }
                        else System.out.println("Place current object first");
                    });
                }
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                //button.setBorderPainted(false);
                button.setFocusable(false);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                add(button);
            }
        }
        else{
            setVisible(false);
        }

    }
}
