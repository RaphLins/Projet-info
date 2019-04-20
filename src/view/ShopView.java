package view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import model.Time;
import model.TimeObserver;


public class ShopView extends JPanel{
    JLabel label = new JLabel();
    public ShopView() {
        this.setPreferredSize(new Dimension(1660, 30));
        this.setBackground(new Color(182, 160, 132));
        this.setOpaque(true);
        add(label);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        label.setText("Gold: "+ Game.getInstance().getGold()+"g");
    }

    public String convert(int i) {
        return ((i/60)+":"+(i%60));
    }

}
