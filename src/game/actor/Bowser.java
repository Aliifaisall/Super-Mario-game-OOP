package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologues;
import game.Status;
import game.action.Speakable;
import game.behaviour.Behaviour;
import game.item.Key;

import java.util.HashMap;
import java.util.Map;

/**
 * The class representing Bowser
 * @Author: Ali Maskari
 * @version: 2.2
 */

public class Bowser extends Enemy implements Speakable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * an instance of type monologue is instantiated
     */
    private final Monologues monologue = new Monologues();
    /**
     * turn of type int that is initialized with a value of zero
     */
    private int turn = 0;

    /**
     * Constructor.
     * contains all of Bowser's Monologue and characteristics
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.addCapability(Status.BOWSER_FIRE_POWER);
        behaviours.remove(1);
        addItemToInventory(new Key());

        // monologue
        monologue.pushMonologue(0,"What was that sound? Oh, just a fire.");
        monologue.pushMonologue(1, "Princess Peach! You are formally invited... to the creation of my new kingdom!");
        monologue.pushMonologue(2,"Never gonna let you down!");
        monologue.pushMonologue(3,"Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");

    }

    /**
     * Bowser Speaking will be done every next turn , he will shout monologue randomly
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


    /**
     *
     * @return bowsers intrinsic weapon
     */

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    /**
     * reset instance allows boweser to be affected when the game is retested
     */

    @Override
    public void resetInstance() {

    }

    /**
     *
     * @return Monologue
     */

    @Override
    public String speak() {
        return this + ": " + monologue.getMonologue();    }

    public String toString()
    {
        return "Bowser";
    }
}
