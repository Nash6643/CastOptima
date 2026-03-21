package com.castoptima;

import com.castoptima.evaluation.CostCalculator;
import com.castoptima.io.ScheduleExporter;
import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import com.castoptima.solver.*;
import java.util.*;

public class AppConsole {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      CASTOPTIMA PRODUCTION SOLVER PIPELINE       ");
        System.out.println("==================================================");

        Map<String, Actor> actors = new HashMap<>();
        actors.put("A1", new Actor("A1", "Lead Actor", 2500.0));
        actors.put("A2", new Actor("A2", "Supporting Actor", 1200.0));
        actors.put("A3", new Actor("A3", "Special Guest", 3500.0));
        actors.put("A4", new Actor("A4", "Stunt Double", 900.0));

        List<Scene> scenes = List.of(
            new Scene("S1", "Opening Sequence", 3, Set.of("A1", "A4"), 15000),
            new Scene("S2", "Midpoint Drama", 2, Set.of("A1", "A2"), 10000),
            new Scene("S3", "Guest Appearance", 1, Set.of("A2", "A3"), 8000),
            new Scene("S4", "Climax Stunt", 4, Set.of("A1", "A3", "A4"), 25000),
            new Scene("S5", "Epilogue", 2, Set.of("A1", "A2"), 6000)
        );

        ScheduleSolver productionSolver = new GeneticAlgorithmSolver(100, 250, 0.10);
        List<Scene> optimizedSchedule = productionSolver.solve(scenes, actors);

        double totalCost = CostCalculator.calculateHoldingCost(optimizedSchedule, actors);

        System.out.println(ScheduleExporter.exportToFormattedText(optimizedSchedule));
        System.out.printf("Total Minimized Holding Cost: $%.2f%n", totalCost);
        System.out.println("==================================================");
    }
}