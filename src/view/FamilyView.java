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
import model.characters.Character;
import model.items.Item;
import model.items.Sellable;
import model.items.Wand;
import model.map.Wall;


public class FamilyView extends JPanel{
    TextureHashMap textures = new TextureHashMap();

    public FamilyView() {
        setPreferredSize(new Dimension(1660, 80));
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        for(Character character:Game.getInstance().getFamily()){
            //Image image = textures.get(object.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JButton button = new JButton(character.ID);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            //button.setBorderPainted(false);
            button.setFocusable(false);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            button.addActionListener(e -> {
                //center view on character
            });

            add(button);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

}
