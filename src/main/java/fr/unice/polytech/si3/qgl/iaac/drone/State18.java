package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State18 implements State {

    /**
     *
     * 
     *
     */
    @Override
    public void execute(Drone drone) {
	if (drone.getOpposeLastDirection().equals("R")) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
	}
	else {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));
	    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
	}
	/*drone.setAction(HEADING.toString(drone.getDirection().back()));
    drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().back()));*/
    drone.setAction(HEADING.toString(drone.getDirection().front()));
	drone.setLastDirection(drone.getOpposeLastDirection());
        
        
    }

    /**
     *
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State19());

    }

}
