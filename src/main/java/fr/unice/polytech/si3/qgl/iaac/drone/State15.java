package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
public class State15 implements State {

    /**
     *
     * Demande un echo
     *
     */
    @Override
    public void execute(Drone drone) {
	/*if( EnumDirection.getOppose(drone.getLastDirection()).equals(drone.getDirection().right()))
	    drone.setAction(ECHO.toString(drone.getDirection().right()));
	else
	    drone.setAction(ECHO.toString(drone.getDirection().left()));*/
        if( drone.getLastDirection().equals(drone.getDirection().right()))
            drone.setAction(ECHO.toString(drone.getDirection().right()));
        else
            drone.setAction(ECHO.toString(drone.getDirection().left()));
	
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
	if (ReadJSON.getInformations().get("found").equals("GROUND") && (((int) ReadJSON.getInformations().get("range")) < 4)) {
	    drone.setState(new State16());
	}
	else {
	    drone.setState(new State17());
	}
	if (drone.getCaseToTarget() == 0) {
	    drone.setState(new State11());
	}
    }


}
