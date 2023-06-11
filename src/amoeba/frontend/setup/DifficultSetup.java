package amoeba.frontend.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultSetup implements ActionListener {
    JFrame frame = new JFrame();
    JButton beginner = new JButton("Kezdő");
    JButton advanced = new JButton("Haladó");
    JButton prof = new JButton("Profi");

    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();


    FrontendSetup frontendSetup;
    boolean cancel = false;


    public DifficultSetup(FrontendSetup frontendSetup){
        this.frontendSetup = frontendSetup;
        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textfield, BorderLayout.CENTER);
        buttonPanel.add(beginner);
        buttonPanel.add(advanced);
        buttonPanel.add(prof);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        beginner.addActionListener(this);
        advanced.addActionListener(this);
        prof.addActionListener(this);
        textfield.setFont(new Font("Arial", Font.BOLD, 18));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Válassz a következő lehetőségek közül!");
        frame.setVisible(false);
    }



    public void setVisible(boolean visible){
        if (visible){
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
        }
    }

    public void waitingForTheClick() {
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

        if (e.getSource() == beginner) {
            frame.setVisible(false);
            frontendSetup.setDifficultyLevel(1);
            cancel = true;
        } else if (e.getSource() == advanced) {
            frame.setVisible(false);
            frontendSetup.setDifficultyLevel(2);
            cancel = true;
        } else if (e.getSource() == prof) {
            frame.setVisible(false);
            frontendSetup.setDifficultyLevel(3);
            cancel = true;
        }
    }
}
