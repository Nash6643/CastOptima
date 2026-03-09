package com.castoptima.solver;

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

    @Override
    public List<Scene> solve(List<Scene> scenes, Map<String, Actor> actors) {
        return new ArrayList<>(scenes);
    }
}