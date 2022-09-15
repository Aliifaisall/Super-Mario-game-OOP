package game.item;

import game.action.Drinkable;
import game.actor.Drinker;

/**
 * Class representing a power water from a power fountain
 */
public class PowerWater implements Drinkable {

    /**
     * allows the player to consume the water and implement the features.
     *
     * @param drinker the player consuming the water
     * @return the description that the player has consumed health water.
     */
    @Override
    public String drink(Drinker drinker){
        drinker.increaseAttack(15);
        return drinker + " has consumed power water";

    }

    @Override
    public String toString(){return "Power water";}
}
