package com.castoptima.io;

import com.castoptima.model.Scene;
import java.util.List;

public class ScheduleExporter {

    public static String exportToFormattedText(List<Scene> schedule) {
        if (schedule == null || schedule.isEmpty()) {
            return "=== CastOptima Schedule: No Scenes Scheduled ===";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== CastOptima Optimized Production Schedule ===\n");
        int currentDay = 1;
        for (Scene s : schedule) {
            sb.append(String.format("Day %d-%d: [Scene: %s] %s | Required Cast: %s%n",
                    currentDay, currentDay + s.getDurationDays() - 1,
                    s.getId(), s.getTitle(), s.getRequiredActorIds()));
            currentDay += s.getDurationDays();
        }
        return sb.toString();
    }
}