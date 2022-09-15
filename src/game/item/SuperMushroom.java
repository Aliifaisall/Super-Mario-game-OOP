package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.Status;

/**
 * A class for Super Mushroom
 */
public class SuperMushroom extends MagicalItem {

    /**
     * A counter ticking when the item is carried by the Actor.
     */
    private int counter;

    private int value = 400;

    /**
     * Consume action.
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^');
        counter = 0;
    }

    /**
     * The actor consumes Super Mushroom.
     *
     * @param actor Actor consuming the item.
     * @return String indicating that the Actor has consumed the item (e.g. Mario consumed Super Mushroom.)
     */
    @Override
    public String consume(Actor actor) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
        actor.addCapability(Status.CONSUMED_SUPERMUSHROOM);
        return actor + " consumed Super Mushroom.";
    }

    /**
     * Create and return an action to drop this Item.
     * If this Item is not portable, returns null.
     *
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    public DropItemAction getDropAction(Actor actor) {
        if (!actor.hasCapability(Status.TALL)) {
            this.removeAction(consumeAction);
            return new DropItemAction(this);
        }
        return null;
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESETTED)){
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESETTED);
            this.addCapability((Status.RESETCOMPLETE));
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
        if (actor.hasCapability(Status.CONSUMED_SUPERMUSHROOM)){
            if (this.hasCapability(Status.RESETTED)){
                actor.removeCapability(Status.TALL);
                actor.removeItemFromInventory(this);
                actor.removeCapability(Status.CONSUMED_SUPERMUSHROOM);
                this.removeCapability(Status.RESETTED);
                this.addCapability(Status.RESETCOMPLETE);
            } else if (!actor.hasCapability(Status.TALL)){
                actor.removeItemFromInventory(this);
                actor.removeCapability(Status.CONSUMED_SUPERMUSHROOM);
            }
        } else {
            this.addAction(consumeAction);}
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpItemAction(this);
    }

    public String toString(){return "Super Mushroom";}
}