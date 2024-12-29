package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

/**
 * The MainPage class represents the main page of the application.
 * It extends the abstract Page class and serves as an entry point for the user.
 * This page provides navigation options to view sets, start the game, or exit the application.
 */
public class MainPage extends Page {

    /**
     * Constructs a MainPage instance with the given router and application state.
     *
     * @param router   The Router instance for handling page navigation.
     * @param appState The application's shared state.
     */
    public MainPage(Router router, AppState appState) {
        super(router, appState);
    }

    /**
     * Loads the main page and displays the available options to the user.
     * Users can navigate to different pages (sets or game) or exit the application.
     */
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### MAIN PAGE ####");

        // Display a welcome message to the user.
        System.out.println("Welcome to flashcard training!");
        System.out.println("Nice to see you again!");

        // Provide navigation options to the user.
        System.out.printf("Type '%s' and press enter to see your sets.%n", Router.SETS_CODE);
        System.out.printf("Type '%s' and press enter to save the application state and leave the application.%n", Router.EXIT_CODE);
        System.out.printf("Type '%s' and press enter to open the game.%n", Router.GAME_CODE);

        // Prompt the user for input.
        System.out.print("Type here: ");
        String input = new Scanner(System.in).nextLine();

        // Handle user input with a switch statement.
        switch (input) {
            // Exit the application if the EXIT_CODE is entered.
            case Router.EXIT_CODE -> throw new CloseApplicationException("Application closed by exiting from the main page!");

            // Navigate to the Sets Page if the SETS_CODE is entered.
            case Router.SETS_CODE -> router.changePage(AvailablePages.SETS_PAGE);

            // Navigate to the Game Page if the GAME_CODE is entered.
            case Router.GAME_CODE -> router.changePage(AvailablePages.GAME_PAGE);
        }
    }
}

