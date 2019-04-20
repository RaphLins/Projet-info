package model.map;

import model.GameObject;
import model.items.Broom;

import java.util.ArrayList;

public class ObjectFactory {
    public GameObject getInstance(String type, int x, int y){
        GameObject res = null;
        switch(type) {
                case "X": res= new Wall(); break;
                case "F": res = new HouseWindow(); break;
                case "Br": res = new Broom(40); break;
                case "B" : res = new Bed(); break;
                case "M": res = new Wardrobe(); break;
            }
        return res;
    }
}
