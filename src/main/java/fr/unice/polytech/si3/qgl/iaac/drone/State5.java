package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

public class State5 implements State {

    /**
     * Tourne à droite
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
        drone.setAction(HEADING.toString(drone.getDirection().right()));
        drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
        drone.setLastDirection("R");
    }

    /**
     * Change the state
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        drone.setState(new State10());

    }


}
