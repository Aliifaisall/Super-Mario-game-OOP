package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.PurchaseAction;
import game.action.SpeakAction;
import game.action.Speakable;
import game.item.PowerStar;
import game.item.SuperMushroom;
import game.item.Wrench;

import game.Monologues;
import game.Status;


import java.util.Random;


/**
 * class created to represent toad, an npc in the game
 * @author Ali Maskari
 * @version 4
 */


public class Toad extends Actor implements Speakable {

    /**
     * an instance of the monologue class
     */
    private Monologues monologue = new Monologues();
    /**
     * turn of type int that is initialized with a value of zero
     */
    private int turn = 0;

    /**
     * an instance of the player
     */

    private Player player = new Player("Mario", 'm',9);

    /**
     * Constructor.
     *
     */
    public Toad() {
        super("Toad", 'O', 99999);
        addMessages();
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
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
     * a method that add messages to the ArrayList of messages 
     */
    private void addMessages(){

        monologue.pushMonologue(0,"You might need a wrench to smash Koopa's hard shells.");
        monologue.pushMonologue(1,"You better get back to finding the Power Stars.");
        monologue.pushMonologue(2, "The Princess is depending on you! You are our only hope.");
        monologue.pushMonologue(3, "Being imprisoned in these walls can drive a fungus crazy :(");

    }

    /**
     * this method is used to look for the player if Toad was with in any of the exits the player can got to to execute the action of purchasing
     * powerstar , Wrench or SuperMashroom
     *
     * if not toad will not be able to talk unless if he was in the players exits
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        for (Exit exit : map.locationOf(otherActor).getExits()) {

            if (exit.getDestination().getActor() == this) {
                actionList.add(new SpeakAction(this, monologue));
                actionList.add(new PurchaseAction(otherActor, new PowerStar(), 600));
                actionList.add(new PurchaseAction(otherActor, new Wrench(), 200));
                actionList.add(new PurchaseAction(otherActor, new SuperMushroom(), 400));
                }
            }
        return actionList;
        }

    /**
     * the speak action will be performed based on the actors positioning of the weapon wrench
     * or if he consumed the power star
     * @return
     */


    @Override
    public String speak() {

        if(player.getWeapon().toString() == "wrench") {
            monologue.removeMonologue(0);
        }
        if(player.hasCapability(Status.CONSUMED_POWERSTAR)){
            monologue.removeMonologue(1);
        }
        String message = monologue.getMonologue();
        monologue.clearMonologue();
        addMessages();

        return this + ": " + message;
    }
    public String toString(){
        return "Toad";
    }
}
