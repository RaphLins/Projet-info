package model.map;

import model.GameObject;
import model.items.Broom;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectFactory {



    public GameObject getInstance(String type){
        GameObject res = null;

        switch(type) {
                case "X": res= new Wall(); break;
                case "Fr": res = new Fridge(); break;
                case "Br": res = new Broom(); break;
                case "Be" : res = new Bed(); break;
                case "Wa": res = new Wardrobe(); break;
                case "Wc": res = new Toilet(); break;
                case "Ba": res = new Bath(); break;
                case "Ta": res = new Table(); break;
                case "St": res = new Stool(); break;
                case "Ben": res = new Bench(); break;
                case "Bo": res = new Bookshelf(); break;
            }
            if(res ==null){
                String split[] = type.split("-");
                if(split.length==2){
                    type = split[0];
                    String dimensions[] = split[1].split("x");
                    int width = Integer.parseInt(dimensions[0]);
                    int height = Integer.parseInt(dimensions[1]);
                    res = new Decoration(type,width,height);
                }
            }
        return res;
    }
}
