package amoeba.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrontendAskPlayer implements ActionListener {
    JFrame frame = new JFrame();
    JButton yes = new JButton("Igen");
    JButton no = new JButton("Nem");
    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();

    boolean yesAnswer = false;
    boolean noAnswer = false;

    public FrontendAskPlayer() {


        frame.setSize(850, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        BorderLayout borderLayout = new BorderLayout();
        FlowLayout flowLayout = new FlowLayout();
        buttonPanel.setLayout(flowLayout);
        frame.setLayout(borderLayout);
        frame.add(textfield, BorderLayout.CENTER);
        buttonPanel.add(yes);
        buttonPanel.add(no);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        yes.addActionListener(this);
        no.addActionListener(this);
        textfield.setFont(new Font("Arial", Font.BOLD, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);


    }

    public boolean yesOrNo(String Instruction) {
        textfield.setText(Instruction);
        while (!yesAnswer && !noAnswer) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.setVisible(false);
        if (yesAnswer) {

            return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {
            yesAnswer = true;
        } else if (e.getSource() == no) {
            noAnswer = true;
        }
    }
}

