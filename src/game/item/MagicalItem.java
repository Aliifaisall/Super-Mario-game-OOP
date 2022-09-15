package game.item;

import edu.monash.fit2099.engine.items.Item;
import game.action.Consumable;
import game.action.Resettable;
import game.Status;

/**
 * a class for magical items (Super Mushroom and Power Star)
 */
public abstract class MagicalItem extends Item implements Consumable, Resettable {

    /**
     * Constructor.
     *
     * @param name Name of the magical item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public MagicalItem(String name, char displayChar) {
        super(name, displayChar, true);
        this.registerInstance();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTED);
    }



}
