package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;


public class State11 implements State {

    /**
     *
     * Stop la partie
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(STOP.toString(""));
    }

    /**
     *
     * Do nothing
     *
     */
    @Override
    public void wait(Drone drone) {

        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
    }

}
