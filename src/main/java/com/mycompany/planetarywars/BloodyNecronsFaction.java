package com.mycompany.planetarywars;

import java.util.List;

class BloodyNecronsFaction extends Faction {
    
    public BloodyNecronsFaction(String factionType) {
        super(factionType);
        initialize();
    }


    @Override
    public void createEnemies() {
        enemies = new Enemy[]{
            new WraithLegion(),
            new VoidReavers(),
            new DarkSovereign()
        };
    }

    private void initialize() {
        createEnemies();  
    }

    @Override
    public String getFactionType(){
        return "Bloody Necrons";
    }


    @Override
    public String getIntro() {
        return "In the twilight of a forgotten era, the Bloody Necrons emerged from the depths of despair, " +
               "ancient guardians who once swore to protect the cosmos. Betrayed by their own ambition and stripped " +
               "of their souls, they now rise as cold, unyielding metal warriors, driven only by an insatiable hunger " +
               "for conquest. Led by the infamous Deathlord, whose very name sends shivers through the hearts of the " +
               "living, the Bloody Necrons march forth from their tombs, ready to reclaim the galaxy. With every pulse " +
               "of their war machines, they seek to extinguish the flickering light of life, forging a future where " +
               "silence reigns supreme, and only they remain.\n";
    }

@Override
public List<String> getStorytellingLines() {
    return List.of(
        "Awakening from the ancient crypts beneath the cold soil, your forces stir as their undead forms rise to the surface. Your objective is simple but brutal: cleanse the area of any who dare resist the Necrons’ eternal march.",

        "You stand as their commander, overseeing a battalion that doesn’t know fear or pain. These are warriors of a bygone era, relentless in their pursuit of total conquest. Unlike others, your troops can fall, yet they rise again, reassembled and ready to press onward.",

        "The air is filled with the eerie hum of ancient machinery and the metallic sound of servos as your legions emerge. Enemy forces lie ahead, ignorant of the horror they are about to face. They think they can fight you, but they do not understand. You are no mere army. You are a tide of undeath that cannot be stemmed.",

        "Through your command, these relentless warriors advance. Their red eyes blaze in unison, casting a glow upon the battlefield that hints at their boundless fury. For every Necron that falls, another rises, reformed and reborn to continue the onslaught.",

        "Your mission is to scour every trace of life from this territory. The galaxy is but a graveyard waiting to be claimed, and you, the commander of the Bloody Necrons, will see it fall silent once again."
    );
}
    
    @Override
    public String getFirstBattleIntro() {
        return "The horizon darkened as the Wraith Legion emerged, spectral figures of metal and shadow converging upon the Bloody Necrons. " +
               "These ethereal beings, neither fully material nor fully ghostly, were once brethren of the Necrons, now twisted by their own " +
               "obsession with eternal control. The Necron Deathlord watched as the Legion’s shimmering wraith-forms phased in and out, " +
               "guardians of a warped order, determined to prove their supremacy. The Deathlord’s voice echoed across the battlefield: " +
               "“You are but shadows of your former selves. Today, the true Necrons will reclaim what is rightfully ours!”\n\n";
    }
    
    @Override
    public String getFirstBattlePostBattle() {
        return "As the last echoes of phasing energy dissipated, the battlefield lay littered with fragments of the Wraith Legion’s shattered forms. " +
               "Their spectral existence, once a terror to behold, was now reduced to mere glimmers on the blood-soaked ground. " +
               "The Bloody Necrons had triumphed, absorbing the energies of their twisted kin to strengthen their own resurrection. " +
               "With the remnants of the Wraith Legion scattered, the galaxy bore witness to an undeniable truth: no force, however ghostly or eternal, " +
               "could resist the unyielding might of the Bloody Necrons. With each victory, their legend grew, a chilling march echoing through the stars.\n";
    }
    
