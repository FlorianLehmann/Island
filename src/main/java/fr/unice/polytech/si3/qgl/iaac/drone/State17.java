package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
public class State17 implements State {

    /**
     *
     * 
     *
     */
    @Override
    public void execute(Drone drone) {
	/*if (EnumDirection.getOppose(drone.getLastDirection()).equals(drone.getDirection().right())) {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	drone.setAction(HEADING.toString(drone.getDirection().front()));*/
     //drone.setAction(HEADING.toString(drone.getLastDirection().front()));
        if (drone.getOpposeLastDirection().equals("R")) {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
            
        }
        else {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));

            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
            
        }
        drone.setAction(HEADING.toString(drone.getDirection().front()));
        
        
    }

    /**
     *
     *
     */
    @Override
    public void wait(Drone drone) {
	drone.setState(new State18());

    }

}
