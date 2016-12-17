package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State19 implements State {

    /**
     *
     * Demand un echo
     *
     */
    @Override
    public void execute(Drone drone) {

	drone.setAction(ECHO.toString(drone.getDirection().front()));
    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if (ReadJSON.getInformations().get("found").equals("GROUND")) {
	    drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
	    drone.setState(new State20());
	}
	else {
	    drone.setState(new State21());
	}
    }

}
