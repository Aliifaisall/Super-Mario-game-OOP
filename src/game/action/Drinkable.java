package game.action;

import game.actor.Drinker;

/**
 * An interface that is implemented by drinkable drinks.
 */
public interface Drinkable {

    /**
     * the actor drinks the drinkable
     * @param drinker actor who drinks the drink
     * @return description that actor has consumed this drink
     */
    String drink(Drinker drinker);
}
