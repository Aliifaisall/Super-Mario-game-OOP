package game.item;

import game.action.Drinkable;
import game.actor.Drinker;

/**
 * Class representing a health water from a health fountain.
 */
public class HealthWater implements Drinkable {

    /**
     * allows the player to consume the water and implement the features.
     *
     * @param drinker the player consuming the water
     * @return the description that the player has consumed health water.
     */
    @Override
    public String drink(Drinker drinker){
        drinker.heal(50);
        return drinker + " has consumed health water";
    }

    @Override
    public String toString(){return "Health water";}
}
