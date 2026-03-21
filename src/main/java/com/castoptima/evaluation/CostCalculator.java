package com.castoptima.evaluation;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;

import java.util.*;

public class CostCalculator {

    public static double calculateHoldingCost(List<Scene> schedule, Map<String, Actor> actors) {
        if (schedule == null || schedule.isEmpty() || actors == null) {
            return 0.0;
        }

        Map<String, Integer> firstAppearance = new HashMap<>();
        Map<String, Integer> lastAppearance = new HashMap<>();

        int currentDay = 0;
        for (Scene scene : schedule) {
            int duration = Math.max(1, scene.getDurationDays());
            int startDay = currentDay;
            int endDay = currentDay + duration;

            for (String actorId : scene.getRequiredActorIds()) {
                firstAppearance.putIfAbsent(actorId, startDay);
                lastAppearance.put(actorId, endDay);
            }
            currentDay = endDay;
        }

        double totalHoldingCost = 0.0;

        for (Map.Entry<String, Integer> entry : firstAppearance.entrySet()) {
            String actorId = entry.getKey();
            int start = entry.getValue();
            int end = lastAppearance.get(actorId);

            Actor actor = actors.get(actorId);
            if (actor != null) {
                int totalDaysOnContract = end - start;
                totalHoldingCost += totalDaysOnContract * actor.getDailyRate();
            }
        }

        return totalHoldingCost;
    }
}