package model.characters.states;

import model.characters.Character;

public class Waiting extends State {
    private long duration;
    private long minutesPassed;

    public Waiting(Character character, int groupID, long duration) {
        super(character, groupID);
        this.duration = duration;
    }

    @Override
    public void run() {
        minutesPassed++;
        if(minutesPassed >=duration){
            finish();
        }
    }
}
