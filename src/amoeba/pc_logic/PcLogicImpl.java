package amoeba.pc_logic;

import amoeba.frontend.setup.FrontendSetup;
import amoeba.program.Coordinates;
import amoeba.program.Field;

public class PcLogicImpl implements PcLogic {
    DangerPatterns dangerPatterns = new DangerPatterns();
    int rounds = 0;

    @Override
    public void pcSleep(int period) {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coordinates pcStep(Field field, FrontendSetup frontendSetup) {

        /**
         SZÁMÍTÓGÉP LOGIKA - KÖNNYŰ:

         1.  - Saját magánál    4     azonos keresése
         2.  - Ellenfélnél      4     azonos keresése
         3.  - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
         4.  - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
         6.  - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
         10. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
         12. - Saját magánál    1     karakter mellé rakni véletlenszerűen
         14. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
         15. - Véletlen szerű lépés a pálya közepe táján
         */

        Coordinates stepCoordinate = null;
        rounds = rounds + 1;
        if (frontendSetup.getDifficultyLevel() == 1) {

            // 1. - Saját magánál    4     azonos keresése
            if (stepCoordinate == null && rounds % 10 != 0) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 2. - Ellenfélnél      4     azonos keresése
            if (stepCoordinate == null && rounds % 10 != 5) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 3. - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null && rounds % 10 != 3) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 4. - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null && rounds % 10 != 7) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 5. - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 6. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 7. - Saját magánál    1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 8. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 9. - Véletlen szerű lépés a pálya közepe táján
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.randomstep(field);
            }
        }

        //--------------------------------------------------------------------------------
        /**
         SZÁMÍTÓGÉP LOGIKA - KÖZEPES:

         1.  - Saját magánál    4     azonos keresése
         2.  - Ellenfélnél      4     azonos keresése
         3.  - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
         4.  - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
         5.  - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
         6.  - Saját magánál    2     azonos egyik oldalról SEM ZÁRT keresése
         7.  - Ellenfélnél      3     azonos egyik oldalról ZÁRT keresése
         8. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
         9. - Ellenfélnél      2     azonos egyik oldalról SEM ZÁRT keresése
         10. - Saját magánál    1     karakter mellé rakni véletlenszerűen
         11. - Ellenfélnél      2     azonos egyik oldalról ZÁRT keresése
         12. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
         13. - Véletlen szerű lépés a pálya közepe táján
         */

        if (frontendSetup.getDifficultyLevel() == 2) {
            // 1. - Saját magánál    4     azonos keresése
            if (stepCoordinate == null && rounds % 15 != 0) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 2. - Ellenfélnél      4     azonos keresése
            if (stepCoordinate == null && rounds % 15 != 8) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 3. - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 4. - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 5. - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 6. - Saját magánál    2     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacterAndNotClosed(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 7. - Ellenfélnél      3     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 8. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 9. - Ellenfélnél      2     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacterAndNotClosed(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 10. - Saját magánál    1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 11. - Ellenfélnél      2     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 12. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 13. - Véletlen szerű lépés a pálya közepe táján
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.randomstep(field);
            }
        }
        //--------------------------------------------------------------------------------
        /**
         SZÁMÍTÓGÉP LOGIKA - NEHÉZ:

         1.  - Saját magánál    4     azonos keresése
         2.  - Ellenfélnél      4     azonos keresése
         3.  - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
         4.  - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
         5.  - Saját magánál  2 x 2   azonos NEM ZÁRT keresése, amiből 2 x 3 gyártható
         6.  - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
         7.  - Ellenfélnél    2 x 2   azonos NEM ZÁRT keresése, amiből 2 x 3 gyártható
         8.  - Saját magánál    2     azonos egyik oldalról SEM ZÁRT keresése
         9.  - Ellenfélnél      3     azonos egyik oldalról ZÁRT keresése
         10. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
         11. - Ellenfélnél      2     azonos egyik oldalról SEM ZÁRT keresése
         12. - Saját magánál    1     karakter mellé rakni véletlenszerűen
         13. - Ellenfélnél      2     azonos egyik oldalról ZÁRT keresése
         14. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
         15. - Véletlen szerű lépés a pálya közepe táján
         */

        if (frontendSetup.getDifficultyLevel() == 3) {
            // 1. - Saját magánál    4     azonos keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 2. - Ellenfélnél      4     azonos keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.fourSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 3. - Saját magánál    3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 4. - Ellenfélnél      3     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameChareacterAndNotClosed(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 5. - Saját magánál  2 x 2   azonos NEM ZÁRT keresése, amiből 2 x 3 gyártható
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twiceTwoSameCharacterDanger(field.getLevel(), Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 6. - Saját magánál    3     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 7. - Ellenfélnél    2 x 2   azonos NEM ZÁRT keresése, amiből 2 x 3 gyártható
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twiceTwoSameCharacterDanger(field.getLevel(), Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 8. - Saját magánál    2     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacterAndNotClosed(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 9. - Ellenfélnél      3     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.threeSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 10. - Saját magánál    2     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 11. - Ellenfélnél      2     azonos egyik oldalról SEM ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacterAndNotClosed(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 12. - Saját magánál    1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PC_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 13. - Ellenfélnél      2     azonos egyik oldalról ZÁRT keresése
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.twoSameCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 14. - Ellenfélnél      1     karakter mellé rakni véletlenszerűen
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.onlyOneCharacter(Field.PLAYER_CHARACTER, Field.PLAYER_CHARACTER, Field.PC_CHARACTER, field);
            }

            // 15. - Véletlen szerű lépés a pálya közepe táján
            if (stepCoordinate == null) {
                stepCoordinate = dangerPatterns.randomstep(field);
            }
        }
        return stepCoordinate;
    }


}



