package com.castoptima.model;

public class Actor {
    private final String id;
    private final String name;
    private final double dailyRate;

    public Actor(String id, String name, double dailyRate) {
        this.id = id;
        this.name = name;
        this.dailyRate = dailyRate;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getDailyRate() { return dailyRate; }
}