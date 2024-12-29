package cz.cuni.mff.nagylad.algorithms;

import cz.cuni.mff.nagylad.model.TermDefinitionPair;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The WeightAlgorithm class implements a learning algorithm where each term-definition
 * pair is associated with a weight. The selection frequency of each pair is inversely
 * proportional to its weight, meaning pairs with higher weights are selected more often.
 * Weights are adjusted dynamically based on user performance.
 */
public class WeightAlgorithm extends AlgorithmBase {
    private List<TermDefinitionPair> termDefinitionPairs; // List of term-definition pairs to learn.
    private TermDefinitionPair currentPair; // The current term-definition pair being reviewed.
    private Iterator<TermDefinitionPair> iterator; // Iterator for selecting the next pair.

    /**
     * Constructor that initializes the WeightAlgorithm with a list of term-definition pairs.
     *
     * @param termDefinitionPairs The list of term-definition pairs to use in the algorithm.
     */
    public WeightAlgorithm(List<TermDefinitionPair> termDefinitionPairs) {
        this.termDefinitionPairs = termDefinitionPairs;
        this.iterator = iterator();
    }

    /**
     * Retrieves the next term-definition pair to be reviewed.
     * Updates the currentPair field with the selected pair.
     *
     * @return The next term-definition pair.
     */
    @Override
    public TermDefinitionPair nextPair() {
        currentPair = iterator.next();
        return currentPair;
    }

    /**
     * Checks if the provided definition matches the current pair's definition.
     * If correct, the weight of the term is halved (making it less frequent).
     * If incorrect, the weight of the term is incremented by 1 (making it more frequent).
     *
     * @param definition The user's input definition.
     * @return true if the definition is correct; false otherwise.
     */
    @Override
    public boolean checkCurrentPair(String definition) {
        if (Objects.equals(definition, currentPair.definition)) {
            currentPair.weight /= 2; // Reduce weight for correct answers.
            return true;
        } else {
            currentPair.weight += 1; // Increase weight for incorrect answers.
            return false;
        }
    }

    /**
     * Prints the term of the current pair along with its weight for the user.
     */
    @Override
    public void printCurrentPairTerm() {
        System.out.println();
        System.out.printf("%s - Weight: %s%n", currentPair.term, currentPair.weight);
    }

    /**
     * Creates an iterator that continuously selects the next term-definition pair to review.
     * Pairs with higher weights have a higher chance of being selected.
     *
     * @return An iterator for the term-definition pairs.
     */
    @Override
    public Iterator<TermDefinitionPair> iterator() {
        return new Iterator<TermDefinitionPair>() {

            /**
             * Indicates that the iterator always has a next element, as it cycles indefinitely.
             *
             * @return true (always).
             */
            @Override
            public boolean hasNext() {
                return true;
            }

            /**
             * Selects the next term-definition pair to review based on the weight algorithm logic.
             * Pairs with higher weights are selected more frequently using weighted random selection.
             *
             * @return The next term-definition pair.
             */
            @Override
            public TermDefinitionPair next() {
                Random random = new Random();
                double totalWeight = termDefinitionPairs.stream()
                        .mapToDouble(pair -> pair.weight)
                        .sum(); // Calculate the total weight of all pairs.

                double randomValue = random.nextDouble(totalWeight); // Generate a random value in range of totalWeight.

                for (TermDefinitionPair pair : termDefinitionPairs) {
                    randomValue -= pair.weight;
                    // If weights are not initialized properly, the pair weight is set to 1.
                    if (pair.weight == 0) {
                        pair.weight = 1;
                    }
                    if (randomValue <= 0) {
                        return pair; // Select this term based on its weight.
                    }
                }
                // Fallback in case of rounding errors (should not happen in practice).
                return termDefinitionPairs.getFirst();
            }
        };
    }
}
