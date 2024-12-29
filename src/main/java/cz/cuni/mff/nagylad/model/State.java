package cz.cuni.mff.nagylad.model;

import java.util.List;

/**
 * Represents the application's global state, including all sets and game-related data.
 * The state is used to store and manage the application's persistent data.
 */
public class State {

    /** The list of all sets created by the user. */
    public List<Set> sets;

    /** The current game state, including city and gem data. */
    public Game game;
}


