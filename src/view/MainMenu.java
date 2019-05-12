package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private Window window;

    public MainMenu(Window window){
        this.window = window;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 400)));

        addButton("Resume game").addActionListener(e->{
            window.showGame();
        });

        addButton("New game").addActionListener(e->{
            Game.getInstance().newGame();
            window.showGame();
        });

        addButton("Save game").addActionListener(e->{
            Game.getInstance().saveStateToFile("shared/saves/gameSave"+System.currentTimeMillis()+".txt");
            window.showGame();
        });

        addButton("Restore save").addActionListener(e->{
            window.setMainView(new RestoreSaveView(window));
        });

        JToggleButton button = new JToggleButton("Allow to edit all map");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setMinimumSize(new Dimension(400,40));
        button.setMaximumSize(new Dimension(400,40));

        button.addActionListener(e->{
            Game.getInstance().toggleMapEditable();
        });
        add(button);

        setVisible(true);
    }

    private JButton addButton(String text){
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(400,40));
        button.setMaximumSize(new Dimension(400,40));
        add(button);
        return button;
    }
}
