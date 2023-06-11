package amoeba.frontend;

import amoeba.frontend.setup.FrontendSetup;
import amoeba.program.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontendRules implements ActionListener {
    JFrame frame = new JFrame();
    JButton yes = new JButton("Indítsd a játékot!");
    JButton exit = new JButton("Kilépés");
    JButton setup = new JButton("Beállítások");

    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();
    JLabel textfield1 = new JLabel();
    JLabel textfield2 = new JLabel();
    JLabel textfield3 = new JLabel();
    JLabel textfield4 = new JLabel();


    boolean go = false;
    boolean setups = false;

    public FrontendRules() {
        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JPanel pane = new JPanel();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.add(textfield);
        pane.add(textfield1);
        pane.add(textfield2);
        pane.add(textfield3);
        pane.add(textfield4);
        frame.add(pane, BorderLayout.CENTER);
        buttonPanel.add(yes);
        buttonPanel.add(setup);
        buttonPanel.add(exit);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        yes.addActionListener(this);
        exit.addActionListener(this);
        setup.addActionListener(this);
        textfield.setFont(new Font("Arial", Font.BOLD, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield1.setFont(new Font("Arial", Font.BOLD, 20));
        textfield1.setHorizontalAlignment(JLabel.CENTER);
        textfield2.setFont(new Font("Arial", Font.BOLD, 20));
        textfield2.setHorizontalAlignment(JLabel.CENTER);
        textfield3.setFont(new Font("Arial", Font.BOLD, 20));
        textfield3.setHorizontalAlignment(JLabel.CENTER);
        textfield4.setFont(new Font("Arial", Font.BOLD, 20));
        textfield4.setHorizontalAlignment(JLabel.CENTER);
    }

    public int rules(FrontendSetup frontendSetup) {
        textfield.setText("Kedves Játékos!");
        textfield1.setText(" ");
        textfield2.setText("Üdvözöllek az amőba alkalmazásban! A te karaktered az " + frontendSetup.getPlayerCharacter() + ", a számítógépé az " + frontendSetup.getPcCharacter() + ".");
        textfield3.setText("A pálya " + Field.WIDTH + " egység széles és " + Field.HEIGHT + " egység magas. A játékot az nyeri, akinek először");
        textfield4.setText("sikerül leraknia egymás alá, mellé vagy átlósan 5 ugyanolyan karaktert.");

        while (!(go || setups)) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.setVisible(false);
        if (go){
            go = false;
            return 1;
        } else {
            setups = false;
            return 2;
        }
    }
    public void setVisible(boolean visible){
        if (visible){
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == yes) {
            frame.setVisible(false);
            go = true;
        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == setup) {
            frame.setVisible(false);
            setups = true;
        }
    }
}
