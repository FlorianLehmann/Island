package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State23 implements State {

    /**
     *
     *
     */
    @Override
    public void execute(Drone drone) {
	if ((drone.getLastDirection()).equals("R")) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	drone.setAction(HEADING.toString(drone.getDirection().front()));
    //drone.setAction(HEADING.toString(EnumDirection.getOppose(drone.getLastDirection()).left()));
    //drone.setLastDirection(EnumDirection.getOppose(drone.getLastDirection()));

    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State24());//24

    }
    
}
