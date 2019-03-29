package view;

import model.GameObject;
import model.items.Wall;

public class ObjectFactory {
    public GameObject getInstance(char type){
        GameObject res = null;
        switch(type) {
            case '*': res = null; break;
        }
        return res;
    }
}
