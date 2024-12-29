package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.algorithms.AlgorithmBase;
import cz.cuni.mff.nagylad.algorithms.BoxAlgorithm;
import cz.cuni.mff.nagylad.algorithms.WeightAlgorithm;
import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Set;
import cz.cuni.mff.nagylad.model.TermDefinitionPair;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Objects;
import java.util.Scanner;

public class TrainingPage extends Page {
    private int setIndex;
    private final int gemsForCorrectAnswer = 10;
    private final int gemsForIncorrectAnswer = -11;

    public TrainingPage(Router router, AppState appState, int setIndex) {
        super(router, appState);
        this.setIndex = setIndex;
    }

    @Override
    public void load() {
        System.out.println();
        System.out.println("#### TRAINING PAGE ####");
        System.out.println("Type which learning algorithm you want to use or press enter for a default one:");
        System.out.print("1 (default) - Box system, 2 - Weight system: ");

        String algorithmToUse = new Scanner(System.in).nextLine();
        AlgorithmBase algorithm = new BoxAlgorithm(appState.state.sets.get(setIndex).termDefinitionPairs);
        switch (algorithmToUse) {
            case "1" -> {}
            case "2" -> algorithm = new WeightAlgorithm(appState.state.sets.get(setIndex).termDefinitionPairs);
        }
        System.out.println("Type the definition for the term or type 'stop123' to end the training: ");

        Set set = appState.state.sets.get(setIndex);

        if (set.termDefinitionPairs.isEmpty()) {
            System.out.println("No pairs to train on!");
            router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);
            return;
        }

        int correctPairs = 0;
        int incorrectPairs = 0;

        while (true) {
            TermDefinitionPair pair = algorithm.nextPair();
            algorithm.printCurrentPairTerm();

            String input = new Scanner(System.in).nextLine();
            if (Objects.equals(input, "stop123")) {
                System.out.println("This session learning statistics: ");
                System.out.printf("This training correct pairs: %s%n", correctPairs);
                System.out.printf("This training incorrect pairs: %s%n", incorrectPairs);
                System.out.printf("This training accuracy: %s%%%n", (float)correctPairs / (correctPairs + incorrectPairs) * 100 );
                System.out.println("Type enter to continue: ");
                String _ = new Scanner(System.in).nextLine();
                router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);
                return;
            }

            if (algorithm.checkCurrentPair(input)) {
                System.out.println("CORRECT \uD83D\uDE0A");
                correctPairs++;
                appState.state.game.gems += gemsForCorrectAnswer;
            } else {
                System.out.println("INCORRECT \uD83E\uDD14");
                System.out.printf("Correct answer was %s%n", pair.definition);
                incorrectPairs++;
                appState.state.game.gems += gemsForIncorrectAnswer;
            }
        }


    }
}
