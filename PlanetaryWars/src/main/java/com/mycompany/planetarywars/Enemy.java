package com.mycompany.planetarywars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Character {
    private final List<Skill> skills;  
    private final int[] skillCooldowns;  
    private final Random random = new Random();
    private final boolean isMainStory; 


    public Enemy(String name, int troops, int damage, boolean isMainStory) {
        super(name, troops, damage);  
        this.skills = new ArrayList<>();  
        this.skillCooldowns = new int[2];  
        this.isMainStory = isMainStory;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);  
    }

    @Override
    public void heal(int amount) {
        troops += amount;
        if (troops > maxTroops) {
            troops = maxTroops;
        }
    }

    public List<Skill> getSkills() {
        return skills;  
    }

    public void updateCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) {
                skillCooldowns[i]--;  
            }
        }
    }

    public boolean canUseSkill(int index) {
        return index >= 0 && index < skillCooldowns.length && skillCooldowns[index] == 0;
    }

    public void useSkillCooldown(int skillIndex) {
        if (skillIndex >= 0 && skillIndex < skillCooldowns.length) {
            skillCooldowns[skillIndex] = skills.get(skillIndex).getCooldown();  
        }
    }

    @Override
    public int attack() {
        int baseDamage = super.getDamage();
        return baseDamage;  
    }

    public int makeAttack() {
        int action = random.nextInt(3) + 1;  

        switch (action) {
            case 1 -> {  
                int normalDamage = attack();  
                return normalDamage;

            }
            case 2 -> {  
                if (canUseSkill(0)) {
                    int damage = skills.get(0).useSkill();  
                    useSkillCooldown(0);  
                    return damage;
                }
                 
                return attack();  
            }
            case 3 -> {  
                if (canUseSkill(1)) {
                    int damage = skills.get(1).useSkill();  
                    useSkillCooldown(1);  
                    return damage;
                }
                 
                return attack();  
            }
            default -> {
                return attack();  
            }
        }
    }

    public boolean isMainStory() {
        return isMainStory;
    }

    public String getPostBattleStory(){
        return null;
    }
}

//main encounters(unique per faction)
class SteelMarauders extends Enemy {
    public SteelMarauders() {
        super("Steel Marauders", 30, 12, true);
        super.addSkill(new Skill("Steel Charge", 10, 15, 2, true));  
        super.addSkill(new Skill("Shield Bash", 5, 8, 3, false));  
    }
}

class VoidCultists extends Enemy {
    public VoidCultists() {
        super("Void Cultists", 75, 16, true);
        super.addSkill(new Skill("Dark Ritual", 12, 18, 3, true));  
        super.addSkill(new Skill("Chaos Bolt", 14, 22, 4, false));  
    }
}

class BattleOrks extends Enemy {
    public BattleOrks() {
        super("Battle Orks", 100, 20, true);
        super.addSkill(new Skill("Ork Rampage", 20, 30, 5, true));  
        super.addSkill(new Skill("Berserk Thrashings", 30, 40, 4, false));  
    }
}

class ChaosRenegades extends Enemy {
    public ChaosRenegades() {
        super("Chaos Renegades", 60, 12, false);
        super.addSkill(new Skill("Renegade Ambush", 10, 15, 3, true));  
        super.addSkill(new Skill("Explosive Trap", 18, 25, 4, false));  
    }
}

class XenoRaiders extends Enemy {
    public XenoRaiders() {
        super("Xeno Raiders", 11, 4, false);
        super.addSkill(new Skill("Xeno Stealth", 8, 12, 2, true));  
        super.addSkill(new Skill("Arc Blast", 5, 10, 3, false));  
    }
}

class WarpChampion extends Enemy {
    public WarpChampion() {
        super("Warp Champion", 16, 5, false);
        super.addSkill(new Skill("Warp Strike", 12, 18, 3, true));  
    }
}

