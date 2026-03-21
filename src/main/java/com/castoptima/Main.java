package com.castoptima;

import com.castoptima.evaluation.BenchmarkRunner;
import com.castoptima.io.ScheduleExporter;
import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.solver.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       CASTOPTIMA ENGINE v1.0.0 RUNTIME           ");
        System.out.println("==================================================");

        Actor a1 = new Actor("A1", "Lead Actor", 1500.0);
        Actor a2 = new Actor("A2", "Supporting Actor", 800.0);
        Actor a3 = new Actor("A3", "Cameo Role", 2000.0);

        Map<String, Actor> actors = Map.of(
            a1.getId(), a1,
            a2.getId(), a2,
            a3.getId(), a3
        );

        Scene s1 = new Scene("S1", "Opening Sequence", 2, Set.of("A1"), 5000);
        Scene s2 = new Scene("S2", "Climax Battle", 3, Set.of("A1", "A2"), 12000);
        Scene s3 = new Scene("S3", "Subplot Dialogue", 1, Set.of("A2", "A3"), 3000);
        Scene s4 = new Scene("S4", "Resolution", 2, Set.of("A1", "A3"), 8000);

        List<Scene> scenes = Arrays.asList(s1, s2, s3, s4);

        System.out.println("\n--- RUNNING BENCHMARKS ---");
        BenchmarkRunner.runBenchmark("Greedy CSP Solver", new GreedyCSPSolver(), scenes, actors);
        BenchmarkRunner.runBenchmark("Simulated Annealing", new SimulatedAnnealingSolver(1000.0, 0.003), scenes, actors);
        BenchmarkRunner.runBenchmark("Genetic Algorithm  ", new GeneticAlgorithmSolver(50, 100, 0.15), scenes, actors);

        ScheduleSolver bestSolver = new GeneticAlgorithmSolver(50, 100, 0.15);
        List<Scene> bestSchedule = bestSolver.solve(scenes, actors);

        System.out.println("\n--- OPTIMAL SCHEDULE OUTPUT ---");
        System.out.println(ScheduleExporter.exportToFormattedText(bestSchedule));
        System.out.println("==================================================");
    }
}