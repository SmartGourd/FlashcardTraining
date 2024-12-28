package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Page;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

public class MainPage extends Page {
    public MainPage (Router router, AppState appState) {
        super(router, appState);
    }
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### MAIN PAGE ####");

        System.out.println("Welcome to flashcard training!");
        System.out.println("Nice to see you again!");
        System.out.printf(
                "Type '%s' and press enter to see your sets.%n" +
                "Type '%s' and press enter to leave the application.%n",
                Router.SETS_CODE, Router.EXIT_CODE
        );

        System.out.print("Type here: ");
        String input = new Scanner(System.in).nextLine();

        switch (input) {
            case Router.EXIT_CODE -> throw new CloseApplicationException("Application closed by exiting from the main page!");
            case Router.SETS_CODE -> router.changePage(AvailablePages.SetsPage);

        }
    }
}