class WraithLegion extends Enemy {
    public WraithLegion() {
        super("Wraith Legion", 50, 4, false);
        super.addSkill(new Skill("Phantom Strike", 10, 14, 2, true));  
        super.addSkill(new Skill("Wraith Walk", 8, 15, 5, false));  
    }
}

class VoidReavers extends Enemy {
    public VoidReavers() {
        super("Void Reavers", 75, 3, false);
        super.addSkill(new Skill("Void Shot", 6, 10, 2, true));  
    }
}

class DarkSovereign extends Enemy {
    public DarkSovereign() {
        super("The Death Lord", 150, 6, false);
        super.addSkill(new Skill("Necrotic Touch", 15, 25, 4, true));  
    }
}


//random encounters
class MalgrimTheFoul extends Enemy {
    public MalgrimTheFoul() {
        super("Malgrim the Foul", 130, 15, false);
        super.addSkill(new Skill("Corruption Touch", 25, 32, 5, true));
        super.addSkill(new Skill("Soul Leech", 20, 35, 3, false));  
    }
}

class ThraxTheRavager extends Enemy {
    public ThraxTheRavager() {
        super("Thrax the Ravager", 120, 25, false);
        super.addSkill(new Skill("Savage Slash", 30, 28, 4, false));
        super.addSkill(new Skill("Ravage", 40, 50, 6, false));  
    }
}

class VarkonTheUnholy extends Enemy {
    public VarkonTheUnholy() {
        super("Varkon the Unholy", 160, 18, false);
        super.addSkill(new Skill("Infernal Blaze", 35, 60, 4, false));
        super.addSkill(new Skill("Void Grasp", 20, 30, 5, true)); 
    }
}

class PlagueCultists extends Enemy {
    public PlagueCultists() {
        super("Plague Cultists", 40, 8, false);
        super.addSkill(new Skill("Blighted Strike", 10, 15, 2, true));
        super.addSkill(new Skill("Plague Cloud", 5, 10, 3, false)); 
    }
}

class ChaosZealots extends Enemy {
    public ChaosZealots() {
        super("Chaos Zealots", 50, 10, false);
        super.addSkill(new Skill("Fanatic Slash", 12, 18, 3, true));
        super.addSkill(new Skill("Dark Gift", 15, 25, 4, false)); 
    }
}

class PossessedEnforcer extends Enemy {
    public PossessedEnforcer() {
        super("Possessed Enforcer", 85, 14, false);
        super.addSkill(new Skill("Infernal Lash", 18, 25, 4, true));
        super.addSkill(new Skill("Hellfire Bolt", 15, 20, 3, false)); 
    }
}

//final bosses
class Bloodreaver extends Enemy {
    public Bloodreaver() {
        super("Bloodreaver, God of Fury", 120, 20, true);
        super.addSkill(new Skill("Blood Frenzy", 15, 25, 4, true));
        super.addSkill(new Skill("Rage Slash", 30, 40, 5, false));
    }

    public String getIntro() {
        return """
            As you step onto the barren battlefield, a sudden chill sweeps through the air, 
            carrying with it a scent—coppery, sharp, unmistakable: blood. 
            The ground beneath your feet trembles, and the earth seems to pulse, as if alive with anger. 
            
            From beyond a ridge, you hear a deep, primal roar that shakes the very air around you. 
            Over the hill strides a figure, massive and brutal, silhouetted against the setting sun. 
            Muscles rippling, eyes burning with unquenchable fury, he drags two enormous axes behind him, 
            their edges gleaming with the fresh blood of countless victims. 
            
            This is Bloodreaver, the God of Fury—a war deity born of rage and baptized in slaughter. 
            His lips twist into a cruel smile as he locks eyes with you, a predator sizing up prey. 
            Blood still dripping from his weapons, he raises them high, the axes catching the last rays of daylight, 
            casting a crimson hue across the field. 
            
            A guttural voice echoes across the field. “I shall bathe this ground in your blood,” he sneers. 
            He begins to charge, each footstep booming like thunder, as he closes the distance, thirsting for carnage.
            """;
    }

