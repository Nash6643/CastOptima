package com.castoptima.evaluation;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CostCalculator {
    
    /**
     * Calculates the holding cost for actors when they are idle on set between their first and last shooting days.
     */
    public static double calculateHoldingCost(List<Scene> schedule, Map<String, Actor> actorMap) {
        Map<String, Integer> firstDay = new HashMap<>();
        Map<String, Integer> lastDay = new HashMap<>();

        int currentDay = 0;
        for (Scene scene : schedule) {
            for (String actorId : scene.getRequiredActorIds()) {
                firstDay.putIfAbsent(actorId, currentDay);
                lastDay.put(actorId, currentDay + scene.getDurationDays() - 1);
            }
            currentDay += scene.getDurationDays();
        }

        double totalHoldingCost = 0.0;
        for (String actorId : firstDay.keySet()) {
            int span = lastDay.get(actorId) - firstDay.get(actorId) + 1;
            Actor actor = actorMap.get(actorId);
            if (actor != null) {
                totalHoldingCost += span * actor.getDailyRate();
            }
        }

        return totalHoldingCost;
    }
}