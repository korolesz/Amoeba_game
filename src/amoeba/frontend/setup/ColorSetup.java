package amoeba.frontend.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorSetup implements ActionListener {
    JFrame frame = new JFrame();
    JButton red = new JButton();
    JButton blue = new JButton();
    JButton green = new JButton();
    JButton yellow = new JButton();
    JButton black = new JButton();
    JButton gray = new JButton();
    JButton purple = new JButton();
    JButton orange = new JButton();
    JButton braun = new JButton();
    JButton pink = new JButton();
    int[][]colors = {
            {255,0,0},
            {0,0,255},
            {0,100,0},
            {212,184,19},
            {0,0,0},
            {90,90,90},
            {82,56,150},
            {250,100,0},
            {92,64,51},
            {244,42,244},
    };
    JButton[] buttons = {red, blue, green, yellow, black, gray, purple, orange, braun, pink};
    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();


    FrontendSetup frontendSetup;
    boolean cancel = false;
    boolean isPlayer;


    public ColorSetup(FrontendSetup frontendSetup) {
        this.frontendSetup = frontendSetup;
        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textfield, BorderLayout.CENTER);

        for (int i = 0; i < buttons.length;i++){
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(new Color(colors[i][0],colors[i][1],colors[i][2]));
            buttons[i].setText("  ");
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

        for (int i = 0; i < buttons.length;i++){
            if (e.getSource()== buttons[i] && isPlayer){
                frame.setVisible(false);
                frontendSetup.setPlayerColor(colors[i]);
                cancel = true;
            } else if (e.getSource()== buttons[i] && !isPlayer) {
                frame.setVisible(false);
                frontendSetup.setPcColor(colors[i]);
                cancel = true;
            }
        }

    }
}