    @Override
    public String getPostBattleStory() {
        return "As you stand over Bloodreaver’s fallen form, the battlefield is drenched in crimson, " +
               "a stark testament to the fury you have overcome. His monstrous body, once towering and " +
               "intimidating, now lies still, a shadow of the wrath that once threatened to consume all." +
               "\n\nYou feel the weight of victory in your chest, but it is laden with the memories of every " +
               "sacrifice made along the way. The echoes of war settle around you, and in the distance, " +
               "your remaining troops lift their weary voices in a shout of triumph, their cries echoing " +
               "against the backdrop of a world saved, if only for the moment." +
               "\n\nYet, in this hollow victory, a darkness lingers. You know that the threat of chaos is far from over. " +
               "Your journey has forged you into a seasoned Commander, but it has not come without cost; " +
               "every enemy cut down fuels a cycle of vengeance that may one day rise against you once more.";
    }
}

class Aetherbound extends Enemy {
    public Aetherbound() {
        super("Aetherbound, Weaver of Fate", 110, 15, true);
        super.addSkill(new Skill("Warping Reality", 20, 30, 3, true));
        super.addSkill(new Skill("Illusory Strike", 15, 25, 4, false) {
            @Override
            public int useSkill() {
                int damage = super.useSkill();
                if (Math.random() < 0.25) {
                    damage *= 2;
                }
                return damage;
            }
        });
    }

    public String getIntro() {
        return """
            The world around you begins to waver, as if a dense fog has fallen, blurring your vision. 
            Shapes twist and stretch at the edges of your sight, shadows slithering and reforming. 
            The air itself feels thick and heavy, as though the very fabric of reality has begun to bend.

            Suddenly, a figure emerges from this haze—a slender, ethereal being whose form shifts like smoke in a breeze. 
            This is Aetherbound, the Weaver of Fate. His face is obscured, constantly changing, 
            as if hundreds of visages are layered atop one another. In his eyes, you glimpse endless paths, 
            realities that may never be, and others that might.

            Aetherbound’s voice reaches you, echoing from all directions at once, 
            “I am the keeper of what was, what is, and what could be. Let us see where your thread unravels.” 
            With a flick of his hand, he weaves the air itself, the space around you distorting, 
            creating illusions and possibilities that shimmer and vanish. 
            You sense that every step, every action, is being pulled into some great cosmic design—one that he controls.
            """;
    }

    @Override
    public String getPostBattleStory() {
        return "With Aetherbound's final scream echoing into the void, the realm around you shifts and warps, " +
               "the fabric of reality unraveling like a frayed tapestry. All his machinations and illusions fade, " +
               "leaving only the remnants of a conflict that blurred the lines between fate and free will." +
               "\n\nYou absorb the enormity of what you've achieved; this victory holds a deeper significance—a " +
               "threat to the threads of existence itself have been severed by your hand. As your men stand victorious, " +
               "they gaze upon the remnants of the battlefield, aware of the sinister ease with which fate was once " +
               "woven against them. " +
               "\n\nEven as the Aetherbound's powerless body lies before you, whispers in the winds remind you that " +
               "the strands of destiny are fickle, and new challenges will rise from the shadows, eager to rewrite the tale...";
    }
}

class Plaguebringer extends Enemy {
    public Plaguebringer() {
        super("Plaguebringer, Lord of Decay", 150, 12, true);
        super.addSkill(new Skill("Plague Breath", 25, 35, 3, true));
        super.addSkill(new Skill("Decay Touch", 10, 20, 4, false));
    }

