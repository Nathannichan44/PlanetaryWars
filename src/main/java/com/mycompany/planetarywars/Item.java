package com.mycompany.planetarywars;

public class Item {
    public enum EffectType {
        HEALTH_BOOST,
        DAMAGE_OVER_TIME,
        GOLD_BOOST
    }

    private final String name;
    private final int cost;
    private final EffectType effectType;
    private final int effectValue;
    private boolean isOwned;

    public Item(String name, int cost, EffectType effectType, int effectValue) {
        this.name = name;
        this.cost = cost;
        this.effectType = effectType;
        this.effectValue = effectValue;
        this.isOwned = false;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }
}
