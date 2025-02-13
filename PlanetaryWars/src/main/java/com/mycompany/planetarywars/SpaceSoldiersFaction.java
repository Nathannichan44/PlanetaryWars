package com.mycompany.planetarywars;

import java.util.List;

class SpaceSoldiersFaction extends Faction {
    
    public SpaceSoldiersFaction(String factionType) {
        super(factionType);
        initialize();
    }

    
    @Override
    public void createEnemies() {
        enemies = new Enemy[]{
            new ChaosRenegades(),
            new XenoRaiders(),
            new WarpChampion()
        };
    }

    private void initialize() {
        createEnemies();  
    }

    @Override
    public String getIntro() {
        return "In the dark, endless expanse of the cosmos, " + 
                "where stars glimmer like distant fires, humanity stands on the edge of extinction. "+ 
                "But there are those who fight so that mankind may endure. " +
                "They are not mere mortals but forged by steel, honed by fire, and bound by their unwavering faith. " + 
                "They are the Space Soldiers the Emperors chosen few, defenders of humankind. " +
                "More than human, less than gods, their bodies and minds have been reforged through relentless genetic modification and grueling training, transforming them into warriors of unrivaled might. " +
                "Clad in ancient power armor, wielding weapons forged to vanquish any threat, they march into battle, unyielding in their purpose. "+
                "They are humanity's last line of defense, angels of death to some, saviors to others. As the universe darkens, "+ 
                "they rise, ready to face any foe, be it man, beast, or nightmare spawned from the stars. " +  
                "The Space Soldiers are here... and they will not rest until every enemy of mankind is cast into oblivion.\n";
    }

    @Override
    public List<String> getStorytellingLines() {
        return List.of(
            "The orders were clear. As one of the Imperium’s most elite commanders, you've been deployed to a critical frontier. The defense of your home world rests on the shoulders of your squad, warriors beyond the ordinary, engineered for excellence.",
            "Stepping onto the barren soil of an enemy-infested planet, you survey your troops. These are not just men—they’re living legends, each one outfitted with the finest armor and the deadliest weapons. You know that every life here counts, and that every soldier is irreplaceable.",
            "But with each step, you feel the weight of expectation. These soldiers, though few, are powerful, and you know you must use their skill strategically to overcome the waves of threats that lie ahead.",
            "Reports flood in about enemy battalions scattered through the territory. 'A few against many,' the saying goes, and here it will be proven true. As you give the order to march, each of your troops moves with precision and discipline, their unwavering loyalty and strength forming a shield around the Imperium's ideals.",
            "Your mission is clear: engage, neutralize, and survive. As each of your elite soldiers steps forward, you feel the strength of the Imperium at your back. In your hands lies the power to turn the tide of this battle, one precise strike at a time."
        );
    }

    @Override
    public String getFirstBattleIntro() {
        return  "In a forsaken star system, a dark tide rises." +
                "The Chaos Renegades, traitors twisted by their devotion to the Chaos Gods, mass in uncountable numbers, ready to drown the light of humanity. " +
                "Armed with only crude weapons and blind fanaticism, they swarm like vermin, striking from the shadows and overwhelming with sheer numbers." +
                "But the Space Soldiers stand unfazed. Clad in power armor and wielding weapons of unmatched power, " +
                "they march forth as humanitys final bastion. With each bolter round and each swing of their chainsword, " +
                "they carve a path through the corrupted masses, loyal only to the Emperors command. " +
                "Today, the Space Soldiers will purge this world of Chaos. No traitor will survive their wrath.\n";
    }

    @Override
    public String getFirstBattlePostBattle() {
        return  "The battlefield falls silent. Chaos Renegades lie scattered, their tainted banners trampled underfoot. The Space Soldiers stand victorious, their armor bearing the marks of battle. " +
                "With duty fulfilled, they turn to march onward, leaving a world cleansed of corruption in their wake. Today, Chaos has fallen. Tomorrow, the space soldiers will fight again. \n";
    }

