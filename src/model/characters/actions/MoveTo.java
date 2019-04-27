package model.characters.actions;

import model.Game;
import model.GameObject;
import model.characters.AStar;
import model.characters.Character;
import model.map.Map;
import model.map.Tile;

public class MoveTo extends Action{
    private Game game = Game.getInstance();
    protected Map map = game.getMap();
    Tile target = null;
    int direction;


    public MoveTo(Character character, Tile target){
        super(character);
        this.target = target;
    }

    public MoveTo(Character character, String objectType){
        super(character);
        Tile closest = map.getClosestTile(getCharacter().getPos(), objectType, 50); //finds the closest tile around the character that contains the object needed.
        if(closest==null){
            System.out.println("found none");
            character.stopEverything();
            //avoids errors if there is no such tile.
        }
        else{
            target = closest;
        }
    }//There are 2 possibilities to create an object from MoveTo : the character can be asked to go to a Tile or to a specific object (using its ID).

    public Tile getTarget(){
        return target;
    }

    @Override
    public void performAction() {	//polymorphism : the character goes somewhere if he has somewhere to go.
        Tile target = getTarget();
        if(target == null){
            direction =-1;
        }
        else {
            direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();	//AStar allows to find the best way to go to the target (it's used to find the direction where the character should go next).
        }
        if(direction==-100){
            System.out.println("No possible path");
            getCharacter().stopEverything();
        }
        else if (direction != -1) {
            Tile nextTile = map.getTileNextTo(getCharacter().getPos(), direction);	//gets the tile corresponding to the direction found with AStar.
            getCharacter().rotateTo(nextTile);
            nextTile.addObject(getCharacter());
            //for (int i = 0; i < 20; i++) {
            //    getCharacter().offSetInDirection((float) (1.0 / 20.0), direction);
            //    try {
            //        Thread.sleep((long) (50 / 5));
            //    } catch (InterruptedException e) {
            //        //e.printStackTrace();
            //    }
        //}
            getCharacter().getPos().removeObject(getCharacter());
            getCharacter().setPos(nextTile);
            getCharacter().resetOffset();
            //the involved tiles and the character's position have to be changed, because the character is now on another tile.
        }
        else {
        	//then direction = -1, as defined in the method getNextStep() in AStar. That means the character is arrived.
            System.out.println("arrived");
            actionFinished();
        }
    }
}
