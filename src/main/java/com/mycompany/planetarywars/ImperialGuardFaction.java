package com.mycompany.planetarywars;

import java.util.List;

class ImperialGuardFaction extends Faction {
    
    public ImperialGuardFaction(String factionType) {
        super(factionType);
        initialize();
    }


    @Override
    public void createEnemies() {
        enemies = new Enemy[]{
            new SteelMarauders(),
            new VoidCultists(),
            new BattleOrks()
        };
    }
    
    private void initialize() {
        createEnemies();  
    }

    @Override
    public String getFactionType(){
        return "Imperial Guard";
    }

    @Override
    public String getIntro() {
        return "In the vast reaches of the galaxy, far from the shining core worlds of the Imperium, lies the "
                + "domain of the Imperial Guard, an elite order of warriors bound by duty to defend the Imperium’s "
                + "frontier worlds. Clad in imposing armor emblazoned with the symbol of the Imperium, they are "
                + "often the first—and sometimes only—line of defense against threats that would consume their "
                + "Emperor’s domain. Their creed is simple but unyielding: to protect, to serve, and to preserve the "
                + "legacy of the Emperor at all costs.\n"
                + "As rumors of traitorous soldiers and evil cultists brew in the horizon; You, the honored "
                + "Commander, head out with your troops to fight against the dark forces that threaten the peace "
                + "of the Imperium.";
    }

    @Override
public List<String> getStorytellingLines() {
    return List.of(
        "Your deployment orders are clear: eliminate any threat before it reaches the heart of the Imperium. The transport carrier rumbles as it pierces through the atmosphere, bringing your regiment down to the desolate, hostile surface of a border world. The mission is simple: clear the area, and secure it for Imperial interests. The lives of your men are the price of victory.",
        
        "As the commander of the Imperial Guard, you are the tip of the spear. The mission is never about being the strongest, the fastest, or the smartest—it's about throwing enough of your soldiers at the enemy to ensure they are overwhelmed. The Emperor demands it. Your men are expendable, but their sacrifice will bring the Imperium one step closer to victory. Reinforcements will not arrive in time. It is up to you and your men to hold the line.",
        
        "The dropship’s door opens, and the dust and heat of the barren world pour in. You survey the battlefield as your regiment disembarks. Hundreds of Guardsmen move in perfect formation, their boots marching in unison. They are your charge, your responsibility. Though they are many, each soldier is nothing more than a cog in the machine, ready to be spent in service of the Emperor.",
        
        "A veteran sergeant at your side salutes, then mutters, 'Sir, it’s not about how good we are; it’s about how many we can throw at the enemy. We win with numbers, not skill.' The weight of the words settles on you. It’s a harsh truth, but one you’ve come to accept. Your troops are expendable, but they are also your greatest strength.",
        
        "Ahead, movement is detected. The enemy is closing in, faster and more lethal than your men. They outmatch your forces in individual combat, but they are few. You can afford to lose soldiers—your men are many. With a firm command, you issue the order to advance, knowing the cost. It is your responsibility to lead them to victory, regardless of the sacrifice.",
        
        "Your regiment surges forward, rifles raised, their battle cries filling the air. The enemy opens fire, cutting down some of the first Guardsmen to reach the front. The casualties are heavy, but your forces do not falter. You direct wave after wave of soldiers forward, urging them to close the distance. The enemy cannot match the weight of your numbers. It is your strategy that will break them, not brute strength.",
        
        "As the battle rages, you issue commands, rallying your men. 'Hold the line! For the Emperor and for our home!' The cry is echoed across the battlefield, a roar of defiance. Your men are weak, but their resolve is unbreakable. Even as they fall, their sacrifice makes way for the next wave. The enemy may kill many, but they cannot stop your endless tide of soldiers.",
        
        "When the dust finally settles, you stand amidst the wreckage. The enemy lies scattered, broken by the overwhelming numbers of your troops. Your casualties are high, but the mission is a success. You know this is just the beginning. Every victory is paid in blood, and every step forward brings you closer to the ultimate goal. As the commander, it is your duty to lead, to command, and to keep pushing forward, no matter the cost."
    );
}


    @Override
    public String getFirstBattleIntro() {
        return "As your army marches on to defend their honor and empire, you encounter hostile soldiers that "
                + "your troops had once considered allies. The Steel Marauders, once loyal soldiers of the "
                + "Imperium, they turned traitor after their homeworld was abandoned during a major conflict. Now, "
                + "they roam the galaxy as warlords, wielding heavy tanks and artillery. These are the obstacles "
                + "that stand before you, and the first of many to stop you on your path of bringing peace to the "
                + "galaxy. As your loyal troops brandish their weapons, you charge towards the traitorous army.";
    }

    @Override
    public String getFirstBattlePostBattle() {
        return "As the smoke settles and the echoes of artillery fade into silence, you and your troops stand "
                + "amid the wreckage of the Steel Marauders’ once-imposing forces. The charred remains of their "
                + "tanks and twisted artillery lie strewn across the battlefield, a stark reminder of the devastation "
                + "wrought by betrayal. The ground is littered with the remnants of old alliances, fractured and "
                + "broken under the strain of lost trust. With renewed resolve, you give the order to move out, "
                + "leaving the battlefield behind as your army presses onward into the uncertain future. The Steel "
                + "Marauders were but the first, and you know that even darker foes await on your journey to "
                + "reclaim the galaxy.";
    }

