package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class Stop implements State {

    /**
     * Stop la partie
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(STOP.toString(""));
    }

    /**
     * Do nothing
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
    }

}
