package com.mycompany.planetarywars;

public class Challenge {
    private final String description;
    private final int reward;
    private boolean isCompleted;
    
    public Challenge(String description, int reward) {
        this.description = description;
        this.reward = reward;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeChallenge() {
        isCompleted = true;
    }

    public boolean checkCompletion(Player player, boolean skillsUsed) {
        return switch (description) {
            case "Above 50% Health" -> player.getTroops() > player.getMaxTroops() * 0.5;
            case "No Skills Used" -> !skillsUsed;
            default -> false;
        };
    }


    public static class ChallengeResult {
        private final boolean completed;
        private final int reward;

        public ChallengeResult(boolean completed, int reward) {
            this.completed = completed;
            this.reward = reward;
        }

        public boolean isCompleted() {
            return completed;
        }

        public int getReward() {
            return reward;
        }
    }

     // "Above 50% Health" Challenge
    public static class AboveFiftyPercentHealthChallenge extends Challenge {
        public AboveFiftyPercentHealthChallenge() {
            super("Above 50% Health", 2500);
        }

        @Override
        public boolean checkCompletion(Player player, boolean skillsUsed) {
            return player.getTroops() > player.getMaxTroops() * 0.5;
        }
    }

    // "No Skills Used" Challenge
    public static class NoSkillsUsedChallenge extends Challenge {
        public NoSkillsUsedChallenge() {
            super("No Skills Used", 2000);
        }

        @Override
        public boolean checkCompletion(Player player, boolean skillsUsed) {
            return !skillsUsed;
        }
    }

    // "Find the Ancient Artifact" Challenge
    public static class FindAncientArtifactChallenge extends Challenge {
        public FindAncientArtifactChallenge() {
            super("Find the Ancient Artifact", 7500);
        }
    }
}

