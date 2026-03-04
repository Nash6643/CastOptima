package com.castoptima.model;

import java.util.Set;

public class Scene {
    private final String id;
    private final String title;
    private final int durationDays;
    private final Set<String> requiredActorIds;
    private final double baseCost;

    public Scene(String id, String title, int durationDays, Set<String> requiredActorIds, double baseCost) {
        this.id = id;
        this.title = title;
        this.durationDays = durationDays;
        this.requiredActorIds = requiredActorIds;
        this.baseCost = baseCost;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationDays() { return durationDays; }
    public Set<String> getRequiredActorIds() { return requiredActorIds; }
    public double getBaseCost() { return baseCost; }
}