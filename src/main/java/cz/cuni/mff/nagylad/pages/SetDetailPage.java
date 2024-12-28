package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.model.TermDefinitionPair;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.Page;
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

        System.out.printf("This set name: %s%n", set.name);
        System.out.println("The set has these term-definition pairs");
        if (set.termDefinitionPairs.isEmpty()) {
            System.out.println("There are no term-definition pairs");
        }
        for (TermDefinitionPair pair: set.termDefinitionPairs) {
            System.out.println("Format is: Term, Definition");
            System.out.printf("%s, %s%n%n", pair.term, pair.definition);
        }

        System.out.println();
        System.out.printf(
            "Type '%s' and press enter to go back to your sets.%n",
            Router.SETS_CODE
        );
        System.out.println("Type 'pair' to add a new term-definition pair");
        System.out.println("Type 'rename' to rename the current set");
        System.out.println("Type 'delete' to delete the current set, this is NOT reversible!");

        System.out.print("Type here: ");
        String input = new Scanner(System.in).nextLine();

        switch (input) {
            case Router.SETS_CODE -> router.changePage(AvailablePages.SetsPage);
            case "pair" -> {
                System.out.print("Type the term: ");
                String term = new Scanner(System.in).nextLine();
                System.out.print("Type the definition: ");
                String definition = new Scanner(System.in).nextLine();
                set.termDefinitionPairs.add(new TermDefinitionPair(term, definition));
            }
            case "rename" -> {
                System.out.print("Type the new set name: ");
                set.name = new Scanner(System.in).nextLine();
            }
            case "delete" -> {
                appState.state.sets.remove(setIndex);
                router.changePage(AvailablePages.SetsPage);
            }
        }
    }
}
