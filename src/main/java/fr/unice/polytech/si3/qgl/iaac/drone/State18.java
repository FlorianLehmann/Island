package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State18 implements State {

    /**
     *
     * 
     *
     */
    @Override
    public void execute(Drone drone) {
	if (drone.getOpposeLastDirection().equals("R")) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	/*drone.setAction(HEADING.toString(drone.getDirection().back()));
    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().back()));*/
    drone.setAction(HEADING.toString(drone.getDirection().front()));
	drone.setLastDirection(drone.getOpposeLastDirection());
        
        
    }

    /**
     *
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State19());

    }

}
