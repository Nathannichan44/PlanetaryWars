package com.mycompany.planetarywars;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mycompany.planetarywars.Challenge.FindAncientArtifactChallenge;

public class RandomEventManager {

    private final Player player;
    private JTextField inputField;
    private Random random;
    private JPanel artifactPanel;
    private JPanel merchantPanel;
    private Challenge challenge;

    public RandomEventManager(Player player) {
        this.player = player; 
    }

    public void triggerRandomEvent(JFrame frame) {
        random = new Random();
        double eventChance = random.nextDouble();
        
        if (eventChance <= 0.33) { 
            encounterAbandonedRuins(frame);
        } else if (eventChance <= 0.66) { 
            encounterFriendlyMerchant(frame);
        } else if (eventChance <= 0.99) { 
            findAncientArtifact(frame);
        }
    }

    private void encounterAbandonedRuins(JFrame frame) {
        frame.getContentPane().removeAll();
        JTextArea ruinsTextArea = new JTextArea();
        ruinsTextArea.setEditable(false);
        ruinsTextArea.setLineWrap(true);
        ruinsTextArea.setWrapStyleWord(true);
        ruinsTextArea.append("You stumble upon some abandoned ruins. Do you want to enter? (yes/no)\n");

        JPanel ruinsPanel = new JPanel(new BorderLayout());
        ruinsPanel.add(new JScrollPane(ruinsTextArea), BorderLayout.CENTER);

        inputField = new JTextField(20); 
        inputField.addActionListener(e -> handleRuinsDecision(inputField.getText(), frame));
        ruinsPanel.add(inputField, BorderLayout.SOUTH);

        frame.getContentPane().add(ruinsPanel); 
        frame.revalidate();
        frame.repaint();
        inputField.requestFocusInWindow(); 
    }

    private void encounterFriendlyMerchant(JFrame frame) {
        frame.getContentPane().removeAll();
        JTextArea merchantTextArea = new JTextArea();
        merchantTextArea.setEditable(false);
        merchantTextArea.setLineWrap(true);
        merchantTextArea.setWrapStyleWord(true);
    
        merchantTextArea.append("You encounter a mysterious merchant on the road, his cart filled with rare and unusual items.\n");
        merchantTextArea.append("He eyes you with a curious smile and says, \"Ah, a brave soul on a perilous journey!\"\n");
        merchantTextArea.append("You sense there’s a chance to gain his favor, if you play your cards right.\n\n");
        merchantTextArea.append("How will you interact with the merchant?\n");
        merchantTextArea.append("1. Ask about his rarest and most prized items.\n");
        merchantTextArea.append("2. Offer him a small token or trinket as a gift.\n");
        merchantTextArea.append("3. Attempt to barter with him for a favor or healing aid.\n");
    
        merchantPanel = new JPanel(new BorderLayout());
        merchantPanel.add(new JScrollPane(merchantTextArea), BorderLayout.CENTER);
    
        inputField = new JTextField(20);
        inputField.addActionListener(e -> handleMerchantDialogue(inputField.getText(), frame, merchantTextArea));
        merchantPanel.add(inputField, BorderLayout.SOUTH);
    
        frame.getContentPane().add(merchantPanel);
        frame.revalidate();
        frame.repaint();
        inputField.requestFocusInWindow();
    }
    
    private void findAncientArtifact(JFrame frame) {
        frame.getContentPane().removeAll();
        JTextArea artifactTextArea = new JTextArea();
        artifactTextArea.setEditable(false);
        artifactTextArea.setLineWrap(true);
        artifactTextArea.setWrapStyleWord(true);
        
        if (player.hasAcceptedArtifactChallenge()) { 
            challenge = new FindAncientArtifactChallenge();
            artifactTextArea.append("You find an ancient artifact! Would you like to examine it and claim your reward? (yes/no)\n");
        } else {
            artifactTextArea.append("You find an ancient artifact! Would you like to examine it? (yes/no)\n");
        }
    
        artifactPanel = new JPanel(new BorderLayout());
        artifactPanel.add(new JScrollPane(artifactTextArea), BorderLayout.CENTER);
    
        inputField = new JTextField(20);
        inputField.addActionListener(e -> handleArtifactDecision(inputField.getText(), frame, challenge));
        artifactPanel.add(inputField, BorderLayout.SOUTH);
    
        frame.getContentPane().add(artifactPanel); 
        frame.revalidate();
        frame.repaint();
        inputField.requestFocusInWindow(); 
    }
    

    private void handleRuinsDecision(String input, JFrame frame) {
        if ("yes".equalsIgnoreCase(input)) {
            navigateRuins(frame);
        } else if ("no".equalsIgnoreCase(input)) {
            ((GameGUI) frame).showMainMenu(); 
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter 'yes' or 'no'.");
            inputField.setText(""); 
            inputField.requestFocusInWindow(); 
        }
    }

