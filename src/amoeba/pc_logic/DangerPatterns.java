package amoeba.pc_logic;

import amoeba.program.Field;
import amoeba.program.Coordinates;

import java.util.Random;

public class DangerPatterns {

    private Coordinates stepCoodinate(int vertical, int horizontal) {
        Coordinates stepCoordinate = new Coordinates(vertical, horizontal);
        return stepCoordinate;
    }

    private boolean isTwoSameCoordinateVertical(Field field, String character, int vertical, int horizontal, int pushAway) {
        return isSomecharacter(field, character, new Coordinates(vertical + pushAway, horizontal), new Coordinates(vertical + 1 + pushAway, horizontal));
    }

    private boolean isTwoSameCoordinateHorizontal(Field field, String character, int vertical, int horizontal, int pushAway) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal + pushAway), new Coordinates(vertical, horizontal + 1 + pushAway));
    }

    private boolean isTwoSameCoordinateDiagonalRight(Field field, String character, int vertical, int horizontal, int pushAway) {
        return isSomecharacter(field, character, new Coordinates(vertical + pushAway, horizontal + pushAway), new Coordinates(vertical + 1 + pushAway, horizontal + 1 + pushAway));
    }

    private boolean isTwoSameCoordinateDiagonalLeft(Field field, String character, int vertical, int horizontal, int pushAway) {
        return isSomecharacter(field, character, new Coordinates(vertical + pushAway, horizontal - pushAway), new Coordinates(vertical + 1 + pushAway, horizontal - 1 - pushAway));
    }

    private boolean isTwoSameCoordinateVerticalWithHole(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 2, horizontal));
    }

    private boolean isTwoSameCoordinateHorizontalWithHole(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical, horizontal + 2));
    }

    private boolean isTwoSameCoordinateDiagonalRightWithHole(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 2, horizontal + 2));
    }

    private boolean isTwoSameCoordinateDiagonalLeftWithHole(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 2, horizontal - 2));
    }

    private boolean isThreeSameCoordinateVertical(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 1, horizontal), new Coordinates(vertical + 2, horizontal));
    }

    private boolean isThreeSameCoordinateHorizontal(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical, horizontal + 1), new Coordinates(vertical, horizontal + 2));
    }

    private boolean isThreeSameCoordinateDiagonalRight(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 1, horizontal + 1), new Coordinates(vertical + 2, horizontal + 2));
    }

    private boolean isThreeSameCoordinateDiagonalLeft(Field field, String character, int vertical, int horizontal) {
        return isSomecharacter(field, character, new Coordinates(vertical, horizontal), new Coordinates(vertical + 1, horizontal - 1), new Coordinates(vertical + 2, horizontal - 2));
    }


    private boolean isSomecharacter(Field field, String character, Coordinates... coordinates) {
        int number = 1;
        for (int i = 0; i < coordinates.length; i++) {

            if (field.getLevel()[coordinates[i].getVertical()][coordinates[i].getHorizontal()].equals(character)) {
                number = number * 1;
            } else {
                number = number * 0;
            }
        }
        if (number == 0) {
            return false;
        }
        return true;
    }

    private boolean isEmptyOrCharacter(Field field, String character, String space, Coordinates... coordinates) {
        int number = 1;
        for (int i = 0; i < coordinates.length; i++) {

            if (field.getLevel()[coordinates[i].getVertical()][coordinates[i].getHorizontal()].equals(character) || field.getLevel()[coordinates[i].getVertical()][coordinates[i].getHorizontal()].equals(space)) {
                number = number * 1;
            } else {
                number = number * 0;
            }
        }
        if (number == 0) {
            return false;
        }
        return true;
    }

    private int randomClosing(int upperLimit) {
        Random random = new Random();
        return random.nextInt(upperLimit);
    }

    public Coordinates fourSameCharacter(String character, String playerCharacter, String pcCharacter, Field field) {
        Coordinates stepCoodinate = null;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {

                //--------függőlegesen 4 egymás alatt
                if (i < Field.HEIGHT - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateVertical(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 4, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 4, j);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 3 && 0 < i) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateVertical(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                // Függőlegesen 5 egymás alatt, de az egyik hiányzik
                if (i < Field.HEIGHT - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateVertical(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateVertical(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateVerticalWithHole(field, character, i + 2, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }

                //--------vízszintesen 4 egymás mellett
                if (j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i, j + 4);
                        break;
                    }
                }
                if (j < Field.WIDTH - 3 && 0 < j) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }

                // Vízszintesen 5 egymás mellett, de az egyik hiányzik
                if (j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i, j + 1);
                        break;
                    }
                }

                if (j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                if (j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }

                //--------Atlósan jobbra lefele 4 egymas mellett
                if (i < Field.HEIGHT - 4 && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 4, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 4, j + 4);
                        break;
                    }
                }
                if (0 < i && 0 < j && i < Field.HEIGHT - 3 && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }

                // Atlósan jobbra 5 egymás alatt, de az egyik hiányzik
                if (i < Field.HEIGHT - 5 && j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j + 1);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 5 && j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;

                    }
                }
                if (i < Field.HEIGHT - 5 && j < Field.WIDTH - 5) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 2, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }

                //-------Atlósan balra lefelé 4 egymás mellett
                if (i < Field.HEIGHT - 4 && 3 < j) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 4, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 4, j - 4);
                        break;
                    }
                }
                if (0 < i && i < Field.HEIGHT - 3 && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j + 1);
                        break;
                    }
                }
                // Atlósan balra 5 egymás alatt, de az egyik hiányzik
                if (i < Field.HEIGHT - 5 && 4 < j) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j - 1);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 5 && 4 < j) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j - 2);
                        break;
                    }
                }
                if (i < Field.HEIGHT - 5 && 4 < j) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 2, j - 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j - 3);
                        break;
                    }
                }
            }
        }
        /*
        if (stepCoodinate != null && character.equals(playerCharacter)) {
            System.out.println("A gép 4 azonos karaktert talált egymás mellett, ezért lezárta azt.");
        } else if (stepCoodinate != null && character.equals(pcCharacter)) {
            System.out.println("A gép 4 egymás melletti karakterét továbbépítette és ezzel nyert.");
        }
         */

        return stepCoodinate;
    }

    public Coordinates threeSameChareacterAndNotClosed(String character, String playerCharacter, String pcCharacter, Field field) {
        Coordinates stepCoodinate = null;
        int nullOrOne;
        boolean pcHasNotSteppedYet = true;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {


                //--------függőlegesen 3 egymás alatt lezáratlanul
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 3) {

                    boolean threeSameCharacter = isThreeSameCoordinateVertical(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j));
                    if (threeSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 3, j);
                        } else {
                            stepCoodinate = stepCoodinate(i - 1, j);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //--------vízszintesen 3 egymás mellett lezáratlanul
                if (pcHasNotSteppedYet && 0 < j && j < Field.WIDTH - 3) {

                    boolean threeSameCharacter = isThreeSameCoordinateHorizontal(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 3), new Coordinates(i, j - 1));
                    if (threeSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i, j + 3);
                        } else {
                            stepCoodinate = stepCoodinate(i, j - 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //-----Atlósan jobbra lefelé 3 egymás mellett lezáratlanul
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 3 && 0 < j && j < Field.WIDTH - 3) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalRight(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j + 3), new Coordinates(i - 1, j - 1));
                    if (threeSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 3, j + 3);
                        } else {
                            stepCoodinate = stepCoodinate(i - 1, j - 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //---Atlósan balra lefelé 3 egymás mellett lezáratlanul
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 3 && 2 < j && j < Field.WIDTH - 1) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalLeft(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j - 3), new Coordinates(i - 1, j + 1));
                    if (threeSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 3, j - 3);
                        } else {
                            stepCoodinate = stepCoodinate(i - 1, j + 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt négy, de köztük az egyik hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j), new Coordinates(i - 1, j));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 4, j), new Coordinates(i - 1, j));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás mellett négy, de köztük az egyik hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 4), new Coordinates(i, j - 1));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 4), new Coordinates(i, j - 1));
                    if (threeSameCharacter && empty1) {
                        stepCoodinate = stepCoodinate(i, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt átlósan jobbra lefele négy, de köztük az egyik hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 0 < j && j < Field.WIDTH - 4) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 4, j + 4), new Coordinates(i - 1, j - 1));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 0 < j && j < Field.WIDTH - 4) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 4, j + 4), new Coordinates(i - 1, j - 1));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt átlósan balra lefele négy, de köztük az egyik hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 3 < j && j < Field.WIDTH - 1) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j - 1), new Coordinates(i + 3, j - 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j - 2), new Coordinates(i + 4, j - 4), new Coordinates(i - 1, j + 1));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j - 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 3 < j && j < Field.WIDTH - 1) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j - 2), new Coordinates(i + 3, j - 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1), new Coordinates(i + 4, j - 4), new Coordinates(i - 1, j + 1));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j - 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

            }
        }
        /*
        if (!pcHasNotSteppedYet && character.equals(playerCharacter)) {
            System.out.println("A gép 3 azonos karaktert talált egymás mellett, ami egyik oldalról sincs lezárva ezért lezárta azt.");
        } else if (!pcHasNotSteppedYet && character.equals(pcCharacter)) {
            System.out.println("A gép 3 egymás melletti egyik oldalról sem zárt karakterét továbbépítette.");
        }
         */

        return stepCoodinate;
    }

    public Coordinates threeSameCharacter(String character, String playerCharacter, String pcCharacter, Field field) {
        boolean pcHasNotSteppedYet = true;
        Coordinates stepCoodinate = null;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {

                //--------függőlegesen 3 egymás alatt
                if (pcHasNotSteppedYet && i < (Field.HEIGHT - 4)) {

                    boolean threeSameCharacter = isThreeSameCoordinateVertical(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i + 4, j));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }

                }
                if (pcHasNotSteppedYet && 1 < i && i < (Field.HEIGHT - 2)) {

                    boolean threeSameCharacter = isThreeSameCoordinateVertical(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt négy, de köztük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 1, j));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 4, j));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i - 1, j));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 1, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                //--------vízszintesen 3 egymás mellett

                if (pcHasNotSteppedYet && j < (Field.WIDTH - 4)) {

                    boolean threeSameCharacter = isThreeSameCoordinateHorizontal(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 3), new Coordinates(i, j + 4));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 1 < j && j < (Field.WIDTH - 2)) {

                    boolean threeSameCharacter = isThreeSameCoordinateHorizontal(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                //egymás mellett négy, de köztük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j - 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 4)) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j - 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //---------Atlósan jobbra lefelé 3 egymás mellett
                if (pcHasNotSteppedYet && i < Field.HEIGHT - 4 && j < Field.WIDTH - 4) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalRight(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j + 3), new Coordinates(i + 4, j + 4));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                if (pcHasNotSteppedYet && 1 < i && 1 < j && i < Field.HEIGHT - 2 && j < Field.WIDTH - 2) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalRight(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt átlósan jobbra lefele négy, de köztük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < i && 0 < j && j < Field.WIDTH - 4 && i < Field.HEIGHT - 4) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 4, j + 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 0 < i && 0 < j && j < Field.WIDTH - 4 && i < Field.HEIGHT - 4) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 4, j + 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i - 1, j - 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 1, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //-------Atlósan balra lefelé 3 egymás mellett
                if (pcHasNotSteppedYet && i < Field.HEIGHT - 4 && 3 < j) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalLeft(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j - 3), new Coordinates(i + 4, j - 4));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 3, j - 3);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && 1 < i && i < Field.HEIGHT - 2 && 1 < j && j < Field.WIDTH - 2) {

                    boolean threeSameCharacter = isThreeSameCoordinateDiagonalLeft(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j + 1), new Coordinates(i - 2, j + 2));
                    if (threeSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //egymás alatt átlósan balra lefele négy, de köztük az egyik hiányzik
                if (pcHasNotSteppedYet && i < Field.HEIGHT - 4 && 0 < i && 3 < j && j < Field.WIDTH - 1) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 1, j - 1), new Coordinates(i + 3, j - 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j - 2), new Coordinates(i + 4, j - 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j - 2), new Coordinates(i - 1, j + 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 2, j - 2);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
                if (pcHasNotSteppedYet && i < Field.HEIGHT - 4 && 0 < i && 3 < j && j < Field.WIDTH - 1) {

                    boolean threeSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 2, j - 2), new Coordinates(i + 3, j - 3));
                    boolean empty1 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1), new Coordinates(i + 4, j - 4));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1), new Coordinates(i - 1, j + 1));
                    if (threeSameCharacter && (empty1 || empty2)) {
                        stepCoodinate = stepCoodinate(i + 1, j - 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
            }
        }
        /*
        if (!pcHasNotSteppedYet && character.equals(playerCharacter)) {
            System.out.println("A gép 3 azonos karaktert talált egymás mellett, ezért lezárta azt.");
        } else if (!pcHasNotSteppedYet && character.equals(pcCharacter)) {
            System.out.println("A gép 3 egymás melletti karakterét továbbépítette.");
        }
         */
        return stepCoodinate;
    }

    public Coordinates twoSameCharacterAndNotClosed(String character, String playerCharacter, String pcCharacter, Field field) {
        Coordinates stepCoodinate = null;
        int nullOrOne;
        boolean pcHasNotSteppedYet = true;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {

                //--------függőlegesen 2 egymás alatt
                if (pcHasNotSteppedYet && 1 < i && i < Field.HEIGHT - 3) {
                    boolean twoSameCharacter = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 1, j));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j));
                    boolean empty3 = isSomecharacter(field, Field.SPACE, new Coordinates(i - 2, j));
                    if (twoSameCharacter && empty && empty2 && empty3) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 2, j);
                        } else {
                            stepCoodinate = stepCoodinate(i - 1, j);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    } else if (twoSameCharacter && empty && empty2) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    } else if (twoSameCharacter && empty && empty3) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }

                //--------vízszintesen 2 egymás mellett
                if (pcHasNotSteppedYet && 1 < j && j < Field.WIDTH - 3) {
                    boolean twoSameCharacter = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j - 1));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 3));
                    boolean empty3 = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 2));
                    if (twoSameCharacter && empty && empty2 && empty3) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i, j + 2);
                        } else {
                            stepCoodinate = stepCoodinate(i, j - 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    } else if (twoSameCharacter && empty && empty2) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    } else if (twoSameCharacter && empty && empty3) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }

                //-----Atlósan jobbra lefelé 2 egymás mellett
                if (pcHasNotSteppedYet && 1 < i && i < Field.HEIGHT - 3 && 1 < j && j < Field.WIDTH - 3) {
                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 2, j + 2));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j + 3));
                    boolean empty3 = isSomecharacter(field, Field.SPACE, new Coordinates(i - 2, j - 2));
                    if (twoSameCharacter && empty && empty2 && empty3) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i - 1, j - 1);
                        } else {
                            stepCoodinate = stepCoodinate(i + 2, j + 2);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    } else if (twoSameCharacter && empty && empty2) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    } else if (twoSameCharacter && empty && empty3) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }

                //----Atlósan balra lefelé 2 egymás mellett
                if (pcHasNotSteppedYet && 1 < i && i < Field.HEIGHT - 3 && 2 < j && j < Field.WIDTH - 2) {
                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j + 1), new Coordinates(i + 2, j - 2));
                    boolean empty2 = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j - 3));
                    boolean empty3 = isSomecharacter(field, Field.SPACE, new Coordinates(i - 2, j + 2));
                    if (twoSameCharacter && empty && empty2 && empty3) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i - 1, j + 1);
                        } else {
                            stepCoodinate = stepCoodinate(i + 2, j - 2);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    } else if (twoSameCharacter && empty && empty2) {
                        stepCoodinate = stepCoodinate(i + 2, j - 2);
                        break;
                    } else if (twoSameCharacter && empty && empty3) {
                        stepCoodinate = stepCoodinate(i - 1, j + 1);
                        break;
                    }
                }

                //--------függőlegesen 3 egymás alatt és közülük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 3)) {
                    boolean twoSameCharacter = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i - 1, j));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //--------vízszintesen 3 egymás mellett  és közülük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i, j - 1));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //---------Atlósan jobbra lefelé 3 egymás mellett  és közülük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < i && 0 < j && i < Field.HEIGHT - 3 && j < Field.WIDTH - 3) {
                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j + 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }

                //-------Atlósan balra lefelé 3 egymás mellett  és közülük az egyik hiányzik
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 3 && 2 < j && j < Field.WIDTH - 1) {
                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1), new Coordinates(i - 1, j + 1), new Coordinates(i + 3, j - 3));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 1, j - 1);
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }


                //egymás alatt négy, de köztük kettő hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < (Field.HEIGHT - 4)) {
                    boolean twoSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 3, j));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 2, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j));
                    if (twoSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 2, j);
                        } else {
                            stepCoodinate = stepCoodinate(i + 1, j);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }


                //egymás mellett négy, de köztük kettő hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i, j + 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 2), new Coordinates(i, j - 1), new Coordinates(i, j + 4));
                    if (twoSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i, j + 2);
                        } else {
                            stepCoodinate = stepCoodinate(i, j + 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }


                //egymás alatt átlósan jobbra lefele négy, de köztük kettő hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 0 < j && j < Field.WIDTH - 4) {
                    boolean twoSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 3, j + 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4));
                    if (twoSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 2, j + 2);
                        } else {
                            stepCoodinate = stepCoodinate(i + 1, j + 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }


                //egymás alatt átlósan balra lefele négy, de köztük kettő hiányzik és lezárva egyik vége sincs
                if (pcHasNotSteppedYet && 0 < i && i < Field.HEIGHT - 4 && 3 < j && j < Field.WIDTH - 1) {
                    boolean twoSameCharacter = isSomecharacter(field, character, new Coordinates(i, j), new Coordinates(i + 3, j - 3));
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j - 1), new Coordinates(i + 2, j - 2), new Coordinates(i - 1, j + 1), new Coordinates(i + 4, j - 4));
                    if (twoSameCharacter && empty) {
                        nullOrOne = randomClosing(2);
                        if (nullOrOne == 0) {
                            stepCoodinate = stepCoodinate(i + 2, j - 2);
                        } else {
                            stepCoodinate = stepCoodinate(i + 1, j - 1);
                        }
                        pcHasNotSteppedYet = false;
                        break;
                    }
                }
            }
        }
