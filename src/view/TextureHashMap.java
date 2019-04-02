package view;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TextureHashMap {
    int W = MapView.TILE_WIDTH;
    int H = MapView.TILE_HEIGHT;
    HashMap<String,BufferedImage> textures= new HashMap<>();
    public TextureHashMap(){
        try {
            BufferedImage characters = ImageIO.read(new File("shared/res/characters.png"));
            BufferedImage items = ImageIO.read(new File("shared/res/items.png"));
            BufferedImage tileset = ImageIO.read(new File("shared/res/tileset.png"));
            textures.put("floor",tileset.getSubimage(199,80,20,20));
            put("grass", items,1,0,1,2);
            textures.put("wall",tileset.getSubimage(479,418,20,40));
            textures.put("house_window",tileset.getSubimage(79,526,20,20));
            textures.put("wardrobe",tileset.getSubimage(359,796,20,40));
            put("white", items,4,0,1,2);
            put4("adult_wizard",characters,0,0,1,2);

        } catch (IOException e) {
        }
    }

    private void put(String id, BufferedImage image, int x, int y, int w, int h){
        textures.put(id,image.getSubimage(x*W,y*H,w*W,h*H));
    }

    private void put4(String id, BufferedImage image, int x, int y, int w, int h){
        put(id+"3",image,x,y,w,h);
        put(id+"1",image,x+1,y,w,h);
        put(id+"0",image,x+2,y,w,h);

        image = image.getSubimage((x+2)*W,y*H,w*W,h*H);
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        textures.put(id+"2",image);
    }

    public BufferedImage get(String id){
        return textures.get(id);
    }


}
