package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.Page;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Objects;
import java.util.Scanner;

public class TrainingPage extends Page {
    private int setIndex;

    public TrainingPage(Router router, AppState appState, int setIndex) {
        super(router, appState);
        this.setIndex = setIndex;
    }

    @Override
    public void load() {
        System.out.println();
        System.out.println("#### TRAINING PAGE ####");
        System.out.println("Type the definition for the term or type 'stop123' to end the training: ");

        Set set = appState.state.sets.get(setIndex);

        if (set.termDefinitionPairs.isEmpty()) {
            System.out.println("No pairs to train on!");
            router.changePage(AvailablePages.SetDetailPage, setIndex);
            return;
        }

        int currentPairIndex = 0;
        int correctPairs = 0;
        int incorrectPairs = 0;

        while (true) {
            System.out.println();
            System.out.println(set.termDefinitionPairs.get(currentPairIndex).term);
            String input = new Scanner(System.in).nextLine();
            if (Objects.equals(input, "stop123")) {
                System.out.println("This set learning statistics: ");
                System.out.printf("This training correct pairs: %s%n", correctPairs);
                System.out.printf("This training incorrect pairs: %s%n", incorrectPairs);
                System.out.printf("This training accuracy: %s%%%n", (float)correctPairs / (correctPairs + incorrectPairs) * 100 );
                System.out.println("Type enter to continue: ");
                String _ = new Scanner(System.in).nextLine();
                router.changePage(AvailablePages.SetDetailPage, setIndex);
                return;
            }
            if (Objects.equals(input, set.termDefinitionPairs.get(currentPairIndex).definition)) {
                System.out.println("CORRECT \uD83D\uDE0A");
                correctPairs++;
            } else {
                System.out.println("INCORRECT \uD83E\uDD14");
                incorrectPairs++;
            }
            currentPairIndex = (currentPairIndex + 1) % set.termDefinitionPairs.size();
        }


    }
}
