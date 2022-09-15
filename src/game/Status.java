package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    FERTILE, // use this status for dirt to be considered as fertile ground
    CONSUMED_POWERSTAR,// use this status to tell that current instance has consumed power star.
    KOOPA_DORMANT, // used to show Koopa's "dormant" state, where they're rendered "behaviour-free"
    KOOPA_ACTIVE, // used to show Koopa's active and normal functioning state
    KOOPA_FLYING, // used to differentiate Flying Koopa from Koopa
    BOWSER_FIRE_POWER, // used to represent Bowser's fire attack
    PIRAHNA_PLANT, // used to represent the Pirahna Plant
    WRENCH_DESTROYABLE, // the player will have this status when they are in possession of the wrench
    END_GAME, // used to mark the end of game, status is inflicted onto Mario
    CONSUMED_SUPERMUSHROOM, // use this status to tell the current instance has consumed super mushroom.
    RESETTED, // use this status to tell that the game will be resetted.
    RESETCOMPLETE, // use this status to mark the game as resetted before.
    FIRE_ATTACK,// used when the fire flower is consumed to give an actor an
    DRINK_BOTTLE, // use this status to give permission to the actor who can drink the drink in the bottle.
}