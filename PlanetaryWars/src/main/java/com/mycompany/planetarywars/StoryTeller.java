package com.mycompany.planetarywars;

public class StoryTeller {

    // This method remains mostly unchanged, just providing the faction story
    public String getFactionStory(String factionType) {
        return switch (factionType) {
            case "Imperial Guard" -> "The Imperial Guard stands as the stalwart defenders of the realm...\n";
            case "Space Soldiers" -> "The Space Soldiers, elite warriors of the cosmos, fight to protect their home...\n";
            case "Bloody Necrons" -> "The Bloody Necrons rise from the shadows, seeking vengeance against their foes...\n";
            default -> "Unknown faction.\n";
        };
    }
}
