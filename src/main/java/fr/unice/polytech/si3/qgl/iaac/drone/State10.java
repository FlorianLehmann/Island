package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State10 implements State {


    /**
     * Vol jusqu'à la terre
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));

        drone.setAction(FLY.toString(""));
        drone.setCaseToTarget(drone.getCaseToTarget() - 1);
    }

    /**
     * Change the state
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if (drone.getCaseToTarget() <= 0) {
            drone.setState(new State12());
            drone.setLastDirection("G");

        }

    }

}
