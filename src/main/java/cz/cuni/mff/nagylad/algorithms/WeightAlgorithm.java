package cz.cuni.mff.nagylad.algorithms;

import cz.cuni.mff.nagylad.model.TermDefinitionPair;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WeightAlgorithm extends AlgorithmBase {
    private List<TermDefinitionPair> termDefinitionPairs;
    private TermDefinitionPair currentPair;
    private Iterator<TermDefinitionPair> iterator;

    public WeightAlgorithm(List<TermDefinitionPair> termDefinitionPairs) {
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
            currentPair.weight /= 2;
            return true;
        } else {
            currentPair.weight += 1;
            return false;
        }
    }

    @Override
    public void printCurrentPairTerm() {
        System.out.println();
        System.out.printf("%s - Weight: %s%n", currentPair.term, currentPair.weight);
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
                Random random = new Random();
                double totalWeight = termDefinitionPairs.stream().mapToDouble(pair -> pair.weight).sum();
                double randomValue = random.nextDouble(totalWeight);

                for (TermDefinitionPair pair : termDefinitionPairs) {
                    randomValue -= pair.weight;
                    if (pair.weight == 0) { // If not initialized properly to not get stuck
                        pair.weight = 1;
                    }
                    if (randomValue <= 0) {
                        return pair; // Select this term
                    }
                }
                return termDefinitionPairs.getFirst();
            }
        };
    }

}
