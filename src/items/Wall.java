package src.items;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall extends GameObject implements Drawable, Obstacle{

    @Override
    public Image getTexture() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/wall.jpg"));
        } catch (IOException e) {
        }
        return img;
    }
}
