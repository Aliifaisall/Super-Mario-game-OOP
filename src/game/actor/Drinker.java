package game.actor;

/**
 * An interface that is implemented by the player who can drink.
 */
public interface Drinker {

    /**
     * heal the player
     * @param point the amount that the player will heal by
     */
    void heal(int point);

    /**
     * increase the base/intrinsic attack damage
     * @param point the amount of increasing attack damage
     */
    void increaseAttack(int point);

    String toString();
}
