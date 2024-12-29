package cz.cuni.mff.nagylad.model;

/**
 * Represents a term-definition pair used for training and learning.
 * This class also tracks the pair's progress through a spaced repetition system
 * by storing its box number, the number of times it has been attempted,
 * and a weight to determine its priority during training.
 */
public class TermDefinitionPair {
    /**
     * The term (e.g., a word, phrase, or concept).
     */
    public String term;

    /**
     * The definition of the term.
     */
    public String definition;

    /**
     * Used by box training algorithm.
     * The box number in the spaced repetition system.
     * Higher box numbers represent better mastery of the term.
     * Defaults to 1 when a new pair is created.
     * Cannot be less than 1, there is no upper boundary.
     */
    public int box;

    /**
     * Used by box training algorithm.
     * The number of times this term has been attempted to be shown, but was not, since it last changed boxes.
     */
    public int timesTriedFromLastBox;

    /**
     * Used by weight training algorithm
     * The weight of the term for determining its selection priority during training.
     * A higher weight indicates higher priority for review.
     * Defaults to 1 when a new pair is created.
     * Cannot be lower or equal to 0.
     */
    public float weight;

    /**
     * Creates a new term-definition pair with the specified term and definition.
     * Initializes the pair in the first box and weight 1.
     *
     * @param term       The term (e.g., word or phrase).
     * @param definition The definition of the term.
     */
    public TermDefinitionPair(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.box = 1;
        this.timesTriedFromLastBox = 0;
        this.weight = 1;
    }
}
