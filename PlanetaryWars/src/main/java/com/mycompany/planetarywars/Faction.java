package com.mycompany.planetarywars;

import java.util.List;

abstract class Faction {
    protected Enemy[] enemies;  
    private int currentEnemyIndex;
    private final String factionType;

    public Faction(String factionType) {
        this.currentEnemyIndex = 0;
        this.factionType = factionType;
    }

    public Enemy[] getEnemies() {
        return enemies;  
    }

    public abstract void createEnemies();

    public void setCurrentEnemyIndex(int currentEnemyIndex) {
        this.currentEnemyIndex = currentEnemyIndex;
    }

    public void incrementEnemy() {
        this.currentEnemyIndex++;
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }

    public boolean hasMoreEnemies() {
        return currentEnemyIndex < enemies.length;  
    }

    public Enemy getCurrentEnemy() {
        if (currentEnemyIndex < enemies.length) {
            return enemies[currentEnemyIndex];  
        }
        return null;  
    }
    public String getFactionType(){
        return factionType;
    }

    public abstract String getIntro();

    public abstract List<String> getStorytellingLines();

    public abstract String getFirstBattleIntro();
    
    public abstract String getFirstBattlePostBattle();

    public abstract String getSecondBattleIntro();
    
    public abstract String getSecondBattlePostBattle();
    
    public abstract String getThirdBattleIntro();
    
    public abstract String getThirdBattlePostBattle();

    public abstract String getEndingStory();

}
