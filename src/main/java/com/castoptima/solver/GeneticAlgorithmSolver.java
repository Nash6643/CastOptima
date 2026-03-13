package com.castoptima.solver;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.*;

public class GeneticAlgorithmSolver implements ScheduleSolver {
    private final int populationSize;
    private final int generations;
    private final double mutationRate;

    public GeneticAlgorithmSolver(int populationSize, int generations, double mutationRate) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.mutationRate = mutationRate;
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