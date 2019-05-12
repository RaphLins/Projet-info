package view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.map.GameObject;
import model.Game;
import model.characters.Character;
import model.map.Tile;

public class SocializeView extends JPanel{
    TextureHashMap textures = new TextureHashMap();
    Character c;
    public SocializeView() {
        setPreferredSize(new Dimension(260, 50));
        GameObject selected = Game.getInstance().getSelectedObject();
        if(selected instanceof Character && Game.getInstance().getFamily().contains(selected)) {
            c = (Character)selected;
            setLayout(new GridLayout(10,1));
            add(new JLabel("Who do you want to socialize with?"));
            ArrayList<Tile> tilesAround = Game.getInstance().getMap().getNearbyTiles(c.getPos(),25);
            ArrayList<Character> acquaintances = new ArrayList<>();
            for(Tile tile:tilesAround){
                for(GameObject object:tile.getObjects()){
                    if(object instanceof Character){
                        Character acquaintance = (Character)object;
                        if(!Game.getInstance().getFamily().contains(acquaintance) && !acquaintances.contains(acquaintance)){
                            acquaintances.add(acquaintance);
                            addButton(acquaintance);
                        }
                    }
                }
            }
        }
        setOpaque(false);
    }
    private JButton addButton(Character acquaintance){
        JButton button = new JButton(acquaintance.getName()+" (Relation level "+c.getRelationLevel(acquaintance)+")");
        button.setIcon(new ImageIcon(textures.getAvatar(acquaintance.getID())));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.addActionListener(e -> {
            c.stopEverything();
            acquaintance.stopEverything();
            c.socialize(acquaintance);
            acquaintance.socialize(c);
            Game.getInstance().getWindow().removeSocializeView();
        });
        add(button);
        return button;
    }

}
