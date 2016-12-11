package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State22 implements State {

    @Override
    public void execute(Drone drone) {

	drone.setAction(FLY.toString(""));
        drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));
    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));

	drone.setState(new State23());
    }
	

}
