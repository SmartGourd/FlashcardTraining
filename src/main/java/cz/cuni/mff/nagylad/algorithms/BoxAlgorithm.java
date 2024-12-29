package cz.cuni.mff.nagylad.algorithms;

import cz.cuni.mff.nagylad.model.TermDefinitionPair;

import java.util.*;

public class BoxAlgorithm extends AlgorithmBase {
    private List<TermDefinitionPair> termDefinitionPairs;
    private TermDefinitionPair currentPair;
    private Iterator<TermDefinitionPair> iterator;

    public BoxAlgorithm(List<TermDefinitionPair> termDefinitionPairs) {
        this.termDefinitionPairs = termDefinitionPairs;
        this.iterator = iterator();
    }

    @Override
    public TermDefinitionPair nextPair() {
        currentPair = iterator.next();
        return currentPair;
    }

    @Override
    public boolean checkCurrentPair(String definition) {
        if (Objects.equals(definition, currentPair.definition)) {
            currentPair.box++;
            return true;
        } else {
            if (currentPair.box > 1) {
                currentPair.box--;
            }
            return false;
        }
    }

    @Override
    public void printCurrentPairTerm() {
        System.out.println();
        System.out.printf("%s - Box: %s%n", currentPair.term, currentPair.box);
    }

    @Override
    public Iterator<TermDefinitionPair> iterator() {
        return new Iterator<TermDefinitionPair>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public TermDefinitionPair next() {
                while (true) {
                    Random random = new Random();
                    int randomValue = random.nextInt(termDefinitionPairs.size());
                    TermDefinitionPair pair = termDefinitionPairs.get(randomValue);
                    if (pair.timesTriedFromLastBox < pair.box - 1) {
                        pair.timesTriedFromLastBox++;
                    } else {
                        pair.timesTriedFromLastBox = 0;
                        return pair;
                    }
                }
            }
        };
    }
}
