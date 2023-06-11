package amoeba.program;

import amoeba.frontend.*;
import amoeba.frontend.setup.CharacterSetup;
import amoeba.frontend.setup.ColorSetup;
import amoeba.frontend.setup.DifficultSetup;
import amoeba.frontend.setup.FrontendSetup;


public class Control {
    public void start(){
        FrontendSetup frontendSetup = new FrontendSetup();
        DifficultSetup difficultSetup = new DifficultSetup(frontendSetup);
        CharacterSetup characterSetup = new CharacterSetup(frontendSetup);
        ColorSetup colorSetup = new ColorSetup(frontendSetup);
        Field field = new Field();
        while (true) {

            FrontendRules frontendRules = new FrontendRules();

            int gameOrSetup = 0;
            while (gameOrSetup != 1) {
                gameOrSetup = frontendRules.rules(frontendSetup);
                if (gameOrSetup == 2) {
                    frontendSetup.setVisible(true);
                    frontendSetup.waitingForTheClick(difficultSetup, characterSetup, colorSetup);
                }
                frontendSetup.setVisible(false);
                frontendRules.setVisible(true);
            }
            frontendRules.setVisible(false);

            boolean reMatch;

            do {

                FrontendLevel frontendlevel = FrontendLevelImpl.getINSTANCE(Field.WIDTH, Field.HEIGHT, Field.SPACE, field, frontendSetup);

                Game game = new Game();
                frontendlevel.setBasicState(frontendSetup);
                game.play(frontendlevel, frontendSetup, field);

                FrontendAskPlayer askRematch = new FrontendAskPlayer();
                reMatch = askRematch.yesOrNo("Szeretnél még egy menetet játszani az aktuális beállításokkal?");

                if (!reMatch) {
                    frontendlevel.setVisibleFalse();
                }
            } while (reMatch);

        }





    }
}