    public String getIntro() {
        return """
            A heavy mist rolls across the ground, clinging to everything it touches, bringing with it a sickly, rancid stench. 
            The plants around you wither, blackening and curling as though aged centuries in mere moments. 
            
            From the depths of this toxic fog, a silhouette emerges, cloaked in rot and ruin. 
            This is Plaguebringer, the Lord of Decay. His very presence seems to drain life itself, 
            leaving a trail of death in his wake. His skin is pallid and mottled, oozing with the filth of countless diseases, 
            and in his eyes, you see a gluttonous hunger for pestilence and suffering.

            He regards you with a ghastly smile, his voice wet and gurgling, 
            “Do you smell it? The sweet scent of rot. Let it fill your lungs... soon, you will be one with the decay.” 
            
            His hands reach out, fingers blackened and twisted, dripping with a viscous green liquid. 
            Each step he takes seems to drain the color from the world, 
            leaving behind a desolate wasteland that only he commands.
            """;
    }

    @Override
    public String getPostBattleStory() {
        return "As the foul miasma of Plaguebringer dissipates into the air, you are left standing in a " +
               "graveyard of death and decay. The corrupted land that once thrived under the Lord of Decay's " +
               "control is now breaking beneath the weight of his downfall." +
               "\n\nWretched bodies lie in testament, a grim reminder of what unchecked rot can create. Your victory " +
               "is bittersweet as you look upon the field, where life once thrived now serves only as an echo of " +
               "the suffering that had been endured." +
               "\n\nYour forces, mired in the foul stench of what had been, gather around, weary and scarred. " +
               "Yet, from this ruin, a phoenix of hope begins to rise; you have reclaimed this land from the brink " +
               "of utter collapse. But as you look towards the horizon, the burden of what remains—the shadows of " +
               "the Plague Cultists—lingers, suggesting that your fight against decay is only just beginning.";
    }
}

class Indulgence extends Enemy {
    public Indulgence() {
        super("Indulgence, Prince of Excess", 100, 16, true);
        super.addSkill(new Skill("Temptation", 20, 30, 2, true)); 
        super.addSkill(new Skill("Excessive Indulgence", 25, 40, 4, false)); 
    }

    @Override
    public int attack() {
        int damage = super.attack();
        // Heal for 20% of the damage dealt
        healDamage((int)(damage * 0.2));
        return damage;
    }

    private void healDamage(int amount) {
        super.heal(amount);
    }

    public String getIntro() {
        return """
            The atmosphere shifts, and you’re overwhelmed by a strange, intoxicating aroma— 
            a blend of sweet perfume, fragrant spices, and something darker beneath. 
            Shapes dance in the corners of your vision, figures cloaked in shadows, 
            whispering secrets of desire, power, and forbidden indulgence.

            From this haze steps Indulgence, the Prince of Excess. 
            Draped in silks and jewels that catch the light, he moves with an unnerving grace, 
            his smile both welcoming and sinister. His eyes, filled with hunger and curiosity, study you as though you’re a prize waiting to be claimed. 

            In a voice like honeyed poison, he speaks, “Come closer, won’t you? There’s so much I can offer... pleasures beyond imagining, power without restraint. 
            All you have to do is submit to me.” 

            He reaches out a hand, inviting you forward, but something in his gaze tells you that indulgence comes at a cost—a cost he’s eager to collect.
            """;
    }

    @Override
    public String getPostBattleStory() {
        return "With Indulgence’s defeat, the intoxicating haze that once draped the battlefield begins to lift, " +
               "revealing the stark reality masked by his seductive charm. You stand victorious, but the cost of " +
               "the battle weighs heavily on your conscience, for every foe defeated was once a life touched " +
               "by temptation." +
               "\n\nYour soldiers look around, their expressions a mixture of relief and horror, understanding " +
               "now the depths of excess that had ensnared countless others. The echoes of sensuous whispers fade, " +
               "replaced by the harsh truths of duty and sacrifice—a hard lesson learned after confronting the darkest " +
               "aspects of temptation." +
               "\n\nAs you rally your troops, the winds shift, carrying away the remnants of illusions, reminding you " +
               "that the real battle lies ahead. The age of excess may be over for now, but the shadows of other " +
               "desires lurk just beyond the horizon.";
    }
}