    @Override
    public String getSecondBattleIntro() {
        return "With their first victory still echoing in their hollow chests, the Bloody Necrons turned their gaze to the " +
               "Void Reavers—a band of ruthless space pirates seeking to plunder the treasures of the Necrons’ ancient technology. " +
               "Unbeknownst to them, they were walking into a trap laid by the relentless Deathlord. As the Reavers laughed, thinking " +
               "themselves clever, the Necrons prepared for war, their cold logic setting the stage for a clash that would drown the stars in blood. " +
               "“Thieves and fools!” the Deathlord growled. “They shall learn the price of their greed!”\n";
    }
    
    @Override
    public String getSecondBattlePostBattle() {
        return "The clash was brutal. The sound of metal grinding against metal resonated across the cosmos as the Necrons decimated " +
               "the Void Reavers, leaving nothing but destruction in their wake. With the last pirate ship exploding in a brilliant flash, " +
               "the Necrons emerged unscathed, their victory a testament to their unyielding might. They reclaimed their ancient technology, " +
               "each piece a trophy of conquest, sending a clear message: the galaxy belongs to the Bloody Necrons, and all who dare " +
               "challenge them will be met with obliteration.\n";
    }
    
    @Override
    public String getThirdBattleIntro() {
        return "As the Necrons continued their ruthless campaign, an unexpected threat arose: the Dark Sovereign, a former ally of the " +
               "Deathlord, now turned enemy, his heart twisted by envy and ambition. The stars quaked at the thought of this betrayal, " +
               "and the Deathlord, seething with rage, prepared his forces for a reckoning. “He was a brother,” he declared, his voice low " +
               "and menacing. “Now he is but a traitor who will taste the bitterness of defeat!” The two forces clashed, both driven by vengeance " +
               "and the desire for supremacy, as the galaxy held its breath.\n";
    }
    
    @Override
    public String getThirdBattlePostBattle() {
        return "The battlefield became a hellscape of fury and fire as the Bloody Necrons and the Dark Sovereign’s forces clashed. In the end, " +
               "only one could stand. The Deathlord, a vision of cold fury, confronted his former ally. With a swift strike, he ended the " +
               "Dark Sovereign’s treachery, scattering his forces to the void. The victorious Necrons surveyed the aftermath, their victory a " +
               "cruel reminder of their dominion over life and death. With every foe vanquished, the Deathlord’s resolve grew stronger; he would reign supreme.\n";
    }

    @Override
    public String getEndingStory() {
        return "As the echoes of battle fade into silence, the Bloody Necrons stand victorious over a battlefield littered " +
                "with the remnants of those who dared to oppose them. The stars above gleam coldly, reflecting the metallic forms " +
                "of your relentless troops, undaunted by the carnage that surrounds them. With each enemy defeated, the strength of " +
                "the Necron legions grows, fueled by ancient hatred and the thirst for reclamation." +
                "\n\nYou, as the Deathlord, feel the weight of your triumph, yet among this glory lies a profound void—a hollow " +
                "realization that each victory brings you no closer to peace. The galaxy, once vibrant with life, now lies quaking " +
                "in your wake, a vast graveyard where silence reigns supreme." +
                "\n\nThey are all gone now, the living whispers of rebellion extinguished. The cries of the fallen fade into nothingness, " +
                "leaving behind only the rhythmic hum of your ancient machinery—a reminder of your eternal purpose. You have forged " +
                "an empire anew, yet the cost is evident in every shadow lurking in the void." +
                "\n\nUnder your command, the Bloody Necrons march forth, their once glorious past now irrevocably stained by the ambition " +
                "of their leader. You realize that the more you conquer, the more you are bound to the galactic silence you sought to impose." +
                "\n\nWith resolute determination, you resolve to purge the stars of any who threaten your unyielding dominion. For as long as " +
                "the galaxy remembers fear, the reign of the Bloody Necrons will echo through the ages: a stark and chilling melody " +
                "reminding all that they are the harbingers of death, and the galaxy is but a playground for their cold, mechanical fury.";
}
    
}
    
    


    