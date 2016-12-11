package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State5 implements State {

    /**
     *
     * Tourne à droite
     *
     */
    @Override
    public void execute(Drone drone) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));

	drone.setAction(HEADING.toString(drone.getDirection().right()));
	drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
    //drone.setLastDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));//rajout
        drone.setLastDirection("R");
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
