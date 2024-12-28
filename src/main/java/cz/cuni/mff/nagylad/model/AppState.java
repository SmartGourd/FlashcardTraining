package cz.cuni.mff.nagylad.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppState {
    public State state; // The state of the application

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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