package com.castoptima.solver;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.evaluation.CostCalculator;

import java.util.*;

public class SolverTest {
    public static void main(String[] args) {
        Actor a1 = new Actor("A1", "Lead", 1000.0);
        Map<String, Actor> actors = Map.of("A1", a1);
        
        Scene s1 = new Scene("S1", "Scene 1", 1, Set.of("A1"), 1000);
        Scene s2 = new Scene("S2", "Scene 2", 1, Set.of("A1"), 1000);
        List<Scene> scenes = List.of(s1, s2);

        ScheduleSolver greedy = new GreedyCSPSolver();
        List<Scene> result = greedy.solve(scenes, actors);
        
        assert result.size() == 2 : "Solver lost scenes during optimization";
        System.out.println("All solver validation assertions passed successfully.");
    }
}