    private void handleMerchantDialogue(String input, JFrame frame, JTextArea merchantTextArea) {
        boolean correctChoice;
        int chance = new Random().nextInt(100);
    
        if ("1".equalsIgnoreCase(input)) {
            merchantTextArea.append("You inquire about his rarest items, hoping to impress him with your curiosity.\n");
            merchantTextArea.append("The merchant chuckles, 'Not often do I meet someone who appreciates true rarity.'\n");
            correctChoice = chance < 50;
    
        } else if ("2".equalsIgnoreCase(input)) {
            merchantTextArea.append("You offer the merchant a small token—a pebble, a feather, anything that might amuse him.\n");
            merchantTextArea.append("He inspects your gift, a glint of amusement in his eye. 'A thoughtful gesture. Few are so generous.'\n");
            correctChoice = chance < 40;
    
        } else if ("3".equalsIgnoreCase(input)) {
            merchantTextArea.append("You attempt to barter with the merchant, suggesting a trade for his favor.\n");
            merchantTextArea.append("He strokes his chin, feigning deep thought. 'A bold offer, but am I to trust you will uphold your end?'\n");
            correctChoice = chance < 60; 
    
        } else {
            merchantTextArea.append("Invalid input! Please enter a number (1, 2, or 3).\n");
            inputField.setText("");
            inputField.requestFocusInWindow();
            return;
        }
    
        if (correctChoice) {
            merchantTextArea.append("The merchant grins, clearly pleased by your approach.\n");
            merchantTextArea.append("As a token of his appreciation, he offers to heal you before you continue on your journey.\n");
            player.heal();  
        } else {
            merchantTextArea.append("The merchant simply nods and returns to his wares, unmoved by your approach.\n");
            merchantTextArea.append("Perhaps he expected more from you—or perhaps he's simply fickle.\n");
        }

        JButton continueButton = new JButton("Press Onward!");
        continueButton.addActionListener(e -> {
            ((GameGUI) frame).showMainMenu();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(continueButton);
        merchantPanel.remove(inputField);
        merchantPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(merchantPanel);
        frame.revalidate();
        frame.repaint();
        inputField.requestFocusInWindow();
    }
    

    private void handleArtifactDecision(String input, JFrame frame, Challenge challenge) {
        if ("yes".equalsIgnoreCase(input)) {
            if (player.hasAcceptedArtifactChallenge()) {

                player.addGold(challenge.getReward());
                JOptionPane.showMessageDialog(frame, "You examined the artifact and gained valuable knowledge, as well as 3000 gold!");
            } else {
                JOptionPane.showMessageDialog(frame, "You examined the artifact and gained valuable knowledge!");
            }
            ((GameGUI) frame).showMainMenu();
        } else if ("no".equalsIgnoreCase(input)) {
            ((GameGUI) frame).showMainMenu(); 
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter 'yes' or 'no'.");
            inputField.setText(""); 
            inputField.requestFocusInWindow(); 
        }
    }


    private void navigateRuins(JFrame frame) {
        JTextArea mazeTextArea = new JTextArea();
        mazeTextArea.setEditable(false);
        mazeTextArea.setLineWrap(true);
        mazeTextArea.setWrapStyleWord(true);
        mazeTextArea.append("You enter the ruins and see two paths: left and right.\n");

        JPanel mazePanel = new JPanel(new BorderLayout());
        mazePanel.add(new JScrollPane(mazeTextArea), BorderLayout.CENTER);

        inputField = new JTextField(20); 
        inputField.addActionListener(e -> handleRuinsChoice(inputField.getText(), mazeTextArea, frame));
        mazePanel.add(inputField, BorderLayout.SOUTH);

        frame.getContentPane().removeAll(); 
        frame.add(mazePanel);
        frame.revalidate();
        frame.repaint();
        inputField.requestFocusInWindow(); 
    }

    private void handleRuinsChoice(String input, JTextArea mazeTextArea, JFrame frame) {
        mazeTextArea.setEditable(false); 
        mazeTextArea.setLineWrap(true);
        mazeTextArea.setWrapStyleWord(true);
        random = new Random();
        JButton continueButton;

        inputField.setText("");

        if ("left".equalsIgnoreCase(input) || "right".equalsIgnoreCase(input)) {

            if (random.nextDouble() <= 0.3) {
                mazeTextArea.append("You encounter a random enemy! Prepare to fight.\n");
                continueButton = new JButton("Fight!");
                continueButton.addActionListener(e -> {
                    ((GameGUI) frame).initiateRandomEncounter();
                });
            } else {
                int goldReward = 5000;
                mazeTextArea.append("You find hidden treasure and gain " + goldReward + " gold!\n");
                player.addGold(goldReward); 

                continueButton = new JButton("Continue");
                continueButton.addActionListener(e -> {
                    ((GameGUI) frame).showMainMenu();
                });
            }

            JPanel mazePanel = new JPanel(new BorderLayout());
            mazePanel.add(new JScrollPane(mazeTextArea), BorderLayout.CENTER);
            mazePanel.add(continueButton, BorderLayout.SOUTH);    
            frame.getContentPane().removeAll(); 
            frame.add(mazePanel);
            frame.revalidate();
            frame.repaint();
            continueButton.requestFocus(); 
        } else {
            mazeTextArea.append("Invalid choice! Please choose left or right.\n");
        }
    }
}
