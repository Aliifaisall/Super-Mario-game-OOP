package game.ground;

import game.item.Bottle;
import game.item.HealthWater;

/**
 * Class representing a fountain that is a source of Health water.
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor.
     *
     */
    public HealthFountain() {
        super('H');
    }

    /**
     * Filling in the bottle with Health water from the Health fountain.
     *
     * @return description that the bottle has been filled with the water.
     */
    @Override
    public String fillWater(Bottle bottle) {
        bottle.pushDrinkable(new HealthWater());
        return "The bottle has been filled with health water";
    }

    @Override
    public String toString(){return "Health fountain";}

}
