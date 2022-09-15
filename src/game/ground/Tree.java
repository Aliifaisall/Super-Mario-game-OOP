package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.action.Resettable;
import game.actor.Goomba;
import game.actor.Koopa;
import game.Status;
import game.item.Coin;
import game.item.FireFlower;

import java.util.Random;


/**
 *
 * a tree at the beginning of the game is
 * @author : Ali Maskari
 * @version : 3.0.0
 */

public abstract class Tree extends HighGround implements Resettable {
    /**
     * Private int age which represents the age of the tree with respect to the ground
     */
    private int age;

    public int getAge() {return this.age;}


    /**
     * Constructor.
     *
     *
     * @param
     */
    public Tree() {
        super('+');
        this.registerInstance();
    }

    /**
     * this tick method is representing the tree in each turn to experience the passing of time , which it
     * has two methods treeFeature and treeAge that will present the tree at each stage
     * @param location The location of the Ground
     */


    public void tick(Location location) {

        if ((this.hasCapability(Status.RESETTED)) && (Math.random() <= 0.5)){
            location.setGround(new Dirt());
            this.removeCapability(Status.RESETTED);
            this.addCapability(Status.RESETCOMPLETE);
        }

        this.treeFeature(location);

        this.treeAge(location);



    }

    /**
     * this funtion is called inside the tree tick method that is meant to be used by all the other tree status
     * @param location
     */

    public abstract void treeFeature(Location location);

    /**
     * thin function is to represent the tree at each turn (age)
     * at age 10 the ground will be sapling
     * at age 20 the ground will be sprout
     * at age 30 the ground will be mature
     * @param location
     */

    public abstract void treeAge(Location location);

    @Override
    public void resetInstance(){
        this.addCapability(Status.RESETTED);
    }


    public abstract void execute(Location location);

}

