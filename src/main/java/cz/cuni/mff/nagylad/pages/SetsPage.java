package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

/**
 * The SetsPage class represents the main page for managing sets.
 * This page allows users to view, create, and select sets for further operations.
 */
public class SetsPage extends Page {

    /**
     * Constructs a SetsPage instance.
     *
     * @param router   The Router instance for handling page navigation.
     * @param appState The shared application state.
     */
    public SetsPage(Router router, AppState appState) {
        super(router, appState);
    }

    /**
     * Loads the Sets Page. Displays a list of available sets and provides options
     * to view set details, create new sets, or navigate back to the main menu.
     */
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### SETS PAGE ####");

        // Display the list of sets or a message if no sets exist.
        System.out.println("You have the following sets:");
        if (appState.state.sets.isEmpty()) {
            System.out.println("There are no sets");
        }

        // Iterate through the sets and print their names with corresponding indices.
        int index = 0;
        for (Set set : appState.state.sets) {
            System.out.printf("%s - %s%n", index, set.name);
            index++;
        }

        // Display available user actions.
        System.out.println();
        System.out.println("Type the number of the set to open this set detail");
        System.out.println("Type 'new' and press enter to create a new set");
        System.out.printf("Type '%s' and press enter to return to the menu%n", Router.MAIN_CODE);
        System.out.printf(
                "Type '%s' and press enter to save the application state and leave the application.%n",
                Router.EXIT_CODE
        );
        System.out.print("Type here: ");

        // Capture the user input.
        String input = new Scanner(System.in).nextLine();

        try {
            // Check if the input is a number.
            int setIndex = Integer.parseInt(input);

            // Validate the set index and navigate to the Set Detail Page if valid.
            if (setIndex >= 0 && setIndex < appState.state.sets.size()) {
                Set selectedSet = appState.state.sets.get(setIndex);
                System.out.printf("You selected the set: %s%n", selectedSet.name);
                router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);
            } else {
                System.out.println("Invalid set number. Please try again.");
            }
        } catch (NumberFormatException e) {
            // Handle non-numeric input for other options.
            switch (input) {
                // Navigate back to the Main Page.
                case Router.MAIN_CODE -> router.changePage(AvailablePages.MAIN_PAGE);

                // Create a new set.
                case "new" -> {
                    System.out.print("Input the name for the new set: ");
                    String name = new Scanner(System.in).nextLine();
                    appState.state.sets.add(new Set(name));
                    System.out.println("New set created successfully.");
                }

                // Exit the application.
                case "exit" -> throw new CloseApplicationException("Application closed by exiting from the sets page!");
            }
        }
    }
}
