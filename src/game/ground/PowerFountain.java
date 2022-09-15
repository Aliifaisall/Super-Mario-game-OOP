package game.ground;

import game.item.Bottle;
import game.item.PowerWater;

/**
 * Class representing a fountain that is a source of Power water.
 */
public class PowerFountain extends Fountain {

    /**
     * Constructor.
     *
     */
    public PowerFountain() {
        super('A');
    }

    /**
     * Filling in the bottle with Power water from the Power fountain.
     *
     * @return description that the bottle has been filled with the water.
     */
    @Override
    public String fillWater(Bottle bottle) {
        bottle.pushDrinkable(new PowerWater());
        return "The bottle has been filled with power water";
    }

    @Override
    public String toString() {return "Power fountain";}

}
