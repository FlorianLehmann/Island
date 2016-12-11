package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State20 implements State {

    /**
     *
     * le drone avance
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(FLY.toString(""));
    drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));
	drone.setCaseToTarget(drone.getCaseToTarget() - 1);
	
    }

    /**
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if (drone.getCaseToTarget() <= 0) {
	    drone.setState(new State12());
	}

    }
}
