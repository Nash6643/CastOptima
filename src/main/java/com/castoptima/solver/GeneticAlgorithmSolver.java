package com.castoptima.solver;

import com.castoptima.evaluation.CostCalculator;
import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.*;

public class GeneticAlgorithmSolver implements ScheduleSolver {
    private final int populationSize;
    private final int generations;
    private final double mutationRate;
    private final Random random = new Random();

    public GeneticAlgorithmSolver(int populationSize, int generations, double mutationRate) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.mutationRate = mutationRate;
    }

    private List<Scene> crossover(List<Scene> parent1, List<Scene> parent2) {
        int size = parent1.size();
        int start = random.nextInt(size);
        int end = random.nextInt(size);

        int cut1 = Math.min(start, end);
        int cut2 = Math.max(start, end);

        List<Scene> child = new ArrayList<>(Collections.nCopies(size, (Scene) null));
        Set<String> addedIds = new HashSet<>();

        for (int i = cut1; i <= cut2; i++) {
            Scene s = parent1.get(i);
            child.set(i, s);
            addedIds.add(s.getId());
        }

        int childIdx = 0;
        for (Scene s : parent2) {
            if (!addedIds.contains(s.getId())) {
                while (child.get(childIdx) != null) {
                    childIdx++;
                }
                child.set(childIdx, s);
            }
        }
        return child;
    }

    private void mutate(List<Scene> individual) {
        if (random.nextDouble() < mutationRate) {
            int i = random.nextInt(individual.size());
            int j = random.nextInt(individual.size());
            Collections.swap(individual, i, j);
        }
    }

    private List<List<Scene>> initializePopulation(List<Scene> baseScenes) {
        List<List<Scene>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<Scene> individual = new ArrayList<>(baseScenes);
            Collections.shuffle(individual);
            population.add(individual);
        }
        return population;
    }

    @Override
    public List<Scene> solve(List<Scene> scenes, Map<String, Actor> actors) {
        List<List<Scene>> population = initializePopulation(scenes);
        return population.get(0);
    }
}