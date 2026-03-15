package com.castoptima.evaluation;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.solver.ScheduleSolver;

import java.util.List;
import java.util.Map;

public class BenchmarkRunner {

    public static void runBenchmark(String solverName, ScheduleSolver solver, List<Scene> scenes, Map<String, Actor> actors) {
        long startTime = System.nanoTime();
        List<Scene> result = solver.solve(scenes, actors);
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        double cost = CostCalculator.calculateHoldingCost(result, actors);

        System.out.printf("[%s] Execution Time: %.2f ms | Minimized Holding Cost: $%.2f%n", solverName, durationMs, cost);
    }
}