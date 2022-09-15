package game.item;

import java.util.Stack;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.Consumable;
import game.action.ConsumeAction;
import game.action.Drinkable;
import game.actor.Drinker;

/**
 * Class representing a bottle that the player possesses.
 */
public class Bottle extends Item implements Consumable {

    /**
     * A stack for keeping the waters
     */
    private Stack<Drinkable> waters = new Stack<>();

    /**
     * Consume action dedicated to the bottle
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * drinker that drinks the drink in the bottle
     */
    private Drinker drinker;

    /**
     * Constructor.
     *
     */
    public Bottle(Drinker drinker) {
        super("Bottle", 'b', false);
        this.drinker = drinker;
    }
    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (waters.isEmpty()){
            this.removeAction(consumeAction);
        }
    }

    @Override
    public String consume(Actor actor) {
        Drinkable drinkable = waters.pop();
        drinkable.drink(drinker);

        return actor + " consumed " + drinkable + " in the bottle";
    }

    /**
     * Pushes drinkable (keeps the drink) into the bottle
     *
     * @param drinkable the drink to be kept in the bottle
     */
    public void pushDrinkable(Drinkable drinkable){
        if (waters.isEmpty()){
            waters.push(drinkable);
            this.addAction(consumeAction);
        } else {
            waters.push(drinkable);
        }
    }

    @Override
    public String toString(){
        return "Bottle" + waters.toString();
    }
}