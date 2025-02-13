package com.mycompany.planetarywars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player extends Character {
    private int gold;  
    private final List<Skill> skills;  
    private final int[] skillCooldowns;
    private int damageOverTime=0;
    private int goldBoost=0;
    private boolean hasDamageOverTimeItem = false;
    private boolean hasGoldBoostItem = false;
    private boolean acceptedArtifactChallenge;
    
    public Player(String name, int troops, int damage) {
        super(name, troops, damage);  
        this.gold = 0;  
        this.skills = new ArrayList<>();  
        this.skillCooldowns = new int[2];
    }

    private final Random random = new Random();


    @Override
    public String getName() {
        return super.getName();  
    }

    @Override
    public int getTroops() {
        return super.getTroops();  
    }

    public int getGold() {
        return gold;  
    }

    public void addGold(int amount) {
        this.gold += amount;  
    }

    @Override
    public boolean isAlive() {
        return getTroops() > 0;  
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);  
    }

    public void heal() {
        setTroops(super.getMaxTroops());
        addGold(-2500);
    }
    
    @Override
    public int attack() {
        return random.nextInt(getDamage() + 1);
    }

    public int calculateDamage() {
        return super.getDamage();
    }

    public int attackWithSkill(int skillIndex) {
        if (canUseSkill(skillIndex)) {
            Skill skill = skills.get(skillIndex);  
            int damage = skill.useSkill();  
            useSkillCooldown(skillIndex, skill);  
            if (skill.isFlatDamage()) {
                return damage;  
            } else {
                 
                return (int) (calculateDamage() * (1 + (damage / 100.0))); 
            }
        } else {
            throw new IllegalStateException("Skill is on cooldown!");
        }
    }

    public void updateCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) {
                skillCooldowns[i]--;  
            }
        }
    }

    public void useSkillCooldown(int skillIndex, Skill skill) {
        skillCooldowns[skillIndex] = skill.getCooldown();  
    }

    public boolean canUseSkill(int skillIndex) {
        if (skillIndex < 0 || skillIndex >= skillCooldowns.length) {
            return false;  
        }
        return skillCooldowns[skillIndex] == 0;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);  
    }

    public List<Skill> getSkills() {
        return skills;  
    }

    public void applyEffect(Item item) {
        switch (item.getEffectType()) {
            case HEALTH_BOOST -> {
                heal();
                this.maxTroops += item.getEffectValue();
                heal();
            }
            case DAMAGE_OVER_TIME -> {
                this.hasDamageOverTimeItem = true;
                this.damageOverTime += item.getEffectValue(); 
            }
            case GOLD_BOOST -> this.goldBoost += item.getEffectValue();
        }
    }

    public int getDamageOverTime() {
        return random.nextInt(damageOverTime + 1)+1;
    }

    public boolean hasDamageOverTimeItem() {
        return hasDamageOverTimeItem;
    }

    public int getGoldBoost() {
        return goldBoost;
    }

    public void applyDamageOverTime() {
        if (damageOverTime > 0) {
            takeDamage(damageOverTime);
        }
    }

    @Override
    public void setTroops(int troops) {
        super.troops = troops;
    }
    
    public void setHasDamageOverTimeItem(boolean hasItem) {
        this.hasDamageOverTimeItem = hasItem;
    }

    public void setHasGoldBoostItem(boolean hasItem) {
        this.hasGoldBoostItem = hasItem;
    }

    public boolean hasGoldBoostItem() {
        return hasGoldBoostItem;
    }


    
    public void setGoldBoost(int goldBoost) {
        this.goldBoost = goldBoost;
    }

    public void acceptArtifactChallenge() {
        this.acceptedArtifactChallenge = true;
    }

    public boolean hasAcceptedArtifactChallenge() {
        return acceptedArtifactChallenge;
    }

}


class ImperialGuard extends Player {
    public ImperialGuard(String name) {
        super(name, 100, 15);  //low damage but high troops 
        super.addSkill(new Skill("Rocket Launcher", 10, 15, 2, true));  
        super.addSkill(new Skill("Plasma Shot", 15, 25, 2, true));  
        super.addSkill(new Skill("Grenade", 10, 20, 3, false));  
    }
}

class SpaceSoldier extends Player {
    public SpaceSoldier(String name) {
        super(name, 60, 30);  
        super.addSkill(new Skill("Laser Burst", 12, 22, 2, true));  
        super.addSkill(new Skill("Explosive Charge", 18, 30, 4, false));  
    }
}

class BloodyNecron extends Player {
    private final double healPercentage = 0.5;
    public BloodyNecron(String name) {
        super(name, 45,20);  
        super.addSkill(new Skill("Necron Beam", 20, 35, 3, true));  
        super.addSkill(new Skill("Reanimation Protocol", 5, 15, 5, false));  
    }

    @Override
    public int attack(){  //passive: heal some damage dealt
        int damageDealt = super.attack();
        heal((int) (damageDealt * healPercentage));
        return damageDealt;
    }
}
