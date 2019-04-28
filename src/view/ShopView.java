package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.Game;
import model.GameObject;
import model.items.Item;
import model.items.Wand;
import model.map.*;


public class ShopView extends JPanel{
    ArrayList<Item> shopItems = new ArrayList<>();
    TextureHashMap textures = new TextureHashMap();

    public ShopView() {
        setPreferredSize(new Dimension(1660, 100));
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        shopItems.add(new Wall());
        shopItems.add(new Fridge());
        shopItems.add(new Toilet());
        shopItems.add(new Bath());
        shopItems.add(new Bed());
        shopItems.add(new Bookshelf());
        shopItems.add(new Stool());
        shopItems.add(new Wand());
        shopItems.add(new Table());

        for(Item item: shopItems){
            Image image = textures.get(item.ID);
            int price = item.getPrice();
            JButton button = new JButton( "<html><center>"+item.ID+"<br>"+price+"g"+"</center></html>",new ImageIcon(image));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            //button.setBorderPainted(false);
            button.setFocusable(false);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setPreferredSize(new Dimension(80,95));

            button.addActionListener(e -> {
                Game game = Game.getInstance();
                        if(game.getDraggedObject()==null){
                            if(game.spendGold(price)){
                                try {
                                    game.setDraggedObject(((GameObject)item).getClass().newInstance());//create item of same class
                                } catch (InstantiationException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            else System.out.println("Not enough gold");
                        }
                        else System.out.println("Place current item first");
            });

            add(button);
        }

    }

}
