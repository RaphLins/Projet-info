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
            BufferedImage tileset1 = ImageIO.read(new File("shared/res/tileset1.png"));
            BufferedImage tileset2 = ImageIO.read(new File("shared/res/tileset2.png"));
            BufferedImage tileset3 = ImageIO.read(new File("shared/res/tileset3.png"));
            BufferedImage tileset4 = ImageIO.read(new File("shared/res/tileset4.png"));
            BufferedImage tileset5 = ImageIO.read(new File("shared/res/tileset5.png"));
            textures.put("Floor",tileset1.getSubimage(199,80,20,20));
            put("Grass", items,1,0,1,2);
            textures.put("Inventory Square",items.getSubimage(0,40,60,60));
            textures.put("Wand",items.getSubimage(100,20,20,20));
            textures.put("Wall",tileset1.getSubimage(559,415,20,40));
            textures.put("Cut Wall",tileset1.getSubimage(559,415,20,25));
            textures.put("House Window",tileset1.getSubimage(79,526,20,20));
            textures.put("Wardrobe",tileset1.getSubimage(359,796,20,40));
            textures.put("Bed",tileset1.getSubimage(577,756,20,60));
            textures.put("Toilet",tileset4.getSubimage(160,220,20,40));
            textures.put("Fridge",tileset4.getSubimage(200,160,20,40));
            textures.put("Bath",tileset4.getSubimage(240,93,20,40));
            textures.put("Road",tileset1.getSubimage(10,390,20,20));
            textures.put("GroceryStore",tileset1.getSubimage(398,895,80,60));
            textures.put("Bookshelf",tileset1.getSubimage(339,756,20,40));
            textures.put("Board",tileset1.getSubimage(338,1084,60,20));
            textures.put("Stool",tileset1.getSubimage(219,836,20,20));
            textures.put("Table",tileset4.getSubimage(0,159,60,60));
            textures.put("Bench",tileset4.getSubimage(220,104,20,28));
            textures.put("Plate",tileset1.getSubimage(197,798,20,20));
            textures.put("Food",tileset1.getSubimage(437,954,20,20));
            put("White", items,4,0,1,2);
            put4("Adult Wizard (M)",characters,0,0,1,2);
            put4("Adult Wizard (F)",characters,0,2,1,2);
            put4("Child Wizard (M)",characters,0,4,1,2);
            put4("Child Wizard (F)",characters,0,6,1,2);

        } catch (IOException e) {
        }
    }

    private void put(String id, BufferedImage image, int x, int y, int w, int h){
        textures.put(id,image.getSubimage(x*W,y*H,w*W,h*H));
    }

    private void put4(String id, BufferedImage image, int x, int y, int w, int h){
        put(id,image,x,y,w,h);
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

    public BufferedImage getPart(String id, int partX, int partY){//to fix
        System.out.println(partX+ " "+partY);
        BufferedImage image = textures.get(id);
        int x = partX*W;
        int y = image.getHeight()-H*partY;
        int w = W;
        int h = image.getHeight()-y+H;
        System.out.println("first part x:" +x +" y:"+y+" w:"+w+" h:"+h);
        return image.getSubimage(x,y,W,h);
    }

    public BufferedImage getAvatar(String id){
        return get(id).getSubimage(5,9,10,10);
    }

}
