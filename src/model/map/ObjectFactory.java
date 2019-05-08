package model.map;

import model.Game;
import model.GameObject;
import model.characters.AdultWizard;
import model.characters.Character;
import model.characters.ChildWizard;
import model.items.Broom;
import model.items.Food;
import model.items.Plate;
import model.places.House;
import model.ObjectHolder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectFactory {
	
	private ArrayList<Character> charactersToAssign = new ArrayList<>();

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
                case "AW_M": res = new AdultWizard("M"); break;
                case "AW_F" : res = new AdultWizard("F"); break;
                case "CW_M" : res = new ChildWizard("M"); break;
                case "CW_F" : res = new ChildWizard("F"); break;
            }
        String character[] = type.split("_");
        if (character.length>=2) {
        	 String charType = character[0];
        	 String charGender = character[1];
        	 if(charType.equals("AW")) {
            	 res = new AdultWizard(charGender);             	
             }
             else if(charType.equals("CW")) {
            	 res = new ChildWizard(charGender);
             }
        	 charactersToAssign.add((Character)res);
             if(character.length>=3 && character[2].equals("fam")) {
            	 Game.getInstance().getFamily().add((Character) res);
            	 charactersToAssign.remove((Character)res);
             }
             else {
            	 (new Food()).storeIn((ObjectHolder)res);
            	 (new Plate()).storeIn((ObjectHolder)res);
             }
        }
        
        if(res ==null){
        	String split[] = type.split("-");
        	if(split.length==2){
        		type = split[0];
        		String dimensions[] = split[1].split("x");
        		int width = Integer.parseInt(dimensions[0]);
        		int height = Integer.parseInt(dimensions[1]);
        		if(!(type.equals("House"))) {
        			res = new Decoration(type,width,height);
        		}
        	}
        }
        return res;
    }
    
    public ArrayList<Character> getCharactersToAssign() {
    	return charactersToAssign;
    }
}