/*
        if (!pcHasNotSteppedYet && character.equals(playerCharacter)) {
            System.out.println("A gép lezáratlan kettest talált, amiből lezáratlan hármast lehetett volna csinálni, ezért lezárta azt.");
        } else if (!pcHasNotSteppedYet && character.equals(pcCharacter)) {
            System.out.println("A gép a lezáratlan kettesét továbbépítette.");
        }
 */
        return stepCoodinate;
    }

    public Coordinates twoSameCharacter(String character, String playerCharacter, String pcCharacter, Field field) {
        Coordinates stepCoodinate = null;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {

                //--------függőlegesen 2 egymás alatt
                if (i < Field.HEIGHT - 4) {
                    boolean twoSameCharacter = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 4, j));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }

                if (2 < i && i < Field.HEIGHT - 1) {

                    boolean twoSameCharacter = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 3, j));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }

                //--------vízszintesen 2 egymás mellett
                if (j < Field.WIDTH - 4) {

                    boolean twoSameCharacter = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i, j + 4));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }

                if (2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i, j - 3));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }


                //-----Atlósan jobbra lefelé 2 egymás mellett
                if (j < Field.WIDTH - 4 && i < Field.HEIGHT - 4) {

                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3), new Coordinates(i + 4, j + 4));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }

                if (2 < i && i < Field.HEIGHT - 1 && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2), new Coordinates(i - 3, j - 3));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }

                //----Atlósan balra lefelé 2 egymás mellett
                if (i < Field.HEIGHT - 4 && 3 < j) {

                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j - 2), new Coordinates(i + 3, j - 3), new Coordinates(i + 4, j - 4));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i + 2, j - 2);
                        break;
                    }
                }
                if (2 < i && i < Field.HEIGHT - 1 && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter = isTwoSameCoordinateDiagonalLeft(field, character, i, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j + 1), new Coordinates(i - 2, j + 2), new Coordinates(i - 3, j + 3));
                    if (twoSameCharacter && empty) {
                        stepCoodinate = stepCoodinate(i - 1, j + 1);
                        break;
                    }
                }
            }
        }
        /*
        if (stepCoodinate != null && character.equals(playerCharacter)) {
            System.out.println("A gép 2 azonos karaktert talált egymás mellett, ezért lezárta azt.");
        } else if (stepCoodinate != null && character.equals(pcCharacter)) {
            System.out.println("A gép 2 egymás melletti karakterét továbbépítette.");
        }
         */

        return stepCoodinate;
    }

    public Coordinates onlyOneCharacter(String actualCharacter, String playerCharacter, String pcCharacter, Field field) {

        Random random = new Random();
        int horizontal;
        int vertical;
        int numberOfAttempts = 0;
        Coordinates stepCoodinate = null;

        outer:
        for (int i = 1; i < Field.HEIGHT - 1; i++) {
            for (int j = 1; j < Field.WIDTH - 1; j++) {
                if (field.getLevel()[i][j].equals(actualCharacter)) {
                    do {
                        horizontal = random.nextInt(3) - 1;
                        vertical = random.nextInt(3) - 1;
                        numberOfAttempts++;
                        if (field.getLevel()[i + vertical][j + horizontal].equals(Field.SPACE)) {
                            stepCoodinate = stepCoodinate(i + vertical, j + horizontal);
                            break outer;


                        }
                    } while (!(field.getLevel()[i + vertical][j + horizontal].equals(Field.SPACE)) && numberOfAttempts < 5);
                    numberOfAttempts = 0;
                }
            }
        }
        /*
        if (stepCoodinate != null && actualCharacter.equals(playerCharacter)) {
            System.out.println("A gép a játékos melletti egyik mezőre lépett véletlenszerűen");
        } else if (stepCoodinate != null && actualCharacter.equals(pcCharacter)) {
            System.out.println("A gép a saját karaktere melletti egyik mezőre lépett véletlenszerűen");
        }
         */
        return stepCoodinate;
    }

    public Coordinates randomstep(Field field) {
        Random random = new Random();
        int horizontal;
        int vertical;
        Coordinates stepCoodinate;
        do {
            horizontal = random.nextInt(Field.WIDTH - 8) + 4;
            vertical = random.nextInt(Field.HEIGHT - 8) + 4;
            stepCoodinate = stepCoodinate(vertical, horizontal);
            System.out.println();

        } while (field.getLevel()[vertical][horizontal].equals(Field.PLAYER_CHARACTER) || field.getLevel()[vertical][horizontal].equals(Field.PC_CHARACTER));
        field.getLevel()[vertical][horizontal] = Field.PC_CHARACTER;
        /*
        System.out.println("A gép véletlenszerűen lépett");

         */

        return stepCoodinate;
    }


    public Coordinates twiceTwoSameCharacterDanger(String[][] level, String character, String playerCharacter, String pcCharacter, Field field) {

        Coordinates stepCoodinate = null;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {

                // 2 függőleges és két vízszintes
                //1.1
                if (0 < i && i < (Field.HEIGHT - 3) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 2, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 2, j - 3), new Coordinates(i + 2, j + 1), new Coordinates(i + 3, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //1.2
                if (1 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 1, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j), new Coordinates(i + 2, j), new Coordinates(i - 1, j - 3), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //1.3
                if (1 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 1, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 1, j + 3), new Coordinates(i + 2, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //1.4
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j + 3), new Coordinates(i + 2, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //2.1
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j - 3), new Coordinates(i + 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.2
                if (2 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 3), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.3
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 3), new Coordinates(i + 2, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.4
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 3), new Coordinates(i + 3, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.5
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j - 3), new Coordinates(i + 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.6
                if (2 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 3), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.7
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 3), new Coordinates(i + 2, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.8
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 3), new Coordinates(i + 3, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //3.1
                if (0 < i && i < (Field.HEIGHT - 3) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 2, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 2, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.2
                if (1 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j), new Coordinates(i + 2, j), new Coordinates(i - 1, j - 4), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.3
                if (1 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.4
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 2, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.5
                if (0 < i && i < (Field.HEIGHT - 3) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 2, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 2, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.6
                if (1 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j), new Coordinates(i + 2, j), new Coordinates(i - 1, j - 4), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.7
                if (1 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 1, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.8
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 2, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 2, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //4.1
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 1), new Coordinates(i + 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.2
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.3
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.4
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.5
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 1), new Coordinates(i + 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.6
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.7
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i - 2, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.8
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontal(field, character, i + 3, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.9
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 3, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 1), new Coordinates(i + 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.10
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 2, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.11
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 2, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.12
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.13
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 3, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 4, j), new Coordinates(i + 3, j + 1), new Coordinates(i + 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.14
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 2, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j), new Coordinates(i + 2, j), new Coordinates(i - 2, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.15
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i - 2, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i , j), new Coordinates(i - 2, j), new Coordinates(i - 2, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 2, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.16
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateHorizontalWithHole(field, character, i + 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 3, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }


                // 2 függőleges és két átlós baljra lefelé
                //1.1
                if (0 < i && i < (Field.HEIGHT - 5) && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 3, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 1, j + 1), new Coordinates(i + 5, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //1.2
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i - 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //1.3
                if (1 < i && i < (Field.HEIGHT - 2) && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 3), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        level[i - 1][j] = pcCharacter;
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //1.4
                if (3 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j - 1), new Coordinates(i - 4, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //2.1
                if ((0 < i && i < (Field.HEIGHT - 6) && 2 < j && j < Field.WIDTH - 1)) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 6, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.2
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.3
                if (2 < i && i < (Field.HEIGHT - 2) && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 1, j - 3), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.4
                if (4 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 5, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.5
                if ((0 < i && i < (Field.HEIGHT - 6) && 2 < j && j < Field.WIDTH - 1)) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i + 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 6, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.6
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i + 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.7
                if (2 < i && i < (Field.HEIGHT - 2) && 2 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 1, j - 3), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.8
                if (4 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 3) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 5, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //3.1
                if (0 < i && i < (Field.HEIGHT - 6) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j + 1), new Coordinates(i + 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.2
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.3
                if (1 < i && i < (Field.HEIGHT - 3) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i + 3, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.4
                if (4 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j - 1), new Coordinates(i - 5, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.5
                if (0 < i && i < (Field.HEIGHT - 6) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 3, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j + 1), new Coordinates(i + 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.6
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 1), new Coordinates(i - 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.7
                if (1 < i && i < (Field.HEIGHT - 3) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i + 3, j - 4), new Coordinates(i - 2, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.8
                if (4 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 4, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 3, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j - 1), new Coordinates(i - 5, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //4.1
                if (0 < i && i < (Field.HEIGHT - 7) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 5, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j), new Coordinates(i + 4, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 7, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.2
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.3
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 2, j - 4), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.4
                if (5 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 5, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.5
                if (0 < i && i < (Field.HEIGHT - 7) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 5, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 4, j), new Coordinates(i + 4, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 7, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.6
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.7
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 2, j - 4), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.8
                if (5 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 5, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.9
                if (0 < i && i < (Field.HEIGHT - 7) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 4, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j), new Coordinates(i + 5, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 7, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.10
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.11
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 2, j - 4), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.12
                if (5 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 5, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 4, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.13
                if (0 < i && i < (Field.HEIGHT - 7) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 4, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 4, j), new Coordinates(i + 5, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j + 1), new Coordinates(i + 7, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.14
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 1), new Coordinates(i - 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.15
                if (2 < i && i < (Field.HEIGHT - 2) && 3 < j && j < Field.WIDTH - 1) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i + 2, j - 4), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.16
                if (5 < i && i < (Field.HEIGHT - 2) && 0 < j && j < Field.WIDTH - 4) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 5, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 4, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j - 1), new Coordinates(i - 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                // 2 függőleges és két átlós jobra lefelé
                //1.1
                if (0 < i && i < (Field.HEIGHT - 5) && 0 < j && j < (Field.WIDTH - 3)) {

                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 1, j - 1), new Coordinates(i + 5, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //1.2
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j - 3), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //1.3
                if (1 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 1), new Coordinates(i + 2, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //1.4
                if (3 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j + 1), new Coordinates(i - 4, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //2.1
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 4, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 6, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.2
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i, j - 3), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.3
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 1, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.4
                if (4 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 4, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 5, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.5
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 4, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 6, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.6
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i, j - 3), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //2.7
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 1, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //2.8
                if (4 < i && i < (Field.HEIGHT - 2) && 2 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 4, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 5, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //3.1
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 1, j - 1), new Coordinates(i + 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.2
                if (1 < i && i < (Field.HEIGHT - 3) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i - 2, j - 4), new Coordinates(i + 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.3
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.4
                if (4 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 4, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j + 1), new Coordinates(i - 5, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.5
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 4, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i + 1, j - 1), new Coordinates(i + 6, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.6
                if (1 < i && i < (Field.HEIGHT - 3) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 3, j), new Coordinates(i - 1, j), new Coordinates(i - 2, j - 4), new Coordinates(i + 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j);
                        break;
                    }
                }
                //3.7
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i + 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i - 2, j - 1), new Coordinates(i + 3, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //3.8
                if (4 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 4, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 3, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 2, j), new Coordinates(i, j + 1), new Coordinates(i - 5, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j);
                        break;
                    }
                }
                //4.1
                if (0 < i && i < (Field.HEIGHT - 7) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 5, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 4, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 7, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.2
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j - 4), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.3
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.4
                if (5 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 5, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.5
                if (0 < i && i < (Field.HEIGHT - 7) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 5, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 4, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 7, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.6
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 2, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j - 4), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.7
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.8
                if (5 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 5, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.9
                if (0 < i && i < (Field.HEIGHT - 7) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 4, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 5, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 7, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.10
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i + 3, j), new Coordinates(i + 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j - 4), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.11
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.12
                if (5 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVertical(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 5, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j), new Coordinates(i - 2, j), new Coordinates(i - 4, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.13
                if (0 < i && i < (Field.HEIGHT - 7) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 4, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 5, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i + 2, j - 1), new Coordinates(i + 7, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.14
                if (0 < i && i < (Field.HEIGHT - 4) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j), new Coordinates(i + 3, j), new Coordinates(i + 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 4, j), new Coordinates(i - 1, j), new Coordinates(i - 1, j - 4), new Coordinates(i + 4, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j);
                        break;
                    }
                }
                //4.15
                if (2 < i && i < (Field.HEIGHT - 2) && 0 < j && j < (Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }
                //4.16
                if (5 < i && i < (Field.HEIGHT - 2) && 3 < j && j < (Field.WIDTH - 1)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateVerticalWithHole(field, character, i - 1, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 5, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j), new Coordinates(i - 4, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i + 2, j), new Coordinates(i - 3, j), new Coordinates(i - 1, j + 1), new Coordinates(i - 6, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j);
                        break;
                    }
                }

                // 2 víszintesen és két átlós balra lefelé
                //1.1
                if (0 < i && i < (Field.HEIGHT - 3) && 3 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i - 1, j), new Coordinates(i + 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //1.2
                if (2 < i && i < (Field.HEIGHT - 1) && 1 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 2), new Coordinates(i - 3, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //1.3
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 3), new Coordinates(i + 3, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //1.4
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 5)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j + 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 1), new Coordinates(i - 3, j + 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //2.1
                if (0 < i && i < (Field.HEIGHT - 3) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.2
                if (2 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.3
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 3, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.4
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 3, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.5
                if (0 < i && i < (Field.HEIGHT - 3) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.6
                if (2 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 3, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.7
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 3, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.8
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 2, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 3, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //3.1
                if (0 < i && i < (Field.HEIGHT - 4) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i + 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.2
                if (3 < i && i < (Field.HEIGHT - 1) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i - 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 2), new Coordinates(i - 4, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.3
                if (0 < i && i < (Field.HEIGHT - 4) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i + 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 3), new Coordinates(i + 4, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.4
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i - 1, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 1), new Coordinates(i - 4, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.5
                if (0 < i && i < (Field.HEIGHT - 4) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j - 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i + 2, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i - 1, j), new Coordinates(i + 4, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.6
                if (3 < i && i < (Field.HEIGHT - 1) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 2), new Coordinates(i - 4, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.7
                if (0 < i && i < (Field.HEIGHT - 4) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 3), new Coordinates(i + 4, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.8
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 5);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i - 2, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 1), new Coordinates(i - 4, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //4.1
                if (0 < i && i < (Field.HEIGHT - 4) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i + 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.2
                if (3 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 4, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.3
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 4, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.4
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 6, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 4, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.5
                if (0 < i && i < (Field.HEIGHT - 4) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i + 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.6
                if (3 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 4, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.7
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 4, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.8
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 6, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 4, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.9
                if (0 < i && i < (Field.HEIGHT - 4) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i + 2, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.10
                if (3 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 4, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.11
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 4, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.12
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 6);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i - 2, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 4, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.13
                if (0 < i && i < (Field.HEIGHT - 4) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i + 2, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.14
                if (3 < i && i < (Field.HEIGHT - 1) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 3), new Coordinates(i - 4, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.15
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 1, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 1, j + 4), new Coordinates(i + 4, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.16
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 3, j + 6);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i - 2, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 1, j + 2), new Coordinates(i - 4, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }


                // 2 víszintesen és két átlós jobbra lefelé
                //1.1
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 5)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j + 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 3, j + 5), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //1.2
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 3, j - 1), new Coordinates(i + 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }

                //1.3
                if (0 < i && i < (Field.HEIGHT - 3) && 1 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 3, j + 2), new Coordinates(i - 1, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //1.4
                if (2 < i && i < (Field.HEIGHT - 1) && 3 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j), new Coordinates(i - 3, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //2.1
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j + 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 3, j + 6), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.2
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 3, j), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.3
                if (0 < i && i < (Field.HEIGHT - 3) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 3, j + 1), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.4
                if (2 < i && i < (Field.HEIGHT - 1) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.5
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j + 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 3, j + 6), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.6
                if (2 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 3, j), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //2.7
                if (0 < i && i < (Field.HEIGHT - 3) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 1, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 3, j + 1), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //2.8
                if (2 < i && i < (Field.HEIGHT - 1) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 2, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //3.1
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j + 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i + 1, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 4, j + 6), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.2
                if (3 < i && i < (Field.HEIGHT - 1) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j - 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i - 1, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 4, j - 2), new Coordinates(i + 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.3
                if (0 < i && i < (Field.HEIGHT - 4) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i + 1, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 3), new Coordinates(i - 1, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.4
                if (3 < i && i < (Field.HEIGHT - 1) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1),  new Coordinates(i - 1, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j), new Coordinates(i - 4, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.5
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j + 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i + 2, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i + 4, j + 6), new Coordinates(i - 1, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.6
                if (3 < i && i < (Field.HEIGHT - 1) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 3), new Coordinates(i - 4, j - 2), new Coordinates(i + 1, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 2);
                        break;
                    }
                }
                //3.7
                if (0 < i && i < (Field.HEIGHT - 4) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i + 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 3), new Coordinates(i - 1, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //3.8
                if (3 < i && i < (Field.HEIGHT - 1) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j - 4);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1),  new Coordinates(i - 2, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 2), new Coordinates(i, j + 2), new Coordinates(i + 1, j), new Coordinates(i - 4, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 1);
                        break;
                    }
                }
                //4.1
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 4, j + 7), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.2
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 4, j - 1), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.3
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i + 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.4
                if (3 < i && i < (Field.HEIGHT - 1) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j - 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2),  new Coordinates(i - 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.5
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i + 1, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 4, j + 7), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.6
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i - 1, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 4, j - 1), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.7
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i + 2, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i + 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.8
                if (3 < i && i < (Field.HEIGHT - 1) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRight(field, character, i - 3, j - 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2),  new Coordinates(i - 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.9
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j + 4);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i + 2, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 4, j + 7), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.10
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 2), new Coordinates(i, j + 3), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 4, j - 1), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.11
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2), new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.12
                if (3 < i && i < (Field.HEIGHT - 1) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontal(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j - 5);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j - 2),  new Coordinates(i - 2, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.13
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j + 4);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i + 2, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i + 4, j + 7), new Coordinates(i - 1, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.14
                if (3 < i && i < (Field.HEIGHT - 1) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j + 1), new Coordinates(i, j + 3), new Coordinates(i - 2, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 1), new Coordinates(i, j + 4), new Coordinates(i - 4, j - 1), new Coordinates(i + 1, j + 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j + 3);
                        break;
                    }
                }
                //4.15
                if (0 < i && i < (Field.HEIGHT - 4) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i + 1, j - 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2), new Coordinates(i + 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j - 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                //4.16
                if (3 < i && i < (Field.HEIGHT - 1) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateHorizontalWithHole(field, character, i, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 3, j - 5);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i, j - 2),  new Coordinates(i - 2, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i, j - 3), new Coordinates(i, j + 2), new Coordinates(i + 1, j - 1), new Coordinates(i - 4, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i, j - 2);
                        break;
                    }
                }
                // két átlós
                //1.1
                if (1 < i && i < (Field.HEIGHT - 2) && 3 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i - 2, j), new Coordinates(i + 2, j - 4));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //1.2
                if (3 < i && i < (Field.HEIGHT - 2) && 1 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i, j - 2), new Coordinates(i - 4, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //1.3
                if (0 < i && i < (Field.HEIGHT - 5) && 0 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 3, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 1, j + 3), new Coordinates(i + 5, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //1.4
                if (0 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 5)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 3, j + 1), new Coordinates(i - 1, j + 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //2.1
                if (2 < i && i < (Field.HEIGHT - 2) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 1, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //2.2
                if (4 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 5, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //2.3
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 6, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //2.4
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //2.5
                if (2 < i && i < (Field.HEIGHT - 2) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 1, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //2.6
                if (4 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 5, j + 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //2.7
                if (0 < i && i < (Field.HEIGHT - 6) && 0 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 6, j));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //2.8
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //3.1
                if (1 < i && i < (Field.HEIGHT - 3) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 1, j - 3, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i, j - 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i - 2, j), new Coordinates(i + 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //3.2
                if (4 < i && i < (Field.HEIGHT - 2) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 4, j + 2, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i, j - 2), new Coordinates(i - 5, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //3.3
                if (0 < i && i < (Field.HEIGHT - 6) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 4, j, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 1, j + 3), new Coordinates(i + 6, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //3.4
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 1, j + 5, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 1, j + 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 3, j + 1), new Coordinates(i - 2, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //3.5
                if (1 < i && i < (Field.HEIGHT - 3) && 4 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j - 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i - 2, j), new Coordinates(i + 3, j - 5));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //3.6
                if (4 < i && i < (Field.HEIGHT - 2) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 4, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 3, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 2, j - 2), new Coordinates(i + 2, j + 2), new Coordinates(i, j - 2), new Coordinates(i - 5, j + 3));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 1, j - 1);
                        break;
                    }
                }
                //3.7
                if (0 < i && i < (Field.HEIGHT - 6) && 1 < j && (j < Field.WIDTH - 3)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 3, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 4, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 1, j + 3), new Coordinates(i + 6, j - 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //3.8
                if (1 < i && i < (Field.HEIGHT - 3) && 0 < j && (j < Field.WIDTH - 6)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j + 5);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 3, j + 1), new Coordinates(i - 2, j + 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 2, j + 2);
                        break;
                    }
                }
                //4.1
                if (2 < i && i < (Field.HEIGHT - 2) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2), new Coordinates(i - 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.2
                if (5 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 5, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2), new Coordinates(i - 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 6, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.3
                if (0 < i && i < (Field.HEIGHT - 7) && 1 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 5, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3), new Coordinates(i + 4, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 7, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.4
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 6, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3), new Coordinates(i + 2, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.5
                if (2 < i && i < (Field.HEIGHT - 2) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j - 4, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2), new Coordinates(i - 1, j - 3));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.6
                if (5 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i - 5, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2), new Coordinates(i - 3, j - 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 6, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.7
                if (0 < i && i < (Field.HEIGHT - 7) && 1 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i + 5, j + 1, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 4, j + 2));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 7, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.8
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeft(field, character, i, j + 6, 0);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 2, j + 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.9
                if (2 < i && i < (Field.HEIGHT - 2) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2), new Coordinates(i, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.10
                if (5 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 5, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i - 2, j - 2), new Coordinates(i - 4, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 6, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.11
                if (0 < i && i < (Field.HEIGHT - 7) && 1 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 4, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3), new Coordinates(i + 5, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 7, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.12
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRight(field, character, i, j, 0);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j + 6);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 2, j + 2), new Coordinates(i + 3, j + 3), new Coordinates(i + 1, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.13
                if (2 < i && i < (Field.HEIGHT - 2) && 5 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 1, j - 3);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2), new Coordinates(i, j - 4));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 3, j - 1), new Coordinates(i + 2, j - 6));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.14
                if (5 < i && i < (Field.HEIGHT - 2) && 2 < j && (j < Field.WIDTH - 2)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i - 1, j - 1);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i - 5, j + 1);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i, j), new Coordinates(i - 2, j - 2), new Coordinates(i - 4, j));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 3, j - 3), new Coordinates(i + 2, j + 2), new Coordinates(i - 1, j - 3), new Coordinates(i - 6, j + 2));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i - 2, j - 2);
                        break;
                    }
                }
                //4.15
                if (0 < i && i < (Field.HEIGHT - 7) && 1 < j && (j < Field.WIDTH - 4)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i + 4, j + 2);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 5, j + 1));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 2, j + 4), new Coordinates(i + 7, j - 1));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }
                //4.16
                if (0 < i && i < (Field.HEIGHT - 4) && 0 < j && (j < Field.WIDTH - 7)) {
                    boolean twoSameCharacter1 = isTwoSameCoordinateDiagonalRightWithHole(field, character, i, j);
                    boolean twoSameCharacter2 = isTwoSameCoordinateDiagonalLeftWithHole(field, character, i, j + 6);
                    boolean empty = isSomecharacter(field, Field.SPACE, new Coordinates(i + 1, j + 1), new Coordinates(i + 3, j + 3), new Coordinates(i + 1, j + 5));
                    boolean emptyOrCharacter = isEmptyOrCharacter(field, character, Field.SPACE, new Coordinates(i - 1, j - 1), new Coordinates(i + 4, j + 4), new Coordinates(i + 4, j + 2), new Coordinates(i - 1, j + 7));
                    if (twoSameCharacter1 && twoSameCharacter2 && empty && emptyOrCharacter) {
                        stepCoodinate = stepCoodinate(i + 3, j + 3);
                        break;
                    }
                }




            }
        }
        /*
        if (stepCoodinate != null && character.equals(playerCharacter)) {
            System.out.println("A gép 2 lezáratlan (vagy lezárt) hármas csinálási lehetőséget akadályozott meg");
        } else if (stepCoodinate != null && character.equals(pcCharacter)) {
            System.out.println("A gép 2 hármast hozott léptre egy lépésben.");
        }

         */


        return stepCoodinate;
    }

}


