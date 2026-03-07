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
            Scene next = remaining.remove(0);
            scheduled.add(next);
        }

        return scheduled;
    }
}