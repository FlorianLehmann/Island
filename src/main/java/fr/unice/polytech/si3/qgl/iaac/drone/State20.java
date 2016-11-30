package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State20 implements State {

    /**
     *
     * le drone avance
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(FLY.toString(""));
	drone.setCaseToTarget(drone.getCaseToTarget() - 1);
	
    }

    /**
     *
     */
    @Override
    public void wait(Drone drone) {
	if (drone.getCaseToTarget() <= 0) {
	    drone.setState(new State12());
	}

    }
}
