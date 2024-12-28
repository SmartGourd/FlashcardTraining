package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.Page;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

public class SetsPage extends Page {
    public SetsPage (Router router, AppState appState) {
        super(router, appState);
    }
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### SETS PAGE ####");

        System.out.println("You have following sets:");
        if (appState.state.sets.isEmpty()) {
            System.out.println("There are no sets");
        }
        int index = 0;
        for (Set set: appState.state.sets) {
            System.out.printf("%s - %s%n", index, set.name);
            index++;
        }

        System.out.println();
        System.out.println("Type the number of the set to open this set detail");
        System.out.println("Type 'new' and press enter to create a new set");
        System.out.printf("Type '%s' and press enter to return to the menu%n", Router.MAIN_CODE);
        System.out.print("Type here: ");

        String input = new Scanner(System.in).nextLine();

        try {
            // Check if the input is a number
            int setIndex = Integer.parseInt(input);

            // Attempt to fetch the set at the given index
            if (setIndex >= 0 && setIndex < appState.state.sets.size()) {
                Set selectedSet = appState.state.sets.get(setIndex);
                System.out.printf("You selected the set: %s%n", selectedSet.name);
                router.changePage(AvailablePages.SetDetailPage, setIndex);
            } else {
                System.out.println("Invalid set number. Please try again.");
            }
        } catch (NumberFormatException e) {
            // Handle non-numeric input
            switch (input) {
                case Router.MAIN_CODE -> router.changePage(AvailablePages.MainPage);
                case "new" -> {
                    System.out.print("Input the name for the new set: ");
                    String name = new Scanner(System.in).nextLine();
                    appState.state.sets.add(new Set(name));
                    System.out.println("New set created successfully.");
                }
            }
        }
    }
}
