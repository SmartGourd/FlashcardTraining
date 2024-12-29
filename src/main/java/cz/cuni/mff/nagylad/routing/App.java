package cz.cuni.mff.nagylad.routing;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Game;
import cz.cuni.mff.nagylad.model.State;
import cz.cuni.mff.nagylad.pages.MainPage;
import cz.cuni.mff.nagylad.pages.Page;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class App {
    public Page page;
    private final Router router;
    private AppState appState;
    private final String dataFile = "appData.json";

    public App () {
        appState = new AppState();
        router = new Router(this, appState);
        page = new MainPage(router, appState);
    }


    public void run() {
        System.out.println();
        System.out.println("LOADING DATA ....");
        try {
            appState.load(dataFile);
            System.out.println("Successfully loaded the application data!");
        } catch (IOException e) {
            System.out.println("Failed to load the application data!");
            System.out.println("Starting with blank state!");
        }

        initializeStateIfNull();

        try {
            while (true) {
                page.load();
            }
        } catch (CloseApplicationException e) {
            System.out.println(e.message);
            System.out.println("Closing the application, see you again soon \uD83D\uDE0A!");
        }

        System.out.println();
        System.out.println("SAVING DATA ....");
        try {
            appState.save(dataFile);
            System.out.println("Successfully saved the application data!");
        } catch (IOException e) {
            System.out.println("Failed to save the application data!");
            return;
        }
    }

    public void initializeStateIfNull() {
        if (appState.state == null || appState.state.sets == null) {
            appState.state = new State();
            appState.state.sets = new ArrayList<>();
        }

        if (appState.state.game == null) {
            appState.state.game = new Game();
        }
    }
}
