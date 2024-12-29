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

/**
 * The TrainingPage class represents a training page where users can practice
 * learning term-definition pairs using different algorithms. Users can select
 * a learning algorithm, provide answers to term prompts, and earn or lose gems
 * based on their performance.
 */
public class TrainingPage extends Page {
    private int setIndex; // The index of the set being trained on
    private final int gemsForCorrectAnswer = 10; // Gems awarded for a correct answer
    private final int gemsForIncorrectAnswer = -11; // Gems deducted for an incorrect answer

    /**
     * Constructs a TrainingPage instance.
     *
     * @param router   The Router instance used for navigating between pages.
     * @param appState The application state containing game and set data.
     * @param setIndex The index of the set to be used for training.
     */
    public TrainingPage(Router router, AppState appState, int setIndex) {
        super(router, appState);
        this.setIndex = setIndex;
    }

    /**
     * Loads the training page and starts the training session.
     * Prompts the user to select a learning algorithm and interactively trains them
     * on the selected term-definition set.
     */
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### TRAINING PAGE ####");
        System.out.println("Type which learning algorithm you want to use or press enter for a default one:");
        System.out.print("1 (default) - Box system, 2 - Weight system: ");

        // Get user input for the learning algorithm
        String algorithmToUse = new Scanner(System.in).nextLine();

        // Initialize the default learning algorithm
        AlgorithmBase algorithm = new BoxAlgorithm(appState.state.sets.get(setIndex).termDefinitionPairs);
        switch (algorithmToUse) {
            case "1" -> {} // Use the default BoxAlgorithm
            case "2" -> // Use the WeightAlgorithm if selected
                    algorithm = new WeightAlgorithm(appState.state.sets.get(setIndex).termDefinitionPairs);
        }

        System.out.println("Type the definition for the term or type 'stop123' to end the training: ");

        // Retrieve the set to be trained on
        Set set = appState.state.sets.get(setIndex);

        // Check if the set contains any term-definition pairs
        if (set.termDefinitionPairs.isEmpty()) {
            System.out.println("No pairs to train on!");
            router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);
            return;
        }

        int correctPairs = 0; // Counter for correct answers
        int incorrectPairs = 0; // Counter for incorrect answers

        while (true) {
            // Retrieve the next pair using the selected algorithm
            TermDefinitionPair pair = algorithm.nextPair();

            // Display the term to the user, is done like this cause the algorithm can display additional information like the pair box
            algorithm.printCurrentPairTerm();

            // Get user input for the definition
            String input = new Scanner(System.in).nextLine();

            // Check if the user wants to stop the training and print statistics for this training session
            if (Objects.equals(input, "stop123")) {
                System.out.println("This session learning statistics: ");
                System.out.printf("This training correct pairs: %s%n", correctPairs);
                System.out.printf("This training incorrect pairs: %s%n", incorrectPairs);
                System.out.printf("This training accuracy: %s%%%n",
                        (float) correctPairs / (correctPairs + incorrectPairs) * 100);
                System.out.println("Type enter to continue: ");
                String _ = new Scanner(System.in).nextLine();
                router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);
                return;
            }

            // Check if the user's input matches the correct definition
            if (algorithm.checkCurrentPair(input)) {
                System.out.println("CORRECT \uD83D\uDE0A");
                correctPairs++; // Increment correct answers counter
                appState.state.game.gems += gemsForCorrectAnswer; // Award gems for a correct answer
            } else {
                System.out.println("INCORRECT \uD83E\uDD14");
                System.out.printf("Correct answer was %s%n", pair.definition);
                incorrectPairs++; // Increment incorrect answers counter
                appState.state.game.gems += gemsForIncorrectAnswer; // Deduct gems for an incorrect answer
            }
        }
    }
}
