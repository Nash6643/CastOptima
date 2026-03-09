package com.castoptima;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.solver.GreedyCSPSolver;
import com.castoptima.solver.SimulatedAnnealingSolver;
import com.castoptima.solver.ScheduleSolver;
import com.castoptima.evaluation.CostCalculator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Actor a1 = new Actor("A1", "Lead Actor", 1500.0);
        Actor a2 = new Actor("A2", "Supporting Actor", 800.0);
        Actor a3 = new Actor("A3", "Cameo", 2000.0);

        Map<String, Actor> actors = new HashMap<>();
        actors.put(a1.getId(), a1);
        actors.put(a2.getId(), a2);
        actors.put(a3.getId(), a3);

        Scene s1 = new Scene("S1", "Opening Scene", 2, Set.of("A1"), 5000);
        Scene s2 = new Scene("S2", "Climax Action", 3, Set.of("A1", "A2"), 12000);
        Scene s3 = new Scene("S3", "Subplot Scene", 1, Set.of("A2", "A3"), 3000);
        Scene s4 = new Scene("S4", "Finale", 2, Set.of("A1", "A3"), 8000);

        List<Scene> scenes = Arrays.asList(s1, s2, s3, s4);

        ScheduleSolver greedy = new GreedyCSPSolver();
        List<Scene> greedyResult = greedy.solve(scenes, actors);
        System.out.println("Greedy Cost: " + CostCalculator.calculateHoldingCost(greedyResult, actors));

        ScheduleSolver sa = new SimulatedAnnealingSolver(1000.0, 0.003);
        List<Scene> saResult = sa.solve(scenes, actors);
        System.out.println("Annealing Cost: " + CostCalculator.calculateHoldingCost(saResult, actors));
    }
}