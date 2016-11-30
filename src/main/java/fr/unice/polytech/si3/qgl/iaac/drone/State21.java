package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State21 implements State {

    /**
     *
     *
     */
    @Override
    public void execute(Drone drone) {
    //drone.setLastDirection(EnumDirection.getOppose(drone.getLastDirection()));
	if (drone.getLastDirection().equals("R")) {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	drone.setAction(HEADING.toString(drone.getDirection().front()));
    //drone.setAction(HEADING.toString(EnumDirection.getOppose(drone.getLastDirection()).front()));
    }

    @Override
    public void wait(Drone drone) {
	drone.setState(new State22());

    }
    
}
