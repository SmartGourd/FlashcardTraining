package cz.cuni.mff.nagylad.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Takes care of storing, saving and loading the application state.
 * Is distributed to pages which modify the state property
 */
public class AppState {
    /**
     * Used to store the state of the application data, is serialized to Json upon saving and loaded by deserializing from JSON on loading
     */
    public State state;

    /**
     * Used to serialize and deserialize to JSON
     */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Create new App state instance with no state, which is loaded later
     * The state is not loaded in the constructor because it is an IO operation which may take long time or fail
     */
    public AppState() {
        this.state = null;
    }

    /**
     * Saves the current state to a JSON file.
     *
     * @param filePath Path to save the JSON file.
     * @throws IOException if an error occurs during saving.
     */
    public void save(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this.state, writer);
        }
    }

    /**
     * Loads the state from a JSON file.
     *
     * @param filePath Path of the JSON file to load.
     * @throws IOException if an error occurs during loading.
     */
    public void load(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            this.state = gson.fromJson(reader, State.class);
        }
    }
}
