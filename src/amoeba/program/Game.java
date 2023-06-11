package amoeba.program;

import amoeba.frontend.FrontendAskPlayer;
import amoeba.frontend.FrontendLevel;
import amoeba.frontend.setup.FrontendSetup;
import amoeba.pc_logic.PcLogic;
import amoeba.pc_logic.PcLogicImpl;


public class Game {

    public void play(FrontendLevel frontendlevel, FrontendSetup frontendSetup, Field field) {


        //Beckend pálya beállítása alapállapotba
        field.initLevel();


        // Ki kezdi a játékot
        FrontendAskPlayer frontendAskIsPlayerFirst= new FrontendAskPlayer();
        boolean playerIsnext = frontendAskIsPlayerFirst.yesOrNo("Szeretnéd kezdeni a játékot?");


        //Egy játék menet
        boolean gameOver = false;
        CheckWin checkWin = new CheckWinImpl();

        PcLogic pcLogic = new PcLogicImpl();

        while (!gameOver) {
            Coordinates stepCoordinate;

            // játékos lépése
            if (playerIsnext) {
                frontendlevel.playerStep(field);

                gameOver = checkWin.check(Field.PLAYER_CHARACTER, field);

                if (gameOver) {
                    frontendlevel.setwinnerCordinates(true, checkWin.getWinnerCoordinates());
                    break;
                } else{
                    frontendlevel.setText(true);
                }
                playerIsnext = false;
            }

            // Számítógép lépése
            if (!playerIsnext) {

                pcLogic.pcSleep(800);
                stepCoordinate = pcLogic.pcStep(field, frontendSetup);
                field.setLevel(stepCoordinate, false);
                frontendlevel.pcStep(stepCoordinate);
                gameOver = checkWin.check(Field.PC_CHARACTER, field);
                if (gameOver) {
                    frontendlevel.setwinnerCordinates(false, checkWin.getWinnerCoordinates());
                    break;
                } else {
                    frontendlevel.setText(false);
                }
                playerIsnext = true;
            }
        }
    }

}
