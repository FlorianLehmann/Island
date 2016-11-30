package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State6 implements State {

    /**
     *
     * Tourne à gauche
     *
     */
    @Override
    public void execute(Drone drone) {

	drone.setAction(HEADING.toString(drone.getDirection().left()));
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));

	drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
    //drone.setLastDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));//rajout
        drone.setLastDirection("G");

    }

    /**
     *
     * Change the state
     *
     */
    @Override
    public void wait(Drone drone) {

	drone.setState(new State10());
	
    }


}
