package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.model.TermDefinitionPair;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

public class SetDetailPage extends Page {
    private int setIndex;

   public SetDetailPage(Router router, AppState appState, int setIndex) {
        super(router, appState);
        this.setIndex = setIndex;
    }

    @Override
    public void load() {
        System.out.println();
        System.out.println("#### SET DETAIL PAGE ####");

        Set set = appState.state.sets.get(setIndex);

        System.out.printf("This set has name: %s%n", set.name);
        System.out.println("The set has these term-definition pairs");
        if (set.termDefinitionPairs.isEmpty()) {
            System.out.println("There are no term-definition pairs");
        } else {
            System.out.println("Format is: Term, Definition");
        }

        int index = 0;
        for (TermDefinitionPair pair: set.termDefinitionPairs) {
            System.out.printf("%s - %s, %s%n", index, pair.term, pair.definition);
            index++;
        }

        System.out.println();
        System.out.printf(
            "Type '%s' and press enter to go back to your sets.%n",
            Router.SETS_CODE
        );
        System.out.println("Type 'training' to start training on this set");
        System.out.println("Type 'add' to add a new term-definition pair");
        System.out.println("Type 'remove' to delete a term-definition pair, you will specify its number in the next input");
        System.out.println("Type 'rename' to rename the current set");
        System.out.println("Type 'delete_set_not_reversible' to delete the current set, this is NOT reversible!");
        System.out.printf("Type '%s' and press enter to save the application state and leave the application.%n", Router.EXIT_CODE);

        System.out.print("Type here: ");
        String input = new Scanner(System.in).nextLine();

        switch (input) {
            case Router.SETS_CODE -> router.changePage(AvailablePages.SETS_PAGE);
            case "training" -> router.changePage(AvailablePages.TRAINING_PAGE, setIndex);
            case "add" -> {
                System.out.print("Type the term: ");
                String term = new Scanner(System.in).nextLine();
                System.out.print("Type the definition: ");
                String definition = new Scanner(System.in).nextLine();
                set.termDefinitionPairs.add(new TermDefinitionPair(term, definition));
            }
            case "remove" -> {
                System.out.print("Type the pair number to remove: ");
                String indexToRemove = new Scanner(System.in).nextLine();
                try {
                    // Check if the input is a number
                    int pairIndex = Integer.parseInt(indexToRemove);

                    if (pairIndex >= 0 && pairIndex < appState.state.sets.get(setIndex).termDefinitionPairs.size()) {
                        appState.state.sets.get(setIndex).termDefinitionPairs.remove(pairIndex);
                    } else {
                        System.out.println("Invalid set number. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input is not a number!");
                }
            }
            case "rename" -> {
                System.out.print("Type the new set name: ");
                set.name = new Scanner(System.in).nextLine();
            }
            case "delete_set_not_reversible" -> {
                appState.state.sets.remove(setIndex);
                router.changePage(AvailablePages.SETS_PAGE);
            }
            case "exit" -> throw new CloseApplicationException("Application closed by exiting from the set detail page!");
        }
    }
}
