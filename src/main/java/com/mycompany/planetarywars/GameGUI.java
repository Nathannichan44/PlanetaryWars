package com.mycompany.planetarywars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.mycompany.planetarywars.GameGUI.GameUtils;

public class GameGUI extends JFrame {
    private Player player;
    private Faction faction;
    private JTextArea textArea;
    private JTextField inputField;
    private JTextArea enemyInfoArea;
    private JTextArea playerInfoArea;
    private final JTextArea storyTextArea;
    private RandomEventManager randomEventManager;
    private Challenge currentChallenge;

    private final List<Enemy> finalBosses = new ArrayList<>(Arrays.asList(
        new Bloodreaver(),
        new Aetherbound(),
        new Plaguebringer(),
        new Indulgence()
    ));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI game = new GameGUI();
            game.setVisible(true);
        });
    }

    public GameGUI() {          //start
        setTitle("Text-Based RPG Game");
        setSize(625, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel storyPanel = new JPanel();
        storyTextArea = new JTextArea();
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);

        storyPanel.setLayout(new BorderLayout());
        storyPanel.add(new JScrollPane(storyTextArea), BorderLayout.CENTER);
    
        add(storyPanel, BorderLayout.CENTER);
    
        showStartMenu();
    }

    private void showStartMenu() {      //faction selection
        
        getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("Select your allegiance in the galaxy's grand conflict:\n");
        textArea.append("1: Imperial Guard\n");
        textArea.append("2: Space Soldiers\n");
        textArea.append("3: Bloody Necrons\n");

        
        textArea.setPreferredSize(new Dimension(500, 300));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0;  
        gbc.fill = GridBagConstraints.BOTH;
    mainPanel.add(new JScrollPane(textArea), gbc);

    JPanel inputPanel = new JPanel();
    inputField = new JTextField(20);
    inputField.addActionListener(e -> handleFactionSelection(inputField.getText()));
    inputPanel.add(inputField);

    gbc.gridy = 1;
    gbc.weighty = 0; 
    gbc.fill = GridBagConstraints.HORIZONTAL; 
    mainPanel.add(inputPanel, gbc);

    add(mainPanel);

    revalidate();
    repaint();
    inputField.requestFocusInWindow();
    }

    private void handleFactionSelection(String input) {  
    switch (input) {
        case "1" -> {
            faction = new ImperialGuardFaction("Imperial Guard");
            player = new ImperialGuard("Nathan");
            randomEventManager = new RandomEventManager(player);
            displayFactionStory(faction.getIntro(), () -> {
                displayStorySection(faction.getStorytellingLines(), this::showMainMenu); 
            });
        }
        case "2" -> {
            faction = new SpaceSoldiersFaction("Space Soldiers");
            player = new SpaceSoldier("Nathan");
            randomEventManager = new RandomEventManager(player);
            displayFactionStory(faction.getIntro(), () -> {
                displayStorySection(faction.getStorytellingLines(), this::showMainMenu); 
            });
        }
        case "3" -> {
            faction = new BloodyNecronsFaction("Bloody Necrons");
            player = new BloodyNecron("Nathan");
            randomEventManager = new RandomEventManager(player);
            displayFactionStory(faction.getIntro(), () -> {
                displayStorySection(faction.getStorytellingLines(), this::showMainMenu); 
            });
        }
        default -> textArea.append("Invalid faction. Please try again.\n");
    }
    inputField.setText("");
}

    private void displayFactionStory(String story, Runnable onStoryComplete) {
        getContentPane().removeAll(); 
    
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setText(story); 
    
        JPanel storyPanel = new JPanel(new BorderLayout());
        storyPanel.add(new JScrollPane(storyTextArea), BorderLayout.CENTER);
    
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> onStoryComplete.run());
        storyPanel.add(continueButton, BorderLayout.SOUTH);
    
        add(storyPanel);
        revalidate();
        repaint();
    }
    
    private void displayStorySection(List<String> storyLines, Runnable onStoryComplete) {
        getContentPane().removeAll();
        
        storyTextArea.setText("");
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        
        JPanel storyPanel = new JPanel(new BorderLayout());
        storyPanel.add(new JScrollPane(storyTextArea), BorderLayout.CENTER);
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            int currentLineIndex = 1; 
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentLineIndex < storyLines.size()) {
                    storyTextArea.setText(storyLines.get(currentLineIndex));
                    currentLineIndex++;
                } else {
                    onStoryComplete.run();
                }
            }
        });
        
        if (!storyLines.isEmpty()) {
            storyTextArea.setText(storyLines.get(0));
        } else {
            onStoryComplete.run();
        }
    
        storyPanel.add(nextButton, BorderLayout.SOUTH);
        add(storyPanel);
        revalidate();
        repaint();
    }
    
    public void showMainMenu() {        //main menu 
        getContentPane().removeAll();
    
        JPanel mainPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
    
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("Welcome, " + player.getName() + "!\n");
        textArea.append("You have joined the " + faction.getClass().getSimpleName() + ".\n");
        textArea.append("Choose your strategy for this campaign:\n");
        textArea.append("1: Engage in Battle\n");
        textArea.append("2: Tend to Your Wounds\n");
        textArea.append("3: Supplies Depot\n");
        textArea.append("4: Secure Tactical Data\n");
        textArea.append("5: Retrieve Tactical Data\n");
        textArea.append("6: Embrace a Trial of Valor\n"); 
        textArea.append("7: Exit\n");
        
        textArea.setPreferredSize(new Dimension(500, 300));
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        mainPanel.add(new JScrollPane(textArea), gbc);
    
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        inputField.addActionListener(e -> handleMainMenuInput(inputField.getText()));
        inputPanel.add(inputField);
        
        gbc.gridy = 1;
        gbc.weighty = 0; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(inputPanel, gbc);
    
        add(mainPanel);
    
        revalidate();
        repaint();
        inputField.requestFocusInWindow();
    }

    private void handleMainMenuInput(String input) {
        switch (input) {
            case "1" -> chooseFightType();
            case "2" -> {
                if (player.getGold()>=2500) {
                    player.heal();
                    textArea.append(player.getName() + " has been healed!\n");
                } else {
                    textArea.append("Not enough gold to heal!\n");
                }
            }
            case "3" -> showShopMenu(); 
            case "4" -> {
                GameUtils.saveGame(player, faction.getFactionType(), faction, faction.getEnemies(),"game_save.txt");
                textArea.append("Game saved.\n");
            }
            case "5" -> {
                player = GameUtils.loadGame("game_save.txt");
                faction = GameUtils.loadFaction("game_save.txt");
                showMainMenu();
                textArea.append("Tactical data retrieved.\n");
            }   
            case "6" -> chooseChallenge();
            case "7" -> System.exit(0);
            default -> textArea.append("Invalid option. Please try again.\n");
        }
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    private void chooseFightType() {
        textArea.append("Choose fight type:\n");
        textArea.append("1: Main Story Fight\n");
        textArea.append("2: Random Encounter Fight\n");
        textArea.append("3: Back to Main Menu\n");
    

        for (ActionListener listener : inputField.getActionListeners()) {
            inputField.removeActionListener(listener);
        }

        inputField.addActionListener(e -> handleFightTypeSelection(inputField.getText()));
        inputField.requestFocusInWindow(); 
    }
    
    private void handleFightTypeSelection(String input) {
        switch (input) {
            case "1" -> initiateMainStoryCombat(); // main story fight
            case "2" -> initiateRandomEncounter(); // random encounter
            case "3" -> showMainMenu(); // return
            default -> textArea.append("Invalid option. Please try again.\n");
        }
        inputField.setText(""); 
        inputField.requestFocusInWindow();
    }


    private void chooseChallenge() {
        textArea.append("Select a challenge:\n");
        textArea.append("1: Finish the battle with more than 50% health.\n");
        textArea.append("2: Win without using skills.\n");
        textArea.append("3: Find the Ancient Artifact\n"); 
        textArea.append("3: Back to Main Menu\n"); 
    

        for (ActionListener listener : inputField.getActionListeners()) {
            inputField.removeActionListener(listener);
        }

        inputField.addActionListener(e -> handleChallengeSelection(inputField.getText()));
        inputField.requestFocusInWindow(); 
    }
    
    private void handleChallengeSelection(String input) {
        switch (input) {
            case "1" -> currentChallenge = new Challenge.AboveFiftyPercentHealthChallenge();
            case "2" -> currentChallenge = new Challenge.NoSkillsUsedChallenge();
            case "3" -> currentChallenge = new Challenge.FindAncientArtifactChallenge();
            case "4" -> showMainMenu();
            default -> textArea.append("Invalid challenge. Please try again.\n");
        }
        inputField.setText("");
    }
    

    public void initiateRandomEncounter() {
        Enemy[] randomEnemies = {
            new MalgrimTheFoul(),
            new ThraxTheRavager(),
            new VarkonTheUnholy(),
            new PlagueCultists(),
            new ChaosZealots(),
            new PossessedEnforcer()
        };
    
        int randomIndex = (int) (Math.random() * randomEnemies.length);
        Enemy randomEnemy = randomEnemies[randomIndex];
    
        showCombatMenu(randomEnemy);
    }

    public void initiateMainStoryCombat() {
        if (faction.getCurrentEnemyIndex() >= 3) {
            initiateFinalBossEncounter();
            return;
        }
    
        if (faction.getCurrentEnemyIndex() < faction.getEnemies().length) {
            Enemy enemy = faction.getEnemies()[faction.getCurrentEnemyIndex()];
    
            String battleIntro;
            battleIntro = switch (faction.getCurrentEnemyIndex()) {
                case 0 -> faction.getFirstBattleIntro();
                case 1 -> faction.getSecondBattleIntro();
                case 2 -> faction.getThirdBattleIntro();
                default -> faction.getFirstBattleIntro();
            };
    
            displayFactionStory(battleIntro, () -> showCombatMenu(enemy));
        }
    }

    private void initiateFinalBossEncounter() {
        if (finalBosses.isEmpty()) {
            displayEndingStory();
            return;
        }
    
        Enemy finalBoss = selectRandomBoss();
        
        switch (finalBoss) {
            case Bloodreaver bloodreaver -> displayFactionStory(bloodreaver.getIntro(), () -> showCombatMenu(finalBoss));
            case Aetherbound aetherbound -> displayFactionStory(aetherbound.getIntro(), () -> showCombatMenu(finalBoss));
            case Plaguebringer plaguebringer -> displayFactionStory(plaguebringer.getIntro(), () -> showCombatMenu(finalBoss));
            case Indulgence indulgence -> displayFactionStory(indulgence.getIntro(), () -> showCombatMenu(finalBoss));
            default -> {
            }
        }
    }
    

    private Enemy selectRandomBoss() {
        int randomIndex = (int) (Math.random() * finalBosses.size());
        return finalBosses.remove(randomIndex);
    }

    private void showShopMenu() {
        getContentPane().removeAll();
    
        JPanel shopPanel = new JPanel(new BorderLayout());
        JTextArea shopTextArea = new JTextArea();
        shopTextArea.setEditable(false);
        
        Item[] items = {
            new Item("Fortifying Bulwark", 5000, Item.EffectType.HEALTH_BOOST, 30),
            new Item("Serrated Blades", 10000, Item.EffectType.DAMAGE_OVER_TIME, 10),
            new Item("Midas' Pendant", 7500, Item.EffectType.GOLD_BOOST, 2500)
        };
    
        for (Item item : items) {
            if (player.hasGoldBoostItem()) {
                item.setOwned(true);
            }
        }
    
        shopTextArea.append("Welcome to the Armory!\n");
        shopTextArea.append("You have " + player.getGold() + " credits.\n");
        shopTextArea.append("Available items:\n");
    
        for (int i = 0; i < items.length; i++) {
            String ownedText = items[i].isOwned() ? " (Owned)" : "";
            shopTextArea.append((i + 1) + ": " + items[i].getName() + ownedText + " - Cost: " + items[i].getCost() + " credits\n");
        }
        shopTextArea.append("Enter the item number to purchase, or '0' to return to the Command Center:\n");
        
        shopPanel.add(new JScrollPane(shopTextArea), BorderLayout.CENTER);
    
        JTextField shopInputField = new JTextField(20);
        shopInputField.addActionListener(e -> handleShopInput(shopInputField.getText(), items, shopTextArea, shopInputField));
        shopPanel.add(shopInputField, BorderLayout.SOUTH);
    
        add(shopPanel);
        revalidate();
        repaint();
        shopInputField.requestFocusInWindow();
    }
    
    private void handleShopInput(String input, Item[] items, JTextArea shopTextArea, JTextField shopInputField) {
        int itemIndex;
        try {
            itemIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            shopTextArea.append("Invalid input. Please enter a valid item number.\n");
            shopInputField.setText("");
            return;
        }
    
        if (itemIndex == -1) { 
            showMainMenu();
            return;
        }
    
        if (itemIndex < 0 || itemIndex >= items.length) {
            shopTextArea.append("Invalid item number. Please try again.\n");
            shopInputField.setText(""); 
            return;
        }
    
        Item selectedItem = items[itemIndex];
    
        if (selectedItem.isOwned()) { // check if player owns the item
            shopTextArea.append("You already own " + selectedItem.getName() + ".\n");
        } else {
            if (player.getGold() >= selectedItem.getCost()) {
                player.addGold(-selectedItem.getCost());
                player.applyEffect(selectedItem);
                selectedItem.setOwned(true); // mark the item as owned
                shopTextArea.append("You bought " + selectedItem.getName() + " for " + selectedItem.getCost() + " credits!\n");
            } else {
                shopTextArea.append("Not enough credits to buy " + selectedItem.getName() + ".\n");
            }
        }
    
        shopTextArea.append("You have " + player.getGold() + " credits left.\n"); 
        shopInputField.setText(""); 
        shopInputField.requestFocusInWindow(); 
    }

public void showCombatMenu(Enemy enemy) {      //combat menu   
    getContentPane().removeAll();

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel playerPanel = new JPanel(new BorderLayout());
    playerInfoArea = new JTextArea();
    playerInfoArea.setEditable(false);
    playerInfoArea.append("You:\n");
    playerInfoArea.append("HP: " + player.getTroops() + "\n");
    playerPanel.add(playerInfoArea, BorderLayout.CENTER);

    JPanel enemyPanel = new JPanel(new BorderLayout());
    enemyInfoArea = new JTextArea();
    enemyInfoArea.setEditable(false);
    enemyInfoArea.append("Enemy: " + enemy.getName() + "\n");
    enemyInfoArea.append("HP: " + enemy.getTroops() + "\n");
    enemyPanel.add(enemyInfoArea, BorderLayout.CENTER);

    JPanel timelinePanel = new JPanel(new BorderLayout());
    JTextArea timelineArea = new JTextArea();
    timelineArea.setEditable(false);
    timelineArea.setLineWrap(true);
    timelineArea.setWrapStyleWord(true);
    timelineArea.append("Fight Timeline:\n");
    JScrollPane scrollPane = new JScrollPane(timelineArea);
    scrollPane.setPreferredSize(new Dimension(200, 150));  
    timelinePanel.add(scrollPane, BorderLayout.CENTER);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);  
    mainPanel.add(playerPanel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;  
    mainPanel.add(timelinePanel, gbc);

    gbc.gridx = 2;
    gbc.gridy = 0;  
    mainPanel.add(enemyPanel, gbc);


    JPanel actionOptionsPanel = new JPanel(new BorderLayout());
    JTextArea actionOptionsArea = new JTextArea();
    actionOptionsArea.setEditable(false);
    actionOptionsArea.append("Select your course of action in this battle:\n");
    actionOptionsArea.append("1: Launch Assault\n");


    List<Skill> skills = player.getSkills();
    for (int i = 0; i < skills.size(); i++) {
        actionOptionsArea.append((i + 2) + ": " + skills.get(i).getName() + "\n"); // skills start from index 2
    }
    actionOptionsArea.append((skills.size() + 2) + ": Retreat\n");

    actionOptionsPanel.add(actionOptionsArea, BorderLayout.CENTER);

    gbc.gridx = 0;
    gbc.gridy = 1;  
    gbc.gridwidth = 3;  
    gbc.insets = new Insets(10, 10, 10, 10);  
    mainPanel.add(actionOptionsPanel, gbc);

    inputField = new JTextField(20);
    inputField.addActionListener(e -> handleCombatInput(inputField.getText(), enemy, timelineArea, skills));

    gbc.gridy = 2;  
    mainPanel.add(inputField, gbc);

    add(mainPanel);

    revalidate();
    repaint();
    inputField.requestFocusInWindow();
}
    
public void handleCombatInput(String input, Enemy enemy, JTextArea timelineArea, List<Skill> skills) {        
    updateEnemyInfoDisplay(enemy);
    updatePlayerInfoDisplay();
    boolean skillsUsed = false; 

    try {
        int actionIndex = Integer.parseInt(input);
        
        if (actionIndex == 1) { // Attack
            int normalDamage = player.attack();
            enemy.takeDamage(normalDamage);
            timelineArea.append(player.getName() + " performs a normal attack and deals " + normalDamage + " damage to " + enemy.getName() + ".\n");


            if (player.hasDamageOverTimeItem()) {

                int additionalDamage = player.getDamageOverTime();
                enemy.takeDamage(additionalDamage);
                timelineArea.append(player.getName() + " deals an additional " + additionalDamage + " damage to " + enemy.getName() + " from Damage Over Time effect!\n");
            }
            updateEnemyInfoDisplay(enemy);
            updatePlayerInfoDisplay();

        } else if (actionIndex >= 2 && actionIndex < skills.size() + 2) { // Skills
            skillsUsed = true; 
            int skillIndex = actionIndex - 2; 
            if (player.canUseSkill(skillIndex)) {
                int skillDamage = player.attackWithSkill(skillIndex);
                enemy.takeDamage(skillDamage);
                timelineArea.append(player.getName() + " uses " + skills.get(skillIndex).getName() + " and deals " + skillDamage + " damage to " + enemy.getName() + ".\n");
            } else {
                timelineArea.append(skills.get(skillIndex).getName() + " is on cooldown!\n");
            }
            updateEnemyInfoDisplay(enemy);
            updatePlayerInfoDisplay();
        } else if (actionIndex == skills.size() + 2) { // Retreat
            timelineArea.append(player.getName() + " retreats!\n");
            showMainMenu();  
            return;
        } else {
            timelineArea.append("Invalid action. Please try again.\n");
            return;
        }
    } catch (NumberFormatException e) {
        timelineArea.append("Invalid input. Please enter a valid action number.\n");
        return;
    }

    player.updateCooldowns();

    
    if(enemy.isAlive() && player.isAlive()){
        int enemyDamage = enemy.makeAttack();
        player.takeDamage(enemyDamage);
        timelineArea.append(enemy.getName() + " deals " + enemyDamage + " damage to " + player.getName() + ".\n");
    
        // additional damage if enemy is Plaguebringer
        if (enemy instanceof Plaguebringer) {
            Random random = new Random();

            int plagueDamage = random.nextInt(9) + 2; 
            player.takeDamage(plagueDamage);
            timelineArea.append(enemy.getName() + " inflicts " + plagueDamage + " damage over time effect on " + player.getName() + ".\n");
        }

        if (enemy instanceof Indulgence) {
            timelineArea.append(enemy.getName() + " heals for " + ((int)enemy.attack()*0.2) + " from their attack \n");
        }
    }

    if (!player.isAlive()) {
        displayDefeatScreen();  
    }

    if (!enemy.isAlive()) {
        if(enemy.isMainStory()){
            switch (faction.getCurrentEnemyIndex()) {
                case 0 -> player.addGold(5000);
                case 1 -> player.addGold(6000);
                case 2 -> player.addGold(7500);
                default -> player.addGold(5000);
            }
            faction.incrementEnemy();
        }else {
            player.addGold(6000);
        }
        if (player.getGoldBoost() > 0) {
            player.addGold(player.getGoldBoost());
        }


        // check challenge is completed
        if (currentChallenge != null && currentChallenge.checkCompletion(player, skillsUsed)) {
            player.addGold(currentChallenge.getReward());
            timelineArea.append("Challenge Completed! You earned an additional " + currentChallenge.getReward() + " gold!\n");
            currentChallenge.completeChallenge();
        }

        if (enemy.isMainStory()) {
            switch (faction.getCurrentEnemyIndex() - 1) {
                case 0 -> displayFactionStory(faction.getFirstBattlePostBattle(), this::displayWinScreen);
                case 1 -> displayFactionStory(faction.getSecondBattlePostBattle(), this::displayWinScreen);
                case 2 -> displayFactionStory(faction.getThirdBattlePostBattle(), this::displayWinScreen);
                default -> displayFactionStory(enemy.getPostBattleStory(), this::displayWinScreen);
            }
        } else {
            displayWinScreen();
        }
    }

    inputField.setText("");  
    inputField.requestFocusInWindow();  
}


private void updatePlayerInfoDisplay() {        //player info display update
     
    playerInfoArea.setText("You:\n");
    playerInfoArea.append("HP: " + player.getTroops() + "\n");  
}

private void updateEnemyInfoDisplay(Enemy enemy) {      //enemy info display update
    enemyInfoArea.setText("Enemy: " + enemy.getName() + "\n");
    enemyInfoArea.append("HP: " + enemy.getTroops() + "\n");  
}

private void displayWinScreen() {       // victory screen
    getContentPane().removeAll();
    JPanel winPanel = new JPanel(new BorderLayout());
    JTextArea winText = new JTextArea();
    Random random = new Random();

    winText.setEditable(false);
    winPanel.add(new JScrollPane(winText), BorderLayout.CENTER);

        int goldEarned;
        switch (faction.getCurrentEnemyIndex() - 1) {
            case 0 -> goldEarned = 5000;
            case 1 -> goldEarned = 6000;
            case 2 -> goldEarned = 7500;
            default ->goldEarned = 5000;
        } 

        winText.append("You have secured " + goldEarned + " gold for vanquishing the foe!\n");
    

    if (player.getGoldBoost() > 0) {
        winText.append("\nYou have earned an additional [" + player.getGoldBoost() + "] gold from the gold boost item!\n");
    }

    // check challenge was completed
    if (currentChallenge != null && currentChallenge.isCompleted()) {
        winText.append("\nTrial completed! You earned an additional " + currentChallenge.getReward() + " gold!\n");
    }
    

    JButton continueButton = new JButton("Press Onward!");
    continueButton.addActionListener(e -> {
        if(random.nextInt(100)>80){
        showMainMenu();
        }else{
            randomEventManager.triggerRandomEvent(this);
        }
    });
    

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(continueButton);
    winPanel.add(buttonPanel, BorderLayout.SOUTH);
    add(winPanel);
    revalidate();
    repaint();

}

private void displayDefeatScreen() {    //defeat screen
    getContentPane().removeAll();
    JPanel defeatPanel = new JPanel(new BorderLayout());

    JTextArea defeatText = new JTextArea("You have been defeated!\n");
    defeatText.setEditable(false);
    defeatPanel.add(new JScrollPane(defeatText), BorderLayout.CENTER);

    JButton menuButton = new JButton("Go to Menu");
    menuButton.addActionListener(e -> showMainMenu());

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(menuButton);
    defeatPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(defeatPanel);
    revalidate();
    repaint();
}

private void displayEndingStory() {
    String endingStory = faction.getEndingStory();
    displayFactionStory(endingStory, this::showMainMenu);
}

    
public static class GameUtils {

    static void saveGame(Player player, String factionName, Faction faction, Enemy[] currentEnemy, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Save player data
            writer.write(player.getName() + "\n"); // Player name
            writer.write(factionName + "\n"); // Faction name
            writer.write(player.getGold() + "\n"); // Player gold
            writer.write(player.getTroops() + "\n"); // Player health (or troops)
            writer.write(player.hasDamageOverTimeItem() ? "1\n" : "0\n"); // Has damage-over-time item
            writer.write(player.getGoldBoost() + "\n"); // Player gold boost
            writer.write(faction.getCurrentEnemyIndex() + "\n"); // Current enemy index
    
            System.out.println("Tactical data stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
        }
    }

    static Player loadGame(String filePath) {
        Player player = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String name = reader.readLine(); // Player name
            String factionName = reader.readLine(); // Faction name
            int gold = Integer.parseInt(reader.readLine()); // Player gold
            int troops = Integer.parseInt(reader.readLine()); // Player health (or troops)
            boolean hasDamageOverTimeItem = reader.readLine().equals("1"); // Has damage-over-time item
            int goldBoost = Integer.parseInt(reader.readLine()); // Player gold boost
            reader.readLine(); // null read
    
            switch(factionName) {
                case "Bloody Necrons" -> {
                    player = new BloodyNecron(name);
                }
                case "Imperial Guard" -> {
                    player = new ImperialGuard(name);
                }
                case "Space Soldiers" -> {
                    player = new SpaceSoldier(name);
                }
                default -> throw new IllegalArgumentException("Unknown faction: " + factionName);
            }
            
            if (player != null) {
                player.addGold(gold);
                player.setTroops(troops); 
                

                if (hasDamageOverTimeItem) {
                    player.setHasDamageOverTimeItem(true);
                }
                player.setGoldBoost(goldBoost); 
            }
            return player;
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while loading the game.");
        }
        return null;
    }

    static Faction loadFaction(String filePath) {
        Faction faction = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        reader.readLine(); // null read
        String factionName = reader.readLine(); // Faction name
        reader.readLine(); // null read
        reader.readLine(); // null read
        reader.readLine(); // null read
        reader.readLine(); // null read
        int enemyIndex = Integer.parseInt(reader.readLine()); // Current enemy index

    switch(factionName) {
        case "Bloody Necrons" -> {
            faction = new BloodyNecronsFaction("Bloody Necrons");
            faction.setCurrentEnemyIndex(enemyIndex);
        }
        case "Imperial Guard" -> {
            faction = new ImperialGuardFaction("Imperial Guard");
            faction.setCurrentEnemyIndex(enemyIndex);
        }
        case "Space Soldiers" -> {
            faction = new SpaceSoldiersFaction("Space Soldiers");
            faction.setCurrentEnemyIndex(enemyIndex);
        }
        default -> throw new IllegalArgumentException("Unknown faction: " + factionName);
    }
    return faction;
    } catch (IOException | NumberFormatException e) {
    System.out.println("An error occurred while loading the game.");
    }
    return null;
    }
}

}

