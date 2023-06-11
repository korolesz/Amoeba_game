package amoeba.frontend;


import amoeba.frontend.setup.FrontendSetup;
import amoeba.program.Coordinates;
import amoeba.program.Field;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrontendLevelImpl implements FrontendLevel, ActionListener {

    private String pcChar;
    private String playerChar;
    private int[] playerColor;
    private int[] pcColor;
    private int levelWidth;
    private int levelHeight;
    private String space;
    private Field field;
    private int NumOfstep;
    private int round;


    boolean playerIsNext;


    JFrame frame = new JFrame();

    JPanel titlepanel = new JPanel();
    JPanel WestButtonsPanel = new JPanel();
    JPanel buttonpanel = new JPanel();
    JLabel textfield = new JLabel();

    JButton[][] buttons;

    String[][] level;
    private static FrontendLevelImpl INSTANCE;


    private FrontendLevelImpl(int levelWidth, int levelHeight, String space, Field field, FrontendSetup frontendSetup) {

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.space = space;
        this.NumOfstep = -1;
        this.round = 0;
        this.field = field;
        setSetups(frontendSetup);


        this.buttons = new JButton[levelHeight][levelWidth];

        this.level = new String[levelHeight][levelWidth];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        textfield.setForeground(new Color(25, 255, 25));
        textfield.setFont(new Font("Arial", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Amőba - játék");
        textfield.setOpaque(true);


        titlepanel.setLayout(new BorderLayout());
        titlepanel.setBounds(10, 10, 1000, 100);


        buttonpanel.setLayout(new GridLayout(levelHeight, levelWidth));
        buttonpanel.setBounds(400, 0, 1500, 700);
        buttonpanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                buttons[i][j] = new JButton();
                buttonpanel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, setFont(false)));
                //számítógép színe
                buttons[i][j].setForeground(new Color(pcColor[0], pcColor[1], pcColor[2]));
                buttons[i][j].setFocusable(false);


            }
        }

        titlepanel.add(textfield);

        frame.add(titlepanel, BorderLayout.NORTH);
        frame.add(WestButtonsPanel, BorderLayout.WEST);
        frame.add(buttonpanel, BorderLayout.CENTER);
    }

    private void setSetups(FrontendSetup frontendSetup) {
        this.pcChar = frontendSetup.getPcCharacter();
        this.playerChar = frontendSetup.getPlayerCharacter();
        this.playerColor = frontendSetup.getPlayerColor();
        this.pcColor = frontendSetup.getPcColor();

        if (frontendSetup.getDifficultyLevel() == 1) {
            textfield.setBackground(new Color(82, 56, 150));
        } else if (frontendSetup.getDifficultyLevel() == 2) {
            textfield.setBackground(new Color(100, 10, 100));
        } else if (frontendSetup.getDifficultyLevel() == 3) {
            textfield.setBackground(new Color(25, 50, 25));
        }
    }

    @Override
    public void setBasicState(FrontendSetup frontendSetup) {
        setSetups(frontendSetup);
        cleanField();
        setVisibleTrue();
    }

    public static FrontendLevelImpl getINSTANCE(int levelWidth, int levelHeight, String space, Field field, FrontendSetup frontendSetup) {
        if (INSTANCE == null) {
            INSTANCE = new FrontendLevelImpl(levelWidth, levelHeight, space, field, frontendSetup);
        }
        return INSTANCE;
    }

    @Override
    public void playerStep(Field field) {
        playerIsNext = true;
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                buttons[i][j].addActionListener(this);
            }
        }

        while (playerIsNext) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void setVisibleFalse() {
        frame.setVisible(false);
    }


    private void setVisibleTrue() {
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (playerIsNext) {
                        if (buttons[i][j].getText() == "") {
                            buttons[i][j].setForeground(new Color(playerColor[0], playerColor[1], playerColor[2]));

                            buttons[i][j].setText(playerChar);
                            buttons[i][j].setFont(new Font("Arial", Font.BOLD, setFont(true)));
                            level[i][j] = "X";
                            Coordinates stepCoordinate = new Coordinates(i, j);
                            field.setLevel(stepCoordinate, true);
                            playerIsNext = false;

                        }

                    }
                }
            }
        }


    }

    private int setFont(boolean isPlayer) {
        String[] characters = {"X", "O", "█", "♪", "▼", "▲", "♥", "☻", "+", "♦", "@", "#"};
        if (isPlayer && (playerChar.equals("♥") || playerChar.equals("♪") || playerChar.equals("#") || playerChar.equals("+"))) {
            return 20;
        } else if (isPlayer && (playerChar.equals("x") || playerChar.equals("o"))) {
            return 18;
        } else if (isPlayer && (playerChar.equals("▲") || playerChar.equals("▼") || playerChar.equals("█") || playerChar.equals("@"))) {
            return 16;
        } else if (isPlayer && playerChar.equals("♦")) {
            return 25;
        } else if (!isPlayer && (pcChar.equals("♥") || pcChar.equals("♪") || pcChar.equals("#") || pcChar.equals("+"))) {
            return 20;
        } else if (!isPlayer && (pcChar.equals("x") || pcChar.equals("o"))) {
            return 18;
        } else if (!isPlayer && (pcChar.equals("▲") || pcChar.equals("▼") || pcChar.equals("█") || pcChar.equals("@"))) {
            return 16;
        } else if (!isPlayer && pcChar.equals("♦")) {
            return 25;
        }
        return 15;
    }


    @Override
    public void pcStep(Coordinates stepCoordinates) {

        buttons[stepCoordinates.getVertical()][stepCoordinates.getHorizontal()].setText(pcChar);
        try {
            Thread.sleep(30);
            buttons[stepCoordinates.getVertical()][stepCoordinates.getHorizontal()].setForeground(new Color(0, 225, 225));
            Thread.sleep(30);
            buttons[stepCoordinates.getVertical()][stepCoordinates.getHorizontal()].setForeground(new Color(pcColor[0], pcColor[1], pcColor[2]));
            Thread.sleep(30);
            buttons[stepCoordinates.getVertical()][stepCoordinates.getHorizontal()].setForeground(new Color(0, 225, 225));
            Thread.sleep(30);
            buttons[stepCoordinates.getVertical()][stepCoordinates.getHorizontal()].setForeground(new Color(pcColor[0], pcColor[1], pcColor[2]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setText(boolean isPlayer) {
        if (isPlayer) {
            textfield.setText("A számítógép következik");
        } else {
            textfield.setText("A játékos következik");
        }
    }

    @Override
    public void setwinnerCordinates(boolean isPlayer, Coordinates[] winnerCoordinates) {
        for (int i = 0; i < winnerCoordinates.length; i++) {
            if (isPlayer) {
                buttons[winnerCoordinates[i].getVertical()][winnerCoordinates[i].getHorizontal()].setBackground(Color.GREEN);
                textfield.setText("A játékos nyert!");
            } else {
                buttons[winnerCoordinates[i].getVertical()][winnerCoordinates[i].getHorizontal()].setBackground(Color.RED);
                textfield.setText("A számítógép nyert!");
            }
        }
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void cleanField() {
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(new JButton().getBackground());
                buttons[i][j].setText("");
                textfield.setText("Amőba - játék");
            }
        }
    }
}


