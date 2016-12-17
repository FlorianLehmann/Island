package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State4 implements State {

    /**
     *
     * Demande de changement de cap
     *
     */
    @Override
    public void execute(Drone drone) {
        
	if (drone.getNbCaseLeft() > drone.getNbCaseRight()) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));

	    drone.setAction(HEADING.toString(drone.getDirection().left()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
      	}
	else {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));

	    drone.setAction(HEADING.toString(drone.getDirection().right()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
    }

    /**
     *
     * Set the state
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State1());
    }


}
