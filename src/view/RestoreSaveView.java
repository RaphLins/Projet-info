package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RestoreSaveView extends JPanel {
    private Window window;

    public RestoreSaveView(Window window){
        this.window = window;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 400)));

        File folder = new File("shared/saves");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++){
            String filename = listOfFiles[i].getName();
            if(filename.startsWith("gameSave")){
                addButton(filename).addActionListener(e->{
                    try {
                        Game.getInstance().restoreStateFromFile("shared/saves/"+filename);
                    } catch (IOException e1) {
                        System.out.println("Error reading file");
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    window.showGame();
                });
            }
        }

        addButton("cancel").addActionListener(e->{
            window.showMainMenu();
        });
        setVisible(true);
    }

    private JButton addButton(String text){
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(button);
        return button;
    }
}
