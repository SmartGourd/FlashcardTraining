package cz.cuni.mff.nagylad.routing;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.pages.*;

/**
 * Router class manages navigation between different pages of the application.
 * It is responsible for transitioning to various pages based on user input or application logic.
 */
public class Router {

    // Constants for commonly used page codes
    /** Code to exit the application. */
    public static final String EXIT_CODE = "exit";

    /** Code to navigate to the Sets page. */
    public static final String SETS_CODE = "sets";

    /** Code to navigate to the Main Menu. */
    public static final String MAIN_CODE = "main";

    /** Code to navigate to the Game page. */
    public static final String GAME_CODE = "game";

    /** Reference to the main application instance. */
    private final App app;

    /** Reference to the global application state containing data and settings. */
    private final AppState appState;

    /**
     * Constructs a Router instance.
     *
     * @param app      The main application instance.
     * @param appState The global application state containing data
     */
    public Router(App app, AppState appState) {
        this.app = app;
        this.appState = appState;
    }

    /**
     * Changes the current page to the specified page, used where no setIndex is needed
     * Throws an exception if trying to navigate to the SetDetailPage or TrainingPage without specifying a set.
     *
     * @param newPage The page to navigate to.
     * @throws RuntimeException If SetDetailPage is selected without a valid set index.
     */
    public void changePage(AvailablePages newPage) {
        if (newPage == AvailablePages.SET_DETAIL_PAGE || newPage == AvailablePages.TRAINING_PAGE) {
            throw new RuntimeException("The set needs to be selected for the SetDetailPage or TrainingPage");
        }
        changePage(newPage, 0); // Default set index is 0
    }

    /**
     * Changes the current page to the specified page with a specified setIndex
     * Used to get to SetDetailPage or TrainingPage
     *
     * @param newPage  The page to navigate to.
     * @param setIndex The index of the selected set, if applicable (e.g., for SetDetailPage and TrainingPage).
     */
    public void changePage(AvailablePages newPage, Integer setIndex) {
        switch (newPage) {
            case MAIN_PAGE -> app.page = new MainPage(this, appState); // Navigate to Main Page
            case SETS_PAGE -> app.page = new SetsPage(this, appState); // Navigate to Sets Page
            case SET_DETAIL_PAGE -> app.page = new SetDetailPage(this, appState, setIndex); // Navigate to Set Detail Page
            case TRAINING_PAGE -> app.page = new TrainingPage(this, appState, setIndex); // Navigate to Training Page
            case GAME_PAGE -> app.page = new GamePage(this, appState); // Navigate to Game Page
        }
    }
}

