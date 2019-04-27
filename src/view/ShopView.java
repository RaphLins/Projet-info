package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.View;

import model.Game;
import model.GameObject;
import model.Time;
import model.TimeObserver;
import model.items.Item;
import model.items.Sellable;
import model.items.Wand;
import model.map.Wall;


public class ShopView extends JPanel{
    ArrayList<GameObject> shopObjects = new ArrayList<>();
    TextureHashMap textures = new TextureHashMap();

    public ShopView() {
        setPreferredSize(new Dimension(1660, 80));
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        shopObjects.add(new Wand());
        shopObjects.add(new Wand());

        for(GameObject object:shopObjects){
            Image image = textures.get(object.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            int price = ((Sellable)object).getPrice();
            JButton button = new JButton(object.ID+" ("+price+")",new ImageIcon(image));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            //button.setBorderPainted(false);
            button.setFocusable(false);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            button.addActionListener(e -> {
                Game game = Game.getInstance();
                        if(game.getDraggedObject()==null){
                            if(game.spendGold(price)){
                                try {
                                    game.setDraggedObject(((GameObject)object).getClass().newInstance());//create object of same class
                                } catch (InstantiationException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            else System.out.println("Not enough gold");
                        }
                        else System.out.println("Place current object first");
            });

            add(button);
        }

    }

}
