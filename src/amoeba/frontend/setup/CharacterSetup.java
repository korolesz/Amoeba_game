package amoeba.frontend.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterSetup implements ActionListener {
    JFrame frame = new JFrame();
    JButton char1 = new JButton("x");
    JButton char2 = new JButton("o");
    JButton char3 = new JButton("█");
    JButton char4 = new JButton("♪");
    JButton char5 = new JButton("▼");
    JButton char6 = new JButton("▲");
    JButton char7 = new JButton("♥");
    JButton char8 = new JButton("+");
    JButton char9 = new JButton("♦");
    JButton char10 = new JButton("@");
    JButton char11 = new JButton("#");
    JButton[] buttons = {char1, char2, char3, char4, char5, char6, char7, char8, char9, char10, char11};
    String[] characters = {"X", "O", "█", "♪", "▼", "▲", "♥", "+", "♦", "@", "#"};

    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();


    FrontendSetup frontendSetup;
    private boolean cancel = false;
    private boolean isPlayer;


    public CharacterSetup(FrontendSetup frontendSetup) {
        this.frontendSetup = frontendSetup;
        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textfield, BorderLayout.CENTER);
        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);

        textfield.setFont(new Font("Arial", Font.BOLD, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Válassz a következő lehetőségek közül!");
        frame.setVisible(false);
    }


    public void setVisible(boolean visible) {
        if (visible) {
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
        }
    }

    public void waitingForTheClick(boolean isPlayer) {
        this.isPlayer = isPlayer;
        frame.setVisible(true);
        while (!cancel) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cancel = false;
        frontendSetup.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i] && isPlayer) {
                frame.setVisible(false);
                frontendSetup.setPlayerCharacter(characters[i]);
                cancel = true;
            } else if (e.getSource() == buttons[i] && !isPlayer) {
                frame.setVisible(false);
                frontendSetup.setPcCharacter(characters[i]);
                cancel = true;
            }
        }
    }
}
