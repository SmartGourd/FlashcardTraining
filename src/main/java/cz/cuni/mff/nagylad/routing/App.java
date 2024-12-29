package cz.cuni.mff.nagylad.routing;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Game;
import cz.cuni.mff.nagylad.model.State;
import cz.cuni.mff.nagylad.pages.MainPage;
import cz.cuni.mff.nagylad.pages.Page;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main entry point for the Learning Application.
 * This class initializes the application, loads and manages its state, and handles
 * the main execution loop. It is responsible for loading and saving data.
 */
public class App {

    /** The current page of the application. */
    public Page page;

    /** The router for managing page transitions. */
    private final Router router;

    /** The application's state, containing user progress, sets, and game data. */
    private AppState appState;

    /** The file where application data is persisted. */
    private final String dataFile = "appData.json";

    /**
     * Constructor for the App class.
     * Initializes the application state, router, and sets the starting page to the main menu.
     */
    public App() {
        appState = new AppState();
        router = new Router(this, appState);
        page = new MainPage(router, appState);
    }

    /**
     * Starts the main execution loop of the application.
     * Handles loading data, navigating between pages, and saving data upon exit.
     */
    public void run() {
        System.out.println();
        System.out.println("LOADING DATA ....");

        // Attempt to load application data from the file.
        try {
            appState.load(dataFile);
            System.out.println("Successfully loaded the application data!");
        } catch (IOException e) {
            // If loading fails, start with a blank state.
            System.out.println("Failed to load the application data!");
            System.out.println("Starting with blank state!");
        }

        // Ensure the state is initialized to avoid null pointer exceptions.
        initializeStateIfNull();

        try {
            // Main execution loop: repeatedly load the current page.
            while (true) {
                page.load();
            }
        } catch (CloseApplicationException e) {
            // Handle application exit gracefully.
            System.out.println(e.message);
            System.out.println("Closing the application, see you again soon \uD83D\uDE0A!");
        }

        System.out.println();
        System.out.println("SAVING DATA ....");

        // Attempt to save application data to the file.
        try {
            appState.save(dataFile);
            System.out.println("Successfully saved the application data!");
        } catch (IOException e) {
            System.out.println("Failed to save the application data!");
        }
    }

    /**
     * Ensures that the application state is properly initialized.
     * If any part of the state is null, it creates the necessary default objects.
     */
    public void initializeStateIfNull() {
        // Initialize the app state and list of sets if it is null.
        if (appState.state == null || appState.state.sets == null) {
            appState.state = new State();
            appState.state.sets = new ArrayList<>();
        }

        // Initialize the game state if it is null.
        if (appState.state.game == null) {
            appState.state.game = new Game();
        }
    }
}
