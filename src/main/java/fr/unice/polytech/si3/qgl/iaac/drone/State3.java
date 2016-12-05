package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State3 implements State {

    /**
     *
     * Demande un echo sur la droite
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(ECHO.toString(drone.getDirection().right()));
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if (ReadJSON.getInformations().get("found").equals("GROUND")) {
	    drone.setState(new State5());
	    drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
       	}
	else {
	    drone.setState(new State4());
	    drone.setNbCaseRight((int) ReadJSON.getInformations().get("range"));
	}
    }
    

}
