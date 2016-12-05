package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;


public class State10 implements State {

    /**
     *
     * Vol jusqu'à la terre
     *
     */
    @Override
    public void execute(Drone drone) {
    drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));

	drone.setAction(FLY.toString(drone.getDirection().front()));
	drone.setCaseToTarget(drone.getCaseToTarget() - 1);
    }

    /**
     *
     * Change the state
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if (drone.getCaseToTarget() <= 0) {
	    drone.setState(new State12()); //11
        drone.setLastDirection("G");//rajout

	}

    }

}