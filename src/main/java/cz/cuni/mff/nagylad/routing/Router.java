package cz.cuni.mff.nagylad.routing;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.pages.MainPage;
import cz.cuni.mff.nagylad.pages.SetDetailPage;
import cz.cuni.mff.nagylad.pages.SetsPage;

public class Router {
    public static final String EXIT_CODE = "exit";
    public static final String SETS_CODE = "sets";
    public static final String MAIN_CODE = "main";

    private final App app;
    private final AppState appState;

    public Router(App app, AppState appState) {
        this.app = app;
        this.appState = appState;
    }

    public void changePage(AvailablePages newPage) {
        if (newPage == AvailablePages.SetDetailPage) {
            throw new RuntimeException("The set needs to be selected for the SetDetail");
        }
        changePage(newPage, 0);
    }


    public void changePage(AvailablePages newPage, Integer setIndex) {
        switch (newPage) {
            case MainPage -> app.page = new MainPage(this, appState);
            case SetsPage -> app.page = new SetsPage(this, appState);
            case SetDetailPage -> app.page = new SetDetailPage(this, appState, setIndex);
        }
    }
}

