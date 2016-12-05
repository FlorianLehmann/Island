package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;

public class State1 implements State {

    /**
     *
     * Demande un echo devant
     *
     */
    @Override
    public void execute(Drone drone) {

	drone.setAction(ECHO.toString(drone.getDirection().front()));

    }

    /**
     *
     * Analyse du résultat et passage à l'etat suivant
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if (ReadJSON.getInformations().get("found").equals("GROUND")) {
	    drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
	    drone.setState(new State10()); //10
       // drone.setLastDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));//rajout
        drone.setLastDirection("G");

	}
	else if ((int) ReadJSON.getInformations().get("range") == 0) {
	    drone.setState(new State12());//11
	}
	else {
	    drone.setState(new State2());
	}
	 
    }


}
