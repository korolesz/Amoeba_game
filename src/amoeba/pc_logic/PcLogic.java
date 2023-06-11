package amoeba.pc_logic;

import amoeba.frontend.setup.FrontendSetup;
import amoeba.program.Coordinates;
import amoeba.program.Field;

public interface PcLogic {
    void pcSleep(int period);
    Coordinates pcStep(Field field, FrontendSetup frontendSetup);
}
