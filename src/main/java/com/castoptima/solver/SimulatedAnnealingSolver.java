package com.castoptima.solver;

import com.castoptima.evaluation.CostCalculator;
import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.*;

public class SimulatedAnnealingSolver implements ScheduleSolver {
    private final double initialTemperature;
    private final double coolingRate;

    public SimulatedAnnealingSolver(double initialTemperature, double coolingRate) {
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
    }

    private double acceptanceProbability(double currentCost, double neighborCost, double temp) {
        if (neighborCost < currentCost) {
            return 1.0;
        }
        return Math.exp((currentCost - neighborCost) / temp);
    }

    @Override
    public List<Scene> solve(List<Scene> scenes, Map<String, Actor> actors) {
        List<Scene> currentSolution = new ArrayList<>(scenes);
        Collections.shuffle(currentSolution);
        
        List<Scene> bestSolution = new ArrayList<>(currentSolution);
        double currentCost = CostCalculator.calculateHoldingCost(currentSolution, actors);
        double bestCost = currentCost;

        double temp = initialTemperature;
        Random random = new Random();

        while (temp > 0.1) {
            List<Scene> neighbor = new ArrayList<>(currentSolution);
            int idx1 = random.nextInt(neighbor.size());
            int idx2 = random.nextInt(neighbor.size());
            Collections.swap(neighbor, idx1, idx2);

            double neighborCost = CostCalculator.calculateHoldingCost(neighbor, actors);

            if (acceptanceProbability(currentCost, neighborCost, temp) > random.nextDouble()) {
                currentSolution = neighbor;
                currentCost = neighborCost;
            }

            if (currentCost < bestCost) {
                bestSolution = new ArrayList<>(currentSolution);
                bestCost = currentCost;
            }

            temp *= (1 - coolingRate);
        }

        return bestSolution;
    }
}