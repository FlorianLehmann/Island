package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.ReadJSON;


public class State11 implements State {

    /**
     *
     * Stop la partie
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(STOP.toString(""));//on débarque!
	//drone.setAction(LAND.toString("", drone.getACrique()));//on débarque!
    }

    /**
     *
     * Do nothing
     *
     */
    @Override
    public void wait(Drone drone) {

        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
    }

}
