package cz.cuni.mff.nagylad.model;

/**
 * Represents a simple game where player collects gems for training and with them manages a city map
 * The player can alter the city map by spending gems on different field types.
 */
public class Game {
    /**
     * The total number of gems the player has.
     */
    public int gems;
    /**
     * The city map represented as an array of strings which are mapped to field types.
     */
    public String[] city;

    /**
     * Only used when the map is not saved in the data file, initializes a new Game instance.
     * Gems and city map are set to their defaults.
     */
    public Game() {
        gems = 0;
        city = new String[100];
    }
}
