package com.mycompany.planetarywars;

import java.util.Random;

public class Skill {
    private final String name;
    private final int minDamage;
    private final int maxDamage;
    private final int cooldown;
    private final boolean isFlatDamage;

    private final Random random;

    public Skill(String name, int minDamage, int maxDamage, int cooldown, boolean isFlatDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.cooldown = cooldown;
        this.isFlatDamage = isFlatDamage;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int useSkill() {
         
        return random.nextInt((maxDamage - minDamage) + 1) + minDamage;  
    }

    public boolean isFlatDamage() {
        return isFlatDamage;  
    }

    public int getCooldown() {
        return cooldown;
    }
    
    @Override
    public String toString() {
        return name + " (Damage: " + minDamage + "-" + maxDamage + ", Cooldown: " + cooldown + ")";
    }
}
