package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

public abstract class Page {
    protected final Router router;
    protected final AppState appState;

    protected Page(Router router, AppState appState) {
        this.router = router;
        this.appState = appState;
    }

    /**
     * Needs to have an input to pause the application
     */
    public void load() {
        String input = new Scanner(System.in).nextLine();
        throw new CloseApplicationException("Forgot to implement load in page!");
    }
}
