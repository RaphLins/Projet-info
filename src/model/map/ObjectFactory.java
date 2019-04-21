package model.map;

import model.GameObject;
import model.items.Broom;

import java.util.ArrayList;

public class ObjectFactory {
    public GameObject getInstance(String type, int x, int y){
        GameObject res = null;
        switch(type) {
                case "X": res= new Wall(); break;
                case "FR": res = new Fridge(); break;
                case "Br": res = new Broom(40); break;
                case "BE" : res = new Bed(); break;
                case "WA": res = new Wardrobe(); break;
                case "WC": res = new Toilet(); break;
                case "BA": res = new Bath(); break;
            }
        return res;
    }
}
