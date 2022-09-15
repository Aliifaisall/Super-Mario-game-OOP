package game.actor;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Monologues;
import game.action.AttackAction;
import game.action.DestroyShellAction;
import game.behaviour.*;
import game.action.Resettable;
import game.Status;
import game.ground.Dirt;
import game.item.SuperMushroom;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * a base class for all enemies
 */
public abstract class Enemy extends Actor implements Printable, Resettable {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();


    private Random rand = new Random();

    public Player target;




    /**
     * an attackbehaviour object, this stores the attackbehaviour available to the enemy
     */


    /**
     * Constructor.
     * @param name the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param maxHitPoints maximum hit points
     */

    public Enemy(String name, char displayChar, int maxHitPoints) {
        super(name, displayChar, maxHitPoints);
        this.registerInstance();
        behaviours.put(0, new AttackBehaviour());
        behaviours.put(1, new WanderBehaviour());
        behaviours.put(2, new FollowBehaviour(target));
    }


    /**
     * Allows the player to attack the Enemy when they're within its surrounding area.
     *
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        //adds the DestroyShellAction if the player possesses a wrench and the opponent is in a dormant state
        if(otherActor.hasCapability(Status.WRENCH_DESTROYABLE) && this.hasCapability(Status.KOOPA_DORMANT)) {
            actions.add(new DestroyShellAction(this,direction));
        }
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }

        return actions;
    }

    /**
     * a method that allows enemies to use their behaviours ("actions").
     * checks have been placed within this method to only allow some enemies to use limited behaviours (For example,
     * Piranha plant cannot move nor follow the player)
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        //reset
        if (this.hasCapability(Status.RESETTED)){
            map.removeActor(this);
        }

        //limits Piranha Plant to only utilising AttackBehaviour
        if (this.hasCapability(Status.PIRAHNA_PLANT)) {
            behaviours.remove(1);
            behaviours.remove(2);
        }


        //Bowser is not allowed to wander the map with WanderBehaviour
        if (this.hasCapability(Status.BOWSER_FIRE_POWER)) {
            behaviours.remove(1);
        }


        //Checks whether a Koopa variety (Koopa or Flying Koopa) is in their dormant state.
        //If so, removes all behaviours, rendering it immobile and incapable of attacking
        //sets their display character to 'D'
        if (this.hasCapability(Status.KOOPA_DORMANT)) {
            behaviours.clear();
            this.setDisplayChar('D');
        }



            for (Behaviour behaviour : behaviours.values()){


                Action action = behaviour.getAction(this, map);
                if (action != null){
                    return action;
                }
            }
        return new DoNothingAction();
    }





}
