package com.castoptima;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.solver.GreedyCSPSolver;
import com.castoptima.solver.ScheduleSolver;
import com.castoptima.evaluation.CostCalculator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Actor a1 = new Actor("A1", "Lead Actor", 1500.0);
        Actor a2 = new Actor("A2", "Supporting Actor", 800.0);

        Map<String, Actor> actors = new HashMap<>();
        actors.put(a1.getId(), a1);
        actors.put(a2.getId(), a2);

        Scene s1 = new Scene("S1", "Opening Scene", 2, Set.of("A1"), 5000);
        Scene s2 = new Scene("S2", "Climax Action", 3, Set.of("A1", "A2"), 12000);
        Scene s3 = new Scene("S3", "Subplot Scene", 1, Set.of("A2"), 3000);

        List<Scene> scenes = Arrays.asList(s1, s2, s3);

        ScheduleSolver solver = new GreedyCSPSolver();
        List<Scene> result = solver.solve(scenes, actors);

        System.out.println("Scheduled " + result.size() + " scenes.");
        System.out.println("Total Holding Cost: " + CostCalculator.calculateHoldingCost(result, actors));
    }
}