package game.item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;

import game.Status;

/**
 * A class for Power Star
 */
public class PowerStar extends MagicalItem {

    /**
     * a counter ticking when the item is on the ground.
     */
    private int counterGround;

    /**
     * a counter ticking when the item is carried by the player.
     */
    private int counterPlayer;

    /**
     * a counter ticking when the item is consumed by the player.
     */
    private int counterConsumed;

    /**
     * Consume action.
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * Constructor.
     *
     */
    public PowerStar() {
        super("Power Star", '*');
    }

    /**
     * The actor consumes Power Star.
     *
     * @param actor Actor consuming the item.
     * @return String indicating that the Actor has consumed the item (e.g. Mario consumed Power Star.)
     */
    @Override
    public String consume(Actor actor){
        if (!actor.hasCapability(Status.CONSUMED_POWERSTAR)){
            actor.heal(200);
            actor.removeCapability(Status.HOSTILE_TO_ENEMY);
            actor.addCapability(Status.CONSUMED_POWERSTAR);}
        return actor + " consumed Power Star";
    };

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param location The location of the ground on which we lie.
     */
    @Override
    public void tick(Location location) {
        counterGround += 1;
        if ((counterGround == 10) || (this.hasCapability(Status.RESETTED))){
            location.removeItem(this);
        }
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
        Display display = new Display();
        int turn = (10 - counterConsumed);
        if (actor.hasCapability(Status.CONSUMED_POWERSTAR)) {
            if (actor.hasCapability(Status.RESETTED)) {
                actor.removeItemFromInventory(this);
                actor.addCapability(Status.HOSTILE_TO_ENEMY);
                actor.removeCapability(Status.CONSUMED_POWERSTAR);
                actor.hasCapability(Status.RESETCOMPLETE);
                actor.removeCapability(Status.RESETTED);
            } else if (counterConsumed == 10) {
                actor.removeItemFromInventory(this);
                actor.addCapability(Status.HOSTILE_TO_ENEMY);
                actor.removeCapability(Status.CONSUMED_POWERSTAR);
            } else {
                display.println("MARIO IS INVINCIBLE");
                display.println("Mario consumes Power Star - " + turn + " turns remaining");

            }
        } else {
            if (counterPlayer + counterGround == 10) {
                actor.removeItemFromInventory(this);
            } else if (actor.hasCapability(Status.RESETTED)) {
                actor.removeItemFromInventory(this);
                actor.hasCapability(Status.RESETCOMPLETE);
                actor.removeCapability(Status.RESETTED);
            } else {
                this.addAction(consumeAction);
            }
        }
        counterPlayer += 1;
        counterConsumed += 1;
    }

    /**
     * Create and return an action to drop this Item.
     * If this Item is not portable, returns null.
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (!actor.hasCapability(Status.CONSUMED_POWERSTAR)){
            this.removeAction(consumeAction);
            return new DropItemAction(this);}
        return null;
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        counterPlayer = 0;
        counterConsumed = 0;
        return new PickUpItemAction(this);
    }

    @Override
    public String toString(){return "Power Star";}

}