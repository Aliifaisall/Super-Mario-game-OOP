package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.actor.Koopa;
import game.Status;

/**
 * A class that represents the tree in a Mature TreeStatus Capability.
 * @author Ali Maskari
 * @version 4
 */




public class Mature extends Tree {
    /**
     * damage attribute based on the jumo action
     */
    private int damage = 30;
    /**
     * success rate of the jump
     */
    private double successRate = 0.7;


    private int turn = 0;


    /**
     * Constructor.
     *
     * @param
     */

    public Mature() {
        this.addCapability(TreeStatus.MATURE);
        setDisplayChar('T');
    }


    /**
     * * execute method that returns void
     * it is made to let the Mature tree be able to Spawn Koopa with a 15% chance in its location if that location did not have any actors
     * and for every 5 turns it will check the  ground if it was a dirt then it will spawn a new Sprout
     * it has a 20% chance to die or become a new dirt
     *
     * @param location
     */

    @Override
    public void treeFeature(Location location) {

        boolean val = Math.random() <= 0.15;
        boolean val1 = Math.random() <= 0.5;
        // 50/50 chance of either spawning a normal Koopa or Flying Koopa
        if (val) {
            if (!location.containsAnActor()) {
                    //Spawning normal Koopa
                if (val1) {
                    Koopa koopa = new Koopa();
                    location.addActor(koopa);
                    //Flying Koopa spawns instead
                } else {
                    Koopa koopa = new Koopa("Flying Koopa", 'F', 150);
                    location.addActor(koopa);

                }

            }

            // new sprout in one of the surrounding fertile squares
            if (this.turn % 5 == 0) {
                for (Exit exit : location.getExits()) {
                    if ((exit.getDestination().containsAnActor()) && (exit.getDestination().getGround().hasCapability(Status.FERTILE))) {
                        Sprout sprout = new Sprout();
                        exit.getDestination().setGround(sprout);
                    }
                }
            }

            // wither and die with 20% and becomes a new dirt
            boolean val2 = Math.random() <= 20;
            if (val2) {
                Dirt dirt = new Dirt();
                location.setGround(dirt);
            }

        }
    }

        /**
         * thin function is to represent the tree at each turn (age)
         * increment the turn
         *
         * @param location
         */
        @Override
        public void treeAge (Location location){
            turn += 1;
        }


        @Override
        public void execute (Location location){
        }


        @Override
        public double getSuccessRate () {
            return successRate;
        }

        @Override
        public int getDamage () {
            return damage;
        }

        @Override
        public ActionList allowableActions (Actor actor, Location location, String direction){
            return new ActionList(new JumpAction(this, location, direction));
        }

        public String toString () {
            return "Mature";
        }

    }

