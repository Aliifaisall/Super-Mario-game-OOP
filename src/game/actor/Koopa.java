package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologues;
import game.action.DestroyShellAction;
import game.action.Speakable;
import game.behaviour.Behaviour;
import game.item.SuperMushroom;
import game.action.AttackAction;
import game.Status;


import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents both Koopa and Flying Koopa.
 * Two constructors have been made for both Koopa varieties to accommodate for their differences.
 *
 * Madeline Ting
 */

public class Koopa extends Enemy implements Speakable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final Monologues monologue = new Monologues();
    private int turn = 0;


    /**
     * Constructor for Koopa. Standard, normal Koopa.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.addCapability(Status.KOOPA_ACTIVE);
        addItemToInventory(new SuperMushroom());
        //Speak
        monologue.pushMonologue(0,"Never gonna make you cry!");
        monologue.pushMonologue(1, "Koopi koopi koopii~!");


    }

    /**
     * Constructor created for Flying Koopa.
     */
    public Koopa(String name, char displayChar, int maxHitPoints) {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.KOOPA_ACTIVE);
        this.addCapability(Status.KOOPA_FLYING);
        addItemToInventory(new SuperMushroom());

        monologue.pushMonologue(0,"Never gonna make you cry!");
        monologue.pushMonologue(1, "Koopi koopi koopii~!");
        monologue.pushMonologue(2, "Pam pam pam!");


    }

    /**
     * Allows the player to attack the enemy
     *
     * Overridden version for Koopa varieties that implements considerations for dormant states.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // if the enemy is alive in its normal state
            if (this.hasCapability(Status.KOOPA_ACTIVE)) {
                actions.add(new AttackAction(this, direction));
            } else {
                /* The player will only be able to destroy a Koopa shell if
                   they're in a dormant state and the player possesses a Wrench */
                if(this.hasCapability(Status.KOOPA_DORMANT) && (otherActor.hasCapability(Status.WRENCH_DESTROYABLE))){
                    actions.add(new DestroyShellAction(this, direction));
                }
            }
        }
        return actions;
    }



    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // speaking
        if (turn % 2 == 0){

            display.println(speak());

        }

        turn += 1;
        actions.clear();


        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }


    @Override
    public void resetInstance() {

    }

    @Override
    public String speak() {
        return this + ": " + monologue.getMonologue();
    }
    public String toString(){
        return super.name;
    }

}
