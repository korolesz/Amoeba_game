package amoeba.program;

public class CheckWinImpl implements CheckWin {
    Coordinates[] winnerCoordinates = new Coordinates[5];


    public Coordinates[] getWinnerCoordinates() {
        return winnerCoordinates;
    }

    @Override
    public boolean check(String character, Field field) {


        boolean gameOver = false;

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {
                //függőleges
                if (!gameOver) {
                    if (i < Field.HEIGHT - 4) {
                        if (field.getLevel()[i][j].equals(character) && field.getLevel()[i + 1][j].equals(character) && field.getLevel()[i + 2][j].equals(character) && field.getLevel()[i + 3][j].equals(character) && field.getLevel()[i + 4][j].equals(character)) {
                            gameOver = true;
                            winnerCoordinates[0] = new Coordinates(i,j);
                            winnerCoordinates[1] = new Coordinates(i + 1,j);
                            winnerCoordinates[2] = new Coordinates(i + 2,j);
                            winnerCoordinates[3] = new Coordinates(i + 3,j);
                            winnerCoordinates[4] = new Coordinates(i + 4,j);
                        }
                    }
                }
                //vízszintes
                if (!gameOver) {
                    if (j < Field.WIDTH - 4) {
                        if (field.getLevel()[i][j].equals(character) && field.getLevel()[i][j + 1].equals(character) && field.getLevel()[i][j + 2].equals(character) && field.getLevel()[i][j + 3].equals(character) && field.getLevel()[i][j + 4].equals(character)) {
                            gameOver = true;
                            winnerCoordinates[0] = new Coordinates(i,j);
                            winnerCoordinates[1] = new Coordinates(i,j + 1);
                            winnerCoordinates[2] = new Coordinates(i,j + 2);
                            winnerCoordinates[3] = new Coordinates(i,j + 3);
                            winnerCoordinates[4] = new Coordinates(i,j + 4);
                        }
                    }
                }
                // átlók
                if (!gameOver) {
                    if (i < Field.HEIGHT - 4 && j < Field.WIDTH - 4) {
                        if (field.getLevel()[i][j].equals(character) && field.getLevel()[i + 1][j + 1].equals(character) && field.getLevel()[i + 2][j + 2].equals(character) && field.getLevel()[i + 3][j + 3].equals(character) && field.getLevel()[i + 4][j + 4].equals(character)) {
                            gameOver = true;
                            winnerCoordinates[0] = new Coordinates(i,j);
                            winnerCoordinates[1] = new Coordinates(i + 1,j + 1);
                            winnerCoordinates[2] = new Coordinates(i + 2,j + 2);
                            winnerCoordinates[3] = new Coordinates(i + 3,j + 3);
                            winnerCoordinates[4] = new Coordinates(i + 4,j + 4);
                        }

                        if (field.getLevel()[i][j + 4].equals(character) && field.getLevel()[i + 1][j + 3].equals(character) && field.getLevel()[i + 2][j + 2].equals(character) && field.getLevel()[i + 3][j + 1].equals(character) && field.getLevel()[i + 4][j].equals(character)) {
                            gameOver = true;
                            winnerCoordinates[0] = new Coordinates(i,j + 4);
                            winnerCoordinates[1] = new Coordinates(i + 1 ,j + 3);
                            winnerCoordinates[2] = new Coordinates(i + 2,j + 2);
                            winnerCoordinates[3] = new Coordinates(i + 3,j + 1);
                            winnerCoordinates[4] = new Coordinates(i + 4,j);
                        }
                    }
                }
            }
        }
        return gameOver;

    }
}
