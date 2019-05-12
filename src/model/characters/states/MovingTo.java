package model.characters.states;

import model.Game;
import model.characters.AStar;
import model.characters.Character;
import model.characters.Wizard;
import model.map.GameObject;
import model.map.Map;
import model.map.Tile;
import model.time.Animation;

public class MovingTo extends State implements Animation {
    private Tile target;
    private boolean teleport =false;
    private int direction;
    private Tile lastPos = null;
    private int minutePerTile;
    private String IDSave;

    private float animOffset = 0;

    public MovingTo(Character character, int groupID, Tile target){
        super(character,groupID);
        this.target = target;
        minutePerTile = 30/character.getSpeed();
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
        IDSave = getCharacter().getID();
        Tile target = getTarget();
        if(getCharacter() instanceof Wizard ){
            if(((Wizard)getCharacter()).getMagicPower()==100 && target!=null && target.distanceTo(getCharacter().getPos())>5){
                teleport = true;
            }
        }
    }

    @Override
    public void run() {
        Map map = Game.getInstance().getMap();
            Tile target = getTarget();
            if(target==null) {
                getCharacter().incrementHappiness(-0.2);
                if(this instanceof MovingToObjectByType){
                    String objectID="";
                    try {
                        objectID = ((GameObject)((MovingToObjectByType)this).getObjectType().newInstance()).getID();
                    } catch (IllegalAccessException e) {
                    } catch (InstantiationException e) {
                    }
                    if(Game.getInstance().getFamily().contains(getCharacter())){
                        Game.getInstance().getWindow().message("Warning, "+getCharacter().getName()+" can't find any available "+objectID+"!");
                    }
                }
                else {
                }
                cancel();
                return;
            }
            if(teleport){
                getCharacter().setID("Teleporting");
                int x = getCharacter().getPosX();
                int y = getCharacter().getPosY();
                int deltaX = target.getX()-x;
                int deltaY = target.getY()-y;
                float distance = (float) Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
                getCharacter().rotateTo(target);
                getCharacter().setPos(map.getTileAt(Math.round(x+deltaX/distance),Math.round(y+deltaY/distance)));

                if(getCharacter().getPos()==target){
                    finish();
                }
            }
            else{
                if(Game.getInstance().getTime().getMinutes()% minutePerTile ==0){//only move one tile every "tilePerMinutes" minutes
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
    }

    @Override
    public void animate(float timeFraction) {
        if(animOffset+timeFraction/ minutePerTile >0){
            animOffset=0;
            getCharacter().resetOffset();
        }
        else {
            animOffset+=timeFraction/ minutePerTile;
            getCharacter().setOffsetInDirection(animOffset,direction);
        }
        getCharacter().getPos().update();
    }

    @Override
    public void finish() {
        if(teleport){
            getCharacter().setID(IDSave);
        }
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
        if(teleport){
            getCharacter().setID(IDSave);
            getCharacter().setPos(getCharacter().getAccessTiles().get(0));
        }
        //end animation
        if(lastPos!=null){
            lastPos.removeObject(getCharacter());
        }
        getCharacter().resetOffset();
        Game.getInstance().getTime().detach(this);

        super.cancel();
    }
}
