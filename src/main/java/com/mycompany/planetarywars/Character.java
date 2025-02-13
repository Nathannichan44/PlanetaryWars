package com.mycompany.planetarywars;

abstract class Character {
    private final String name;
    protected int troops;  
    private final int damage;
    protected int maxTroops;  
    

    public Character(String name, int troops, int damage) {
        this.name = name;
        this.troops = troops;
        this.damage = damage;
        this.maxTroops = troops;
    }

    public int attack() {
        return damage;
    }

    public void takeDamage(int damage) {
        troops -= damage;
        if (troops < 0) {
            troops = 0;
        }
    }

    public boolean isAlive() {
        return troops > 0;
    }

    public String getName() {
        return name;
    }

    public void setTroops(int troops) {
        this.troops = troops;  
    }
    
    public int getTroops() {
        return troops;
    }
    public int getMaxTroops() {
        return maxTroops;
    }

    public void fullyHeal() {
        this.troops = maxTroops;
    }

    public void heal(int amount) {
        troops += amount;
        if (troops > maxTroops) {
            troops = maxTroops;
        }
    }
    
    public int getDamage() {
        return damage;
    }

    
    
}
