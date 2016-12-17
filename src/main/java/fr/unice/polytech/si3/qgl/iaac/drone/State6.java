package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State6 implements State {

    /**
     *
     * Tourne à gauche
     *
     */
    @Override
    public void execute(Drone drone) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));

	drone.setAction(HEADING.toString(drone.getDirection().left()));
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
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State10());
	
    }


}
