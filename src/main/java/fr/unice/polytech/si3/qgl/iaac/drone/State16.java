package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State16 implements State {


    /**
     *
     * le drone avance
     *
     */
    public void execute(Drone drone) {
	drone.setAction(FLY.toString(""));
    drone.changeCoord(FLY, drone.getDirection());
	drone.setCaseToTarget(drone.getCaseToTarget() -1);
    }

    /**
     *
     * passage à l'état suivant
     *
     */
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State15());
    }
    
}
