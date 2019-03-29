package model.items;

import model.GameObject;
import model.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall extends GameObject implements Obstacle{

    public Wall(Tile pos) {
        super(pos);
    }

}
