package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.action.Consumable;
import game.action.ConsumeAction;

public class FireFlower extends Item implements Consumable {


    /**
     * a counter ticking when the item is consumed by the player.
     */
    private int counterConsumed;

    /**
     * when an actor consumes the flower
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);
    /***
     * Constructor.
     */
    public FireFlower() {
        super("Fire Flower", 'f', true);
    }

    /**
     * Allows any classes that use this interface to implement features when it is consumed by the Actor (player).
     *
     * @param actor Actor consuming the item.
     * @return String indicating that the Actor has consumed the item (e.g. Mario consumed Power Star.)
     */
    @Override
    public String consume(Actor actor) {
        if(!actor.hasCapability(Status.FIRE_ATTACK))
        {
            actor.addCapability(Status.FIRE_ATTACK);
            actor.removeItemFromInventory(this);
        }
        return actor +" has Fire Attack";
    }




    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */

    @Override
    public void tick(Location currentLocation, Actor actor){

        Display display = new Display();

        int Turn = (20 - counterConsumed);
        if(actor.hasCapability(Status.FIRE_ATTACK)){
            if(actor.hasCapability(Status.RESETTED)){
                actor.removeCapability(Status.FIRE_ATTACK);
                actor.hasCapability(Status.RESETCOMPLETE);
            }else if(counterConsumed == 20){
                actor.removeCapability(Status.FIRE_ATTACK);
            }
        }else {
            display.println("MARIO HAS FIRE ATTACK");
            display.println("Mario consumer Fire Flower -" + Turn +" turns remaining till it fades away");

        }
        this.addAction(consumeAction);
        counterConsumed +=1;
    }

}