    @Override
    public String getSecondBattleIntro() {
        return "As your forces advance through dreary ruins, you feel a chill run down your spine. From the "
                + "crumbled walls you can see inhuman silhouettes surrounding your army, eyes glowing an eerie "
                + "glow. The Void Cultists emerge from the darkness, their robes adorned with twisted symbols of "
                + "the Warp, whispering ritualistic chants that reverberate like a low, maddening hum. As "
                + "worshippers of The Warp that threaten peace to the galaxy, you know that they do not bear "
                + "friendly intentions. But your forces press on, steeling themselves against the psychic onslaught, "
                + "knowing that the Imperium’s fate may rest on their victory.";
    }

    @Override
    public String getSecondBattlePostBattle() {
        return "As the final echoes of the Void Cultists' twisted chants fade, an unsettling silence settles over "
                + "the battlefield. The once-glowing eyes of the fallen cultists dim to hollow darkness, their robes "
                + "sprawled across the ground like remnants of a nightmare. The air is thick with the lingering taint "
                + "of the Warp, its foul energy slowly dissipating but leaving a metallic taste in your mouth—a "
                + "reminder of the horrors narrowly averted. Your soldiers, though battered and weary, stand "
                + "victorious, their resolve unbroken despite the cultists’ attempts to shatter their minds. You "
                + "rally your forces, lifting them from the dreary ruins. Tired but full of determination, you "
                + "descend further into these galactic ruins. Though unbeknownst to you or your forces…this was not "
                + "going to be the last you’ll see of the Void Cult.";
    }

    @Override
public String getThirdBattleIntro() {
    return "Da WAAAGH is here. You knew the greenskins were rowdy, but this? This is madness. Across the horizon,"+
            "a sea of Orks stretches as far as the eye can see, their crude vehicles belching smoke and their voices"+
           "rising in deafening war cries. The ground shakes with their chaotic charge—buggies, trukks, and lumbering"+
            "walkers cobbled together from scrap, all barreling toward your position with reckless abandon. \n\n" +
           "At the head of the horde, the biggest and meanest of their Warbosses waves a jagged banner and bellows," +
           "'WAAAGH!' The cry is taken up by thousands of his boyz, a chaotic symphony of rage and violence."+
           "You stand atop your command post, rallying your troops. 'Hold the line! Don’t let them through!'" +
           "The air fills with the crackle of lasguns and the thunder of heavy bolters as your soldiers unleash"+
           "everything they have. But the Orks aren’t slowing down—they thrive in the chaos, feeding on the battle’s energy"+
           "as they close the distance.\n\n" +
           "This is no ordinary skirmish. This is a full-scale assault, the culmination of the Orks' endless lust for war." +
           "Their only goal is to smash everything in their path. The odds are grim, but as the commander of the Imperial Guard," +
           "you know one truth: your men will fight to their last breath. It’s not about finesse or strategy." +
           "It’s about who can outlast the other. And today, you’ll make sure the Orks learn what it means to face the unwavering resolve" +
           "of the Guard.";
}


@Override
public String getThirdBattlePostBattle() {
    return "The battlefield is a wasteland of scorched earth, broken machines, and fallen soldiers."+
            "The Ork horde, once an unstoppable tide of green-skinned fury, has finally been broken."+
            "Crude Ork vehicles lie smoldering in the distance, and the remaining greenskins retreat in disorder,"+
            "their WAAAGH silenced—at least for now. Your soldiers stand battered and bloodied, but victorious,"+
            "amidst the wreckage. For every Guardsman lost, ten Orks were cut down. \n\n" +
            "You survey the battlefield from atop your command post. The carnage is immense,"+
            "but your regiment held firm. The Orks’ brute strength and unrelenting charge were no match for"+
            "disciplined fire and unwavering determination. The cost was high, but the enemy has been driven back."+
            "A faint grin crosses your face as you realize the Guard’s strength: no matter the odds, no matter the enemy,"+
            "your troops endure. \n\n" +
            "You signal the troops to regroup and secure the area. Supply drops and reinforcements are already on the way—another"+
            "battle looms on the horizon. But for now, the victory belongs to the Imperial Guard. The greenskins are scattered,"+
            "their WAAAGH crushed, and their Warboss defeated. 'Another day, another win for the Emperor,' you mutter,"+
            "as the banners of the Guard are raised over the battlefield.";
}

    
    @Override
    public String getEndingStory() {
        return "With every enemy vanquished and their plans for chaos thwarted, the battlefield lies before you, " +
               "a testament to the bravery and sacrifice of the Imperial Guard. The echoes of war fade, replaced " +
               "by the stillness that follows such a great conflict." +
               "\n\nYou survey the remains of the enemies that threatened the Imperium and reflect on the " +
               "lives lost in pursuit of peace. Each fallen Guardsman was not just a soldier; they were a part " +
               "of something greater, their sacrifices a stark reminder of the costs of war." +
               "\n\nYour troops celebrate their hard-won victory, their cries filled with pride and allegiance to " +
               "the Emperor. The banners of the Imperial Guard flutter in the breeze, a symbol of hope and unwavering " +
               "resolve that resonates through the hearts of all who stand against tyranny." +
               "\n\nAs you march your men back to the garrison, you know that this is not the end. For every battle " +
               "won, new threats will always arise. But under your command, the Imperial Guard will continue to stand " +
               "vigilant, ready to protect the Imperium at all costs, deferring to the will of the Emperor and countering " +
               "the darkness threatening to consume the galaxy.";
    }

}