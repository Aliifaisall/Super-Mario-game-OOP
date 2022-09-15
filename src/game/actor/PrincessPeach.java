package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologues;
import game.action.EndGameAction;
import game.action.Speakable;


/**
 * a class representing Princess Peach
 */
public class PrincessPeach extends Actor implements Speakable {

    private final Monologues monologue = new Monologues();
    private int turn = 0;




    /**
     * Constructor.
     *
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 99999);
        monologue.pushMonologue(0, "Dear Mario, I'll be waiting for you..");
        monologue.pushMonologue(1, "Never gonna give you up!");
        monologue.pushMonologue(2, "Release me, or I will kick you!");
    }

    /**
     * Controls the actions that can be performed by Princess Peach per turn.
     * Mainly controls her speaking options.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (turn % 2 == 0){

            display.println(speak());

        }

        turn += 1;
        actions.clear();
       return lastAction;
    }

    /**
     * This method is used to sense when the player (otherActor) is around the vicinity of Princess Peach and
     * possesses a key in their inventory.
     * When this happens, the actionList will be cleared, and the EndGameAction will be added to the actionList for
     * the player to free Peach, resulting in the end of the game.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */


    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        for (Exit exit : map.locationOf(otherActor).getExits()) {

            if (exit.getDestination().getActor() == this && otherActor.getInventory().toString().contains("Key")) {
                actionList.add(new EndGameAction());
            }
        }
        return actionList;
    }


    @Override
    public String speak() {
        return this + ": " + monologue.getMonologue();
    }

    public String toString(){
        return "Princess Peach";
    }
}
