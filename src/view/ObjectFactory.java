package src.view;

import src.items.GameObject;
import src.items.Wall;

public class ObjectFactory {
    public GameObject getInstance(char type){
        GameObject res = null;
        switch(type) {
            case '*': res = new Wall(); break;
        }
        return res;
    }
}
