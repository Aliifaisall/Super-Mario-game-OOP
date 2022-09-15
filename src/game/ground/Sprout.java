package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.actor.Goomba;
import game.item.FireFlower;

/**
 * a class the represents the Sprout
 * @author  Ali Maskari
 * @version 3.0.0
 */

public class Sprout extends Tree {
    /**
     * damage attribute based on the jumo action
     */

    private int damage = 10;
    /**
     * success rate of the jump
     */
    private double successRate = 0.9;

    private int turn = 0;

    public Sprout() {
        this.addCapability(TreeStatus.SPROUT);
        setDisplayChar('+');

    }

    /**
     * Sprout tree feature that it can spawn an enemy Goomba in its location with a 10% chance
     *
     * @param location of the sprout that goomba will b e spawned at
     */

    @Override
    public void treeFeature(Location location) {

        if (Math.random() <= 0.1) {
            if (!location.containsAnActor()) {
                 location.addActor(new Goomba());
            }

        }
    }

    /**
     * tree age that will be transformed into sprout at the 10th round
     * it has a 50% chance of spawning a fire flower
     *
     * @param location
     */

    @Override
    public void treeAge(Location location){
        if (turn == 10){
            location.setGround(new Sapling());
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
     * @return Sprout
     */

    public String toString(){
        return "Sprout";
    }


}


