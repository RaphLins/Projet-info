package model.characters.states;

import model.Animation;
import model.Game;
import model.GameObject;
import model.characters.AStar;
import model.characters.Character;
import model.characters.Wizard;
import model.map.Map;
import model.map.Tile;
import model.places.House;

public class MovingTo extends State implements Animation {
    private Tile target;
    private boolean teleport =false;
    private int direction;
    private Tile lastPos = null;
    private static int tilePerMinute = 3;

    private float animOffset = 0;

    public MovingTo(Character character, int groupID, Tile target){
        super(character,groupID);
        this.target = target;
    }

    public Tile getTarget(){
        return target;
    }

    public void setTarget(Tile target) {
        this.target = target;
    }

    @Override
    public void init() {
        Game.getInstance().getTime().attach(this);
        super.init();
        if(getCharacter() instanceof Wizard ){
            if(((Wizard)getCharacter()).getMagicPower()==100 && target.distanceTo(getCharacter().getPos())>10){
                teleport = true;
            }
        }
    }

    @Override
    public void run() {
        if(Game.getInstance().getTime().getMinutes()%tilePerMinute==0){//only move one tile every "tilePerMinutes" minutes
            Tile target = getTarget();
            if(target==null) {
                getCharacter().incrementHappiness(-0.2);
                if(this instanceof MovingToObjectByType){
                    String objectID="";
                    try {
                        objectID = ((GameObject)((MovingToObjectByType)this).getObjectType().newInstance()).ID;
                    } catch (IllegalAccessException e) {
                    } catch (InstantiationException e) {
                    }
                    if(Game.getInstance().getFamily().contains(getCharacter())){
                        Game.getInstance().getWindow().message("Warning, "+getCharacter().getName()+" can't find any available "+objectID+"!");
                    }
                }
                else {
                    System.out.println("No possible path");
                }
                cancel();
                return;
            }
            Map map = Game.getInstance().getMap();
            direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();
            //AStar allows to find the best way to go to the target (it's used to find the direction where the character should go next).
            if(direction==-100){
                cancel();
            }
            else if (direction != -1) {
                //for animation
                if(lastPos!=null){
                    lastPos.removeObject(getCharacter());
                }
                lastPos = getCharacter().getPos();

                //main code
                Tile nextTile = map.getTileNextTo(getCharacter().getPos(), direction);	//gets the tile corresponding to the direction found with AStar.
                getCharacter().rotateTo(nextTile);
                getCharacter().setPos(nextTile);

                //for animation
                lastPos.addObject(getCharacter());
                animOffset = -1;
                getCharacter().resetOffset();
                getCharacter().setOffsetInDirection(animOffset,direction);
            }
            else {
                //then direction = -1, as defined in the method getNextStep() in AStar. That means the character is arrived.
                finish();
            }
        }
    }

    @Override
    public void animate(float timeFraction) {
        if(animOffset+timeFraction/tilePerMinute>0){
            animOffset=0;
            getCharacter().resetOffset();
        }
        else {
            animOffset+=timeFraction/tilePerMinute;
            getCharacter().setOffsetInDirection(animOffset,direction);
        }
        getCharacter().getPos().update();
    }

    @Override
    public void finish() {
        //end animation
        if(lastPos!=null){
            lastPos.removeObject(getCharacter());
        }
        getCharacter().resetOffset();
        Game.getInstance().getTime().detach(this);

        super.finish();
    }

    @Override
    public void cancel() {
        //end animation
        if(lastPos!=null){
            lastPos.removeObject(getCharacter());
        }
        getCharacter().resetOffset();
        Game.getInstance().getTime().detach(this);

        super.cancel();
    }
}
