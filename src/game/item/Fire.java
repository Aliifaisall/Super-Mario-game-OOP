package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * This class represents the Fire that Bowser drops per attack.
 *
 *
 */
public class Fire extends Item {


    /**
     * a counter ticking when the item is on the ground.
     */
    private int counterGround;


    /***
     * Constructor.
     *
     */
    public Fire() {
        super("Fire", 'v', false
        );
    }

    /**
     * Deals 20 damage to anyone who is standing on the fire.
     *
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (currentLocation.containsAnActor()){
            actor.hurt(20);
        }
    }

    /**
     * the fire will stay on the ground for three turns
     */

    @Override
    public void tick(Location location){
        counterGround +=1;
        if(counterGround == 3 || this.hasCapability(Status.RESETTED)){
            location.removeItem(this);
        }

    }






}
