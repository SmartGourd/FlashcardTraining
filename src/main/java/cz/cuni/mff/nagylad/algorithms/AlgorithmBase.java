package cz.cuni.mff.nagylad.algorithms;

import cz.cuni.mff.nagylad.model.TermDefinitionPair;

/**
 * Base class to inherit from for training algorithms, that decide next pairs, based on user performance
 */
public abstract class AlgorithmBase implements Iterable<TermDefinitionPair> {
    /**
     * Sets the new pair as current for the algorithm
     * @return Returns the new pair
     */
    public abstract TermDefinitionPair nextPair();

    /**
     *
     * @param definition the definition to check the pair definition to
     * @return the check result
     */
    public abstract boolean checkCurrentPair(String definition);

    /**
     * Prints the current pair term, can be used to print algorithm specifics like current term box
     */
    public abstract void printCurrentPairTerm();
}
