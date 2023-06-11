package amoeba.program;

public interface CheckWin {
    boolean check(String character, Field field);

    Coordinates[] getWinnerCoordinates();

}

