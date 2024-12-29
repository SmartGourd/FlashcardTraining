package cz.cuni.mff.nagylad.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of term-definition pairs used for training and learning.
 * Each set has a name and a list of term-definition pairs.
 */
public class Set {

    /** The name of the set. */
    public String name;

    /** The list of term-definition pairs in this set. */
    public List<TermDefinitionPair> termDefinitionPairs;

    /**
     * Constructs a new Set with the specified name.
     * Initializes an empty list of term-definition pairs.
     *
     * @param name The name of the set.
     */
    public Set(String name) {
        this.name = name;
        this.termDefinitionPairs = new ArrayList<>();
    }
}
