package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;
public class State15 implements State {

    /**
     *
     * Demande un echo
     *
     */
    @Override
    public void execute(Drone drone) {
        //drone.getOpposeLastDirection().equals("R")
        if( drone.getOpposeLastDirection().equals("R"))
            drone.setAction(ECHO.toString(drone.getDirection().right()));
        else
            drone.setAction(ECHO.toString(drone.getDirection().left()));
        
	/*if( EnumDirection.getOppose(drone.getLastDirection()).equals(drone.getDirection().right()))
	    drone.setAction(ECHO.toString(drone.getDirection().right()));
	else
	    drone.setAction(ECHO.toString(drone.getDirection().left()));*/
        /*if( drone.getLastDirection().equals(drone.getDirection().right()))
            drone.setAction(ECHO.toString(drone.getDirection().right()));
        else
            drone.setAction(ECHO.toString(drone.getDirection().left()));*/
	
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
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
