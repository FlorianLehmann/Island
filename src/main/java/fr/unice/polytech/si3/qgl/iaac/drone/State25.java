package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State25 implements State {

    /**
     *
     *
     */
    @Override
    public void execute(Drone drone) {
	if ((drone.getLastDirection()).equals("R")) {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	drone.setAction(HEADING.toString(drone.getDirection().front()));	
	drone.setLastDirection(drone.getOpposeLastDirection());
    }

    @Override
    public void wait(Drone drone) {
	drone.setState(new State12());

    }
    
}
