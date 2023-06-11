package amoeba.frontend;

import amoeba.frontend.setup.FrontendSetup;
import amoeba.program.Coordinates;
import amoeba.program.Field;

public interface FrontendLevel {


    void playerStep(Field field);
    void pcStep(Coordinates stepCoordinates);
    void setText(boolean isPlayer);
    void setwinnerCordinates(boolean isPlayer, Coordinates[] winnerCoordinates);
    void setVisibleFalse();
    void setBasicState(FrontendSetup frontendSetup);
}
