package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.item.Coin;
import game.item.FireFlower;
/**
 * A class that represents the tree in a Sapling TreeStatus Capability.
 * @author Ali Maskari
 * @version 4
 */
public class Sapling extends Tree {

    private int turn = 0;
    /**
     * damage attribute based on the jumo action
     */

    private int damage = 20;
    /**
     * success rate of the jump
     */
    private double successRate = 0.8;
    /**
     * Constructor
     *
     * @param
     */
    public Sapling() {
        this.addCapability(TreeStatus.SAPLING);
        setDisplayChar('t');

    }

    /**
     * spawns a coin in its location
     * @param location
     */


    @Override
    public void treeFeature(Location location) {
        if (new java.util.Random().nextInt(10) == 0 && !location.containsAnActor()) {
            location.addItem(new Coin());
        }
    }

    /**
     * thin function is to represent the tree at each turn (age), will transform after 10 rounds from Sprout
     * has a 50% chance of spawning fire flower
     *
     *
     * @param location
     */
    @Override
    public void treeAge(Location location){
        if (turn == 10){
            location.setGround(new Mature());
            if (Math.random() <= 0.5){
                location.addItem(new FireFlower());
            }
        }

        turn += 1;
    }

    /**
     *
     * @param location
     */

    @Override
    public void execute(Location location) {

    }

    /**
     *
     * @return
     */

    @Override
    public double getSuccessRate() {
        return successRate;
    }

    /**
     *
     * @return
     */

    @Override
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        return new ActionList(new JumpAction(this, location, direction));
    }

    /**
     *
     * @return
     */

    public String toString(){
        return "Sapling";
    }

}


