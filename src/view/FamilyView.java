package view;

import java.awt.*;

import javax.swing.*;

import model.Game;
import model.characters.Character;


public class FamilyView extends JPanel{
    TextureHashMap textures = new TextureHashMap();

    public FamilyView() {
        setPreferredSize(new Dimension(1660, 100));
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void update(){
        removeAll();
        for(Character character:Game.getInstance().getFamily()){
            Image image = textures.getAvatar(character.ID).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JButton button = new JButton(character.ID, new ImageIcon(image));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            //button.setBorderPainted(false);
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(130,95));
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            button.addActionListener(e -> {
                Game.getInstance().selectObject(character);
            });

            add(button);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

}
