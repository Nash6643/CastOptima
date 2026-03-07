package com.castoptima.solver;

import com.castoptima.evaluation.CostCalculator;
import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.*;

public class GreedyCSPSolver implements ScheduleSolver {

    @Override
    public List<Scene> solve(List<Scene> scenes, Map<String, Actor> actors) {
        List<Scene> remaining = new ArrayList<>(scenes);
        List<Scene> scheduled = new ArrayList<>();

        while (!remaining.isEmpty()) {
            Scene bestNext = null;
            double lowestCost = Double.MAX_VALUE;

            for (Scene candidate : remaining) {
                List<Scene> testSchedule = new ArrayList<>(scheduled);
                testSchedule.add(candidate);

                double cost = CostCalculator.calculateHoldingCost(testSchedule, actors);
                if (cost < lowestCost) {
                    lowestCost = cost;
                    bestNext = candidate;
                }
            }

            if (bestNext != null) {
                scheduled.add(bestNext);
                remaining.remove(bestNext);
            } else {
                scheduled.add(remaining.remove(0));
            }
        }

        return scheduled;
    }
}