package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

/**
 * The abstract Page class represents a generic page in the application.
 * All specific pages (e.g., MainPage, SetsPage) should extend this class.
 * It defines the common structure and behavior for all pages, such as maintaining
 * references to the router for navigation and the application state.
 */
public abstract class Page {

    /** The router instance used for navigating between pages. */
    protected final Router router;

    /** The shared application state that contains global data. */
    protected final AppState appState;

    /**
     * Constructs a Page instance with the given router and application state.
     *
     * @param router   The Router instance for handling page navigation.
     * @param appState The application's shared state.
     */
    protected Page(Router router, AppState appState) {
        this.router = router;
        this.appState = appState;
    }

    /**
     * The load method has to be implemented by all subclasses to define the behavior of the page when it is loaded.
     */
    public abstract void load();
}
