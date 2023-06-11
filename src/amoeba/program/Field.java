package amoeba.program;

public class Field {

    private String[][] level;

    public final static String PC_CHARACTER = "O";
    public final static String PLAYER_CHARACTER = "X";
    public final static int HEIGHT = 15;
    public final static int WIDTH = 28;
    public final static String SPACE = " ";


    public Field() {
        this.level = new String[HEIGHT][WIDTH];
    }
    public String[][] getLevel() {
        return level;
    }


    public void setLevel(Coordinates stepCoordinate, boolean player) {
        if (player) {
            level[stepCoordinate.getVertical()][stepCoordinate.getHorizontal()] = PLAYER_CHARACTER;
        } else {
            level[stepCoordinate.getVertical()][stepCoordinate.getHorizontal()] = PC_CHARACTER;
        }
    }

    //Pálya létrehozása
    public void initLevel() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                level[i][j] = SPACE;
            }
        }
    }
}
