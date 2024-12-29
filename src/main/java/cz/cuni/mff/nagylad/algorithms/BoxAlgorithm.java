package cz.cuni.mff.nagylad.algorithms;

import cz.cuni.mff.nagylad.model.TermDefinitionPair;

import java.util.*;

/**
 * The BoxAlgorithm class implements a spaced repetition learning algorithm
 * using a "box system". Each term-definition pair is assigned to a box, and
 * the algorithm increases or decreases the box number based on correct or
 * incorrect answers, respectively. The higher the box, the less frequently the
 * term appears for review.
 */
public class BoxAlgorithm extends AlgorithmBase {
    private List<TermDefinitionPair> termDefinitionPairs; // List of term-definition pairs to learn.
    private TermDefinitionPair currentPair; // The current term-definition pair being reviewed.
    private Iterator<TermDefinitionPair> iterator; // Iterator for selecting the next pair.

    /**
     * Constructor that initializes the BoxAlgorithm with a list of term-definition pairs.
     *
     * @param termDefinitionPairs The list of term-definition pairs to use in the algorithm.
     */
    public BoxAlgorithm(List<TermDefinitionPair> termDefinitionPairs) {
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
     * If correct, the term's box is incremented. If incorrect, the box is decremented (if greater than 1).
     *
     * @param definition The user's input definition.
     * @return true if the definition is correct; false otherwise.
     */
    @Override
    public boolean checkCurrentPair(String definition) {
        if (Objects.equals(definition, currentPair.definition)) {
            currentPair.box++; // Move the term to a higher box if the answer is correct.
            return true;
        } else {
            if (currentPair.box > 1) {
                currentPair.box--; // Move the term to a lower box if the answer is incorrect.
            }
            return false;
        }
    }

    /**
     * Prints the term of the current pair along with its box number for the user.
     */
    @Override
    public void printCurrentPairTerm() {
        System.out.println();
        System.out.printf("%s - Box: %s%n", currentPair.term, currentPair.box);
    }

    /**
     * Creates an iterator that continuously selects the next term-definition pair to review.
     * The selection is influenced by the box number; pairs with lower box numbers appear more frequently.
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
             * Selects the next term-definition pair to review based on the box algorithm logic.
             * The times tried from last box keeps how many times the pair was randomly chosen but not shown,
             * when it reaches the box number it gets shown.
             *
             * @return The next term-definition pair.
             */
            @Override
            public TermDefinitionPair next() {
                while (true) {
                    Random random = new Random();
                    int randomValue = random.nextInt(termDefinitionPairs.size());
                    TermDefinitionPair pair = termDefinitionPairs.get(randomValue);
                    // Ensure the pair should be shown
                    if (pair.timesTriedFromLastBox < pair.box - 1) {
                        pair.timesTriedFromLastBox++; // Increment attempts from the current pair.
                    } else {
                        pair.timesTriedFromLastBox = 0; // Reset attempts and return the pair.
                        return pair;
                    }
                }
            }
        };
    }
}
