package cz.cuni.mff.nagylad.model;

import java.util.ArrayList;
import java.util.List;

public class Set {
    public String name;
    public List<TermDefinitionPair> termDefinitionPairs;

    public Set(String name) {
        this.name = name;
        this.termDefinitionPairs = new ArrayList<>();
    }
}
