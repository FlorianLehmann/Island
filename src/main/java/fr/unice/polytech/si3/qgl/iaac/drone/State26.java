package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.ReadJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 11/12/2016.
 */
public class State26 implements State {

    @Override
    public void execute(Drone drone) {
        drone.setAction(LAND.toString(drone.getACrique(),drone.getNbMen()-1));

    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        
        drone.setState(new State11());
    }
}
