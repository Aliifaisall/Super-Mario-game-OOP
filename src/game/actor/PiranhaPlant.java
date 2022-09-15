package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologues;
import game.Status;
import game.action.Resettable;
import game.action.Speakable;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * a class representing a Piranha Plant
 */

public class PiranhaPlant extends Enemy implements Speakable, Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final Monologues monologue = new Monologues();
    private int turn = 0;


    /**
     * Constructor.

     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        /* Clears the behaviours HashMap for Piranha Plant, and appends only AttackBehaviour to it*/
        this.behaviours.clear();
        behaviours.put(0, new AttackBehaviour());
        this.addCapability(Status.PIRAHNA_PLANT);



        //Speak
        monologue.pushMonologue(0,"Slsstssthshs~! (Never gonna say goodbye~)");
        monologue.pushMonologue(1, "Ohmnom nom nom nom.");

    }


    /**
     * Initiates Piranha Plant's randomised monologue
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (turn % 2 == 0){

            display.println(speak());

        }

        turn += 1;
        actions.clear();

        return super.playTurn(actions, lastAction, map, display);
    }




    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    @Override
    public void resetInstance() {
        if (this.hasCapability(Status.RESETTED)) {
            increaseMaxHp(50);
            this.removeCapability(Status.RESETTED);
            this.addCapability(Status.RESETCOMPLETE);
        }
    }

    @Override
    public String speak() {
        return this + ": " + monologue.getMonologue();
    }
    public String toString(){
        return "Piranha Plant";
    }

}

