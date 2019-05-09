package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.Game;
import model.GameObject;
import model.items.*;
import model.map.*;
import model.places.House;


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
        shopItems.add(new Plate());
        shopItems.add(new Food());
		shopItems.add(new MagicBook());

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
            button.setPreferredSize(new Dimension(95,95));
            
            if(!(item instanceof Food || item instanceof Plate)) {
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
            			else Game.getInstance().getWindow().message("Not enough gold.");
            		}
            		else Game.getInstance().getWindow().message("Place current item first.");
            	});
            }
            
            else if(item instanceof Plate) {
            	button.addActionListener(e-> {
            		boolean res = false;
            		House familyHouse = Game.getInstance().getFamilyHouse();
            		for (Tile tile : familyHouse.getArea() ) {
            			for(GameObject o : tile.getObjects()) {
            				if(o instanceof Wardrobe && ((Wardrobe)o).getInventory().size()<9) {
            					if(Game.getInstance().spendGold(price)) {
            						Plate plate = new Plate();
            						plate.storeIn((Wardrobe)o);
            						Game.getInstance().getWindow().updateInventory();
									Game.getInstance().getWindow().message("The plate was automatically added in a Wardrobe.");
            					}
            					else {
									Game.getInstance().getWindow().message("Not enough gold.");
            					}
            					res = true;
            				}
            			}
            			if(res) break;
            		}
            		if(!res) {
						Game.getInstance().getWindow().message("No Wardrobe with enough space found.");
            		}
            	});
            }
            
            else {
            	button.addActionListener(e-> {
            		boolean res = false;
            		House familyHouse = Game.getInstance().getFamilyHouse();
            		for (Tile tile : familyHouse.getArea() ) {
            			for(GameObject o : tile.getObjects()) {
            				if(o instanceof Fridge && ((Fridge)o).getInventory().size()<9) {
            					if(Game.getInstance().spendGold(price)) {
            						Food food = new Food();
            						food.storeIn((Fridge)o);
            						Game.getInstance().getWindow().updateInventory();
									Game.getInstance().getWindow().message("The plate was automatically added in a Fridge.");

								}
            					else {
									Game.getInstance().getWindow().message("Not enough gold.");
            					}
            					res = true;
            				}
            			}
            			if(res) break;
            		}
            		if(!res) {
						Game.getInstance().getWindow().message("No fridge with available place found.");
            		}
            		
            	});
            }
            add(button);

        }
    }

}