    @Override
    public String getSecondBattleIntro() {
        return  "The galaxy knows no peace. Where Chaos has fallen, a new threat rises from the void. Xeno Raiders. " +
                "Twisted and treacherous, they care nothing for human life and answer only to their own alien instincts. " +
                "Their goal is simple, raid, corrupt, and erase all traces of humanity. " +
                "For the Imperium, there can be no tolerance for the alien. The Space Soldiers know well that to let any xeno live is to invite corruption and chaos. " +
                "Armed and relentless, they march into battle once more, ready to purge every alien threat. " +
                "Today, they face the Xeno Raiders. By the Emperor's will, they will drive these invaders from humanitys stars and no xeno raider shall survive.\n";
    }

    @Override
    public String getSecondBattlePostBattle() {
        return  "The battlefield lies scorched, littered with the remains of xeno raiders who dared to defy humanity. " +
                "The Space Soldiers stand tall amidst the wreckage, their armor stained with the blood of the vanquished. They have once again purged the Imperium’s worlds of the xeno taint. " +
                "With the last xeno threat extinguished, they turn from the ruins, knowing their task is far from over. Today, they rid the stars of yet another menace. Tomorrow, they will hunt again, for humanity’s survival demands nothing less. \n";
    }

    @Override
    public String getThirdBattleIntro() {
        return  "The air crackles with unnatural energy. A chill settles over the battlefield as shadows warp and twist, giving way to creatures that seem to step out of nightmares. These are Warp Champions. " +
                "Without warning, they materialize, wreathed in unholy fire, descending upon the Imperium’s forces like spectral predators. Their claws are blades, their eyes cold voids of malice. " +
                "The Space Soldiers are aware that they are up against something more than physical, warriors that exist solely to hunt, slice, and pleasure in the carnage of battle. " +
                "But the Space Soldiers do not flinch. With weapons raised and wills steeled, they prepare to confront these spectral fiends. " +
                "The Warp Champions may be creatures of another realm, but the Space Soldiers are the Emperor’s chosen, unyielding even in the face of terror itself. " +
                "Today, the clash will be fierce. The Imperium’s finest stand ready to face these horrors and drive them back into the darkness.\n";
    }       

    @Override
    public String getThirdBattlePostBattle() {
        return  "As the last Warp Champion fades, its unnatural form pulled back into the depths of the Immaterium. " +
                "The battlefield is scarred, scorched by warpfire, but the Space Soldiers stand resolute, their armor marked by the ferocity of the battle. " +
                "They have conquered both flesh and nightmare, forcing the darkness itself to retreat. The Warp Champions are vanquished, their threat extinguished. " +
                "The Space Soldiers steel themselves, ready for whatever new horror may await. For today, the Imperium prevails. \n";
    }

    @Override
    public String getEndingStory() {
        return "As the dust settles and the final echoes of battle fade away, the Space Soldiers stand triumphantly " +
               "over a once-infested battlefield. Their power armor, battle-scarred yet shining, reflects the flickering " +
                "lights of destruction and victory. The enemies that once threatened the sanctity of humanity lie vanquished, " +
                "reduced to mere memories—a testament to the relentless courage of those chosen by the Emperor." +
                "\n\nFilled with pride, your troops gather, their weary faces illuminated by the aftermath of their hard-won " +
                "triumph. Yet, behind every victory looms the dark shadows of sacrifice. Each fallen comrade has left a mark " +
                "on your heart, a reminder that this path, though noble, is littered with the echoes of sacrifice—the price of " +
                "humanity's survival." +
                "\n\nAs you stand with your soldiers, you remember the bravery displayed in each encounter, their precision " +
                "and loyalty shining through the chaos of war. Today, you have pushed back the darkness, but you also recognize " +
                "the relentless nature of the fight. The galaxy, filled with chaos and corruption, waits for your next command." +
                "\n\nThe golden light of humanity may flicker, but the Space Soldiers are an eternal flame, destined to rise " +
                "against the shadows, ready to stand in the face of ever-growing threats. Perhaps it is this battle that will " +
                "echo throughout history—the warriors who brought the fight to the darkness, who stood firm for every innocent soul." +
                "\n\nThe soldiers remember their oath, pledging once more to defend humanity at all costs. The fight is far from over, " +
                "but with every victory, every scar, and every sacrifice, you solidify your legacy—the legacy of the Space Soldiers, " +
                "unchained and unwavering, a bulwark against the void.";
}


}