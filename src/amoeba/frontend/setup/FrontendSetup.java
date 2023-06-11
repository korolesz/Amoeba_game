package amoeba.frontend.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontendSetup implements ActionListener {
    private int difficultyLevel = 3;
    private String pcCharacter = "O";
    private int[] pcColor = {0, 0, 225};
    private String playerCharacter = "X";
    private int[] playerColor = {225, 0, 0};
    private boolean isCancel = false;
    private boolean isSetDifficult = false;
    private boolean isSetColor = false;
    private boolean isSetCharacter = false;
    private boolean isPlayer = false;

    JFrame frame = new JFrame();
    JButton difficult = new JButton("Nehézségi szint");
    JButton playerChar = new JButton("Játékos karaktere");
    JButton playerCol = new JButton("Játékos színe");
    JButton pcChar = new JButton("Számítógép karaktere");
    JButton pcCol = new JButton("Számítógép színe");
    JButton cancel = new JButton("Vissza");

    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();


    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setPcCharacter(String pcCharacter) {
        this.pcCharacter = pcCharacter;
    }

    public void setPcColor(int[] pcColor) {
        this.pcColor = pcColor;
    }

    public void setPlayerCharacter(String playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public void setPlayerColor(int[] playerColor) {
        this.playerColor = playerColor;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getPcCharacter() {
        return pcCharacter;
    }

    public int[] getPcColor() {
        return pcColor;
    }

    public String getPlayerCharacter() {
        return playerCharacter;
    }

    public int[] getPlayerColor() {
        return playerColor;
    }

    public FrontendSetup() {
        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textfield, BorderLayout.CENTER);

        buttonPanel.add(difficult);
        buttonPanel.add(pcChar);
        buttonPanel.add(playerChar);
        buttonPanel.add(pcCol);
        buttonPanel.add(playerCol);
        buttonPanel.add(cancel);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        difficult.addActionListener(this);
        playerChar.addActionListener(this);
        pcChar.addActionListener(this);
        playerCol.addActionListener(this);
        pcCol.addActionListener(this);
        cancel.addActionListener(this);
        textfield.setFont(new Font("Arial", Font.BOLD, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("A következő beállítások közül választhatsz!");
        frame.setVisible(false);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancel) {
            isCancel = true;
        } else if (e.getSource() == difficult) {
            isSetDifficult = true;
        } else if (e.getSource() == pcCol) {
            isPlayer = false;
            isSetColor = true;
        } else if (e.getSource() == playerCol) {
            isPlayer = true;
            isSetColor = true;
        } else if (e.getSource() == pcChar) {
            isPlayer = false;
            isSetCharacter = true;
        } else if (e.getSource() == playerChar) {
            isPlayer = true;
            isSetCharacter = true;
        }
    }

    public void setVisible(boolean visible) {
        if (visible) {
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
        }
    }

    public void waitingForTheClick(DifficultSetup difficultSetup, CharacterSetup characterSetup, ColorSetup colorSetup) {

        while (!isCancel) {
            try {
                Thread.sleep(200);
                if (isSetDifficult) {
                    frame.setVisible(false);
                    difficultSetup.setVisible(true);
                    difficultSetup.waitingForTheClick();
                    isSetDifficult = false;
                } else if (isSetCharacter) {
                    frame.setVisible(false);
                    characterSetup.setVisible(true);
                    characterSetup.waitingForTheClick(isPlayer);
                    isSetCharacter = false;
                } else if (isSetColor) {
                    frame.setVisible(false);
                    colorSetup.setVisible(true);
                    colorSetup.waitingForTheClick(isPlayer);
                    isSetColor = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        isCancel = false;
    }
}